package io.github.asher0913.platformer.mvc.controller.state;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.LevelCompleteView;
import javafx.scene.layout.Pane;

/**
 * Represents the state of the game when a level is successfully completed.
 * Implements the {@link GameState} interface for state management
 * and {@link LevelCompleteListener} to handle user interactions on the level completion screen.
 *
 * <p>This state is responsible for displaying the level completion view,
 * managing transitions to the next level, restarting the current level, or exiting to the main menu.</p>
 *
 * <p>Follows the Singleton design pattern to ensure a single instance manages
 * all level completion logic and resources.</p>
 *
 * @see GameState
 * @see LevelCompleteListener
 */
public class LevelCompleteState implements GameState, LevelCompleteListener {
    private static LevelCompleteState instance;
    private final Pane appRoot;

    /**
     * Private constructor to enforce the Singleton pattern.
     * @param appRoot the application's root pane
     */
    private LevelCompleteState(Pane appRoot) {
        this.appRoot = appRoot;
    }

    /**
     * Retrieves the current instance of {@code LevelCompleteState}.
     *
     * @return the existing {@code LevelCompleteState} instance
     */
    public static LevelCompleteState getInstance() {
        return instance;
    }

    /**
     * Retrieves or creates the singleton instance of {@code LevelCompleteState}.
     * @param appRoot the application's root pane
     * @return the {@code LevelCompleteState} instance
     */
    public static LevelCompleteState getInstance(Pane appRoot) {
        if (instance == null) {
            instance = new LevelCompleteState(appRoot);
        }
        return instance;
    }

    /**
     * Enters the level completion state by displaying the level complete view
     * and registering the current instance as the view's listener.
     */
    @Override
    public void enterState() {
        LevelCompleteView.getInstance().showLevelCompleteView(this);
    }

    /**
     * Exits the level completion state by closing the level complete view
     * and clearing user input data.
     */
    @Override
    public void exitState() {
        LevelCompleteView.getInstance().close();
        GameController.getInstance().getKeys().clear();
    }

    /**
     * Handles the transition to the next level when the "Next Level" button is clicked.
     */
    @Override
    public void onNextLevel() {
        GameModel.getInstance().increaseTotalScore(GameModel.getInstance().getCurrentScore());
        LevelCompleteView.getInstance().close();
        GameModel.getInstance().loadNextLevel();
    }

    /**
     * Restarts the current level when the "Restart" button is clicked.
     */
    @Override
    public void onRestartClicked() {
        LevelCompleteView.getInstance().close();
        GameController.getInstance().getRestartController().restartGame();
    }

    /**
     * Exits to the main menu when the "Exit" button is clicked.
     */
    @Override
    public void onExitClicked() {
        LevelCompleteView.getInstance().close();
        GameController.getInstance().getBackController().backGame();
    }
}