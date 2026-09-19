package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.RestartView;
import javafx.scene.layout.Pane;

/**
 * The {@code RestartController} class handles the game's restart functionality,
 * allowing the player to restart the current level seamlessly.
 *
 * <p>This controller:
 * <ul>
 *   <li>Initializes and manages the restart button using {@link RestartView}.</li>
 *   <li>Interacts with {@link GameController} to reset game logic and state.</li>
 *   <li>Resets the {@link GameModel} to its initial state for the current level.</li>
 *   <li>Ensures the game loop is restarted correctly after resetting the state.</li>
 * </ul>
 *
 * <p>This class is part of the MVC architecture, connecting the restart-related UI elements with
 * the underlying game logic to provide a responsive and consistent user experience.
 *
 * @see RestartView
 * @see GameController
 * @see GameModel
 */
public class RestartController {
    private final RestartView restartView;
    private final GameController gameController;
    private final GameModel model;

    /**
     * Constructs a {@code RestartController} with the specified UI root, game controller, and game model.
     *
     * @param uiRoot         the root pane for user interface elements
     * @param gameController the controller managing the game's overall state and behavior
     * @param model          the model representing the game's logic and state
     */
    public RestartController(Pane uiRoot, GameController gameController, GameModel model) {
        restartView = new RestartView(uiRoot);
        this.gameController = gameController;
        this.model = model;
        setupRestartAction();
    }

    /**
     * Configures the restart button to trigger the restart action when clicked.
     */
    private void setupRestartAction() {
        restartView.setRestartAction(this::restartGame);
    }

    /**
     * Restarts the current level by resetting the game state and logic.
     *
     * <p>This method:
     * <ul>
     *   <li>Stops the game loop timer.</li>
     *   <li>Clears and reinitializes the game root in the {@link GameModel}.</li>
     *   <li>Resets the game root's position and clears any active key inputs.</li>
     *   <li>Starts a new game loop to reflect the updated state.</li>
     * </ul>
     */
    public void restartGame() {
        gameController.getTimer().stop();
        gameController.getKeys().clear();
        model.stopAllMonsterShooting();
        model.initLevel(model.getLevels()[model.getCurrentLevelIndex()]);
        gameController.startGameLoop();
    }
}