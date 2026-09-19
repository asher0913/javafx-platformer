package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.controller.state.GameOverState;
import io.github.asher0913.platformer.mvc.controller.state.LevelCompleteState;
import io.github.asher0913.platformer.mvc.controller.utils.CollisionHandler;
import io.github.asher0913.platformer.mvc.controller.utils.ControllerFactory;
import io.github.asher0913.platformer.mvc.controller.utils.InputHandler;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Bullet;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import io.github.asher0913.platformer.mvc.view.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The {@code GameController} class is the central coordinator of gameplay within the MVC structure.
 * It connects the {@link GameModel}, {@link GameView}, and various controllers and utilities to run
 * the game's logic, handle player input, manage state transitions, and maintain a smooth gameplay experience.
 *
 * <p>
 * <strong>Key Responsibilities:</strong>
 * <table border="1">
 * <caption>GameController Responsibilities</caption>
 * <tr><th>Responsibility</th><th>Description</th></tr>
 * <tr>
 *   <td>Manage Game State</td>
 *   <td>Starts, updates, and stops the main game loop; transitions to game over or level complete states as needed.</td>
 * </tr>
 * <tr>
 *   <td>Handle Input</td>
 *   <td>Uses {@link InputHandler} to interpret key presses into player actions (jump, move, crouch) and applies these to the player character.</td>
 * </tr>
 * <tr>
 *   <td>Collision Checks</td>
 *   <td>Utilizes the {@link CollisionHandler} to detect and process collisions between the player and game entities like coins, hearts, spikes, goals, and bullets.</td>
 * </tr>
 * <tr>
 *   <td>HUD &amp; Timers</td>
 *   <td>Updates health and score displays via {@link HealthController} and {@link ScoreController}, and manages countdowns through {@link CountDownTimerController}.</td>
 * </tr>
 * <tr>
 *   <td>Pause &amp; Restart</td>
 *   <td>Provides pause functionality through {@link PauseController} and allows level restarts and main menu returns via {@link RestartController} and {@link BackController}.</td>
 * </tr>
 * </table>
 *
 * <p>
 * The {@code GameController} follows a singleton pattern, ensuring only one instance controls the game loop and state.
 * Other parts of the system retrieve this instance via {@link #getInstance()}.
 * </p>
 *
 * <p>
 * <strong>Usage Example:</strong>
 * <pre>{@code
 * GameController controller = GameController.getInstance();
 * controller.startGameLoop(); // begins the game updates and rendering
 *
 * // During the game loop, controller.update() is called each frame by AnimationTimer,
 * // processing input, collisions, and game state transitions.
 * }</pre>
 */
public class GameController {
    private static GameController instance;
    private final GameModel model;
    private final GameView view;
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private AnimationTimer timer;
    private Player player;
    private final InputHandler inputHandler;
    private final CollisionHandler collisionHandler;
    private final PauseController pauseController;
    private boolean isPaused = false;
    private final RestartController restartController;
    private final BackController backController;
    private boolean isGameOver = false;
    private final ScoreController scoreController;
    private final HealthController healthController;
    private final CountDownTimerController countDownTimerController;
    private final GameOverState gameOverState;
    private final LevelCompleteState levelCompleteState;

    /**
     * Private constructor to enforce singleton pattern and setup all core references.
     * <p>
     * Initializes the game model and view, sets up the player, creates controllers for pause, restart,
     * back navigation, score, health, and the countdown timer. Also instantiates the state classes for
     * game over and level complete scenarios.
     * </p>
     */
    private GameController() {
        view = GameView.getInstance();
        model = GameModel.getInstance(view.getGameRoot());
        player = model.getPlayer();

        inputHandler = new InputHandler();
        collisionHandler = new CollisionHandler();

        pauseController = ControllerFactory.createPauseController(view, this);
        countDownTimerController = ControllerFactory.createCountDownTimerController(view, this);
        restartController = ControllerFactory.createRestartController(view, model, this);
        backController = ControllerFactory.createBackController(view, model, this);
        scoreController = ControllerFactory.createScoreController(model, view);
        healthController = ControllerFactory.createHealthController(model, view);

        gameOverState = GameOverState.getInstance(model.getGameRoot());
        levelCompleteState = LevelCompleteState.getInstance(model.getGameRoot());
    }

    /**
     * Retrieves the singleton instance of the {@code GameController}.
     *
     * @return the single {@code GameController} instance
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Starts the main game loop, resetting player state, position, score, and health to default values.
     * <p>
     * Also binds the player to the view for camera follow, starts the countdown timer, and initiates the
     * {@link AnimationTimer} that calls {@link #update()} each frame.
     * </p>
     */
    public void startGameLoop() {
        isGameOver = false;
        model.getGameRoot().setLayoutX(0);
        player = model.getPlayer();
        player.resetPlayerState();
        model.resetScoreHealth(50, 100);

        view.bindPlayerToView(player.getHitbox(), model.getLevelWidth());
        countDownTimerController.startCountdown();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    /**
     * Called every frame by the {@link AnimationTimer} when the game loop is running.
     * <p>
     * This method:
     * <ul>
     *   <li>Processes input using {@link InputHandler}.</li>
     *   <li>Updates health and score displays.</li>
     *   <li>Applies gravity and moves the player vertically.</li>
     *   <li>Updates bullets' positions.</li>
     *   <li>Handles collisions using {@link CollisionHandler}.</li>
     *   <li>Checks for game over conditions.</li>
     * </ul>
     * If the game is flagged as over, the update loop returns early.
     */
    public void update() {
        if (isGameOver) return;

        inputHandler.handleInput(keys, player, model);
        healthController.updateHealthDisplay();
        scoreController.updateScoreDisplay();

        if (player.getVelocity().getY() < 10) {
            player.setVelocity(player.getVelocity().add(0, 1));
        }
        player.moveY((int) player.getVelocity().getY(), model.getPlatforms());

        updateBullets();
        collisionHandler.handleCollisions(model, player);

        checkGameOver();
    }

    /**
     * Checks conditions to determine if the game is over. If the player's vertical position is below a certain
     * threshold or health drops to zero, the game transitions to the game over state.
     * <p>
     * Stops the animation timer and sets the global game over flag.
     * </p>
     */
    private void checkGameOver() {
        if (player.getHitbox().getTranslateY() > 720 || model.getPlayerHealth() <= 0) {
            isGameOver = true;
            timer.stop();
            Platform.runLater(() -> StateController.getInstance().setState(gameOverState));
        }
    }

    /**
     * Updates all bullets in the scene by calling their move methods. Bullets are retrieved from the model, and
     * each bullet moves according to its defined logic. If a bullet leaves the scene or collides with something,
     * the bullet logic handles removal.
     */
    private void updateBullets() {
        List<Rectangle> bulletHitboxes = new ArrayList<>(model.getBullets());
        for (Rectangle bulletHitbox : bulletHitboxes) {
            Bullet bullet = (Bullet) model.getEntityFromHitbox(bulletHitbox);
            if (bullet != null) {
                bullet.move(model);
            }
        }
    }

    /**
     * Returns the internal key state map, indicating which keys are currently pressed.
     * <p>
     * Other parts of the code (like input listeners) can set or clear values in this map,
     * and the {@link InputHandler} will read it during {@link #update()}.
     * </p>
     *
     * @return a {@code HashMap} mapping {@link KeyCode} to a boolean pressed state
     */
    public HashMap<KeyCode, Boolean> getKeys() {
        return keys;
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if paused, false if running normally
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Sets the pause state of the game.
     *
     * @param paused true to pause the game loop, false to resume
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    /**
     * Provides access to the {@link AnimationTimer} controlling the frame updates.
     * Useful for stopping or modifying the game loop from external logic.
     *
     * @return the current AnimationTimer instance
     */
    public AnimationTimer getTimer() {
        return timer;
    }

    /**
     * @return the {@link RestartController} responsible for restarting the current level
     */
    public RestartController getRestartController() {
        return restartController;
    }

    /**
     * @return the {@link BackController} that navigates back to the main menu or previous states
     */
    public BackController getBackController() {
        return backController;
    }

    /**
     * @return the {@link CountDownTimerController} managing the countdown during gameplay
     */
    public CountDownTimerController getCountDownTimerController() {
        return countDownTimerController;
    }

    /**
     * Provides access to the {@link GameModel} representing the game's state (entities, player data, scores, etc.).
     *
     * @return the current game model instance
     */
    public GameModel getModel() {
        return model;
    }
}