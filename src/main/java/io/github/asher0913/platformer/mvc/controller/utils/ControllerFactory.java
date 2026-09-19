package io.github.asher0913.platformer.mvc.controller.utils;

import io.github.asher0913.platformer.mvc.controller.*;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.GameView;

/**
 * The <code>ControllerFactory</code> class provides a centralized location for creating controller instances.
 * <p>
 * By using this factory, the game can easily instantiate controllers like {@link PauseController},
 * {@link RestartController}, {@link BackController}, {@link ScoreController}, {@link HealthController},
 * and {@link CountDownTimerController} without duplicating creation logic or requiring manual wiring of
 * dependencies each time.
 * </p>
 * <p>
 * This approach improves code maintainability and flexibility, making it simpler to swap or modify
 * controllers as the game evolves.
 * </p>
 */
public class ControllerFactory {

    /**
     * Creates a new {@link PauseController} configured with the given view and game controller.
     *
     * @param view          the main game view for UI references
     * @param gameController the main game controller managing the loop and input
     * @return a configured PauseController instance
     */
    public static PauseController createPauseController(GameView view, GameController gameController) {
        return new PauseController(view.getUiRoot(), gameController);
    }

    /**
     * Creates a new {@link RestartController} enabling the player to restart the current level.
     *
     * @param view          the game view for UI placement
     * @param model         the current game model holding level and player data
     * @param gameController the main game controller coordinating game actions
     * @return a configured RestartController instance
     */
    public static RestartController createRestartController(GameView view, GameModel model, GameController gameController) {
        return new RestartController(view.getUiRoot(), gameController, model);
    }

    /**
     * Creates a new {@link BackController} enabling transitions back to main menu or other states.
     *
     * @param view          the game view for UI placement
     * @param model         the current game model
     * @param gameController the primary game controller managing state
     * @return a configured BackController instance
     */
    public static BackController createBackController(GameView view, GameModel model, GameController gameController) {
        return new BackController(view.getUiRoot(), model, gameController);
    }

    /**
     * Creates a new {@link ScoreController} to track and display player score.
     *
     * @param model the current game model providing score data
     * @param view  the game view for placing score display elements
     * @return a configured ScoreController instance
     */
    public static ScoreController createScoreController(GameModel model, GameView view) {
        return new ScoreController(model, view.getUiRoot());
    }

    /**
     * Creates a new {@link HealthController} to track and display player health.
     *
     * @param model the current game model providing health data
     * @param view  the game view for placing health display elements
     * @return a configured HealthController instance
     */
    public static HealthController createHealthController(GameModel model, GameView view) {
        return new HealthController(model, view.getUiRoot());
    }

    /**
     * Creates a new {@link CountDownTimerController} to manage and display a countdown timer.
     *
     * @param view           the game view for placing the timer display
     * @param gameController the main game controller to handle time-up events
     * @return a configured CountDownTimerController instance
     */
    public static CountDownTimerController createCountDownTimerController(GameView view, GameController gameController) {
        return new CountDownTimerController(view.getUiRoot(), gameController);
    }
}