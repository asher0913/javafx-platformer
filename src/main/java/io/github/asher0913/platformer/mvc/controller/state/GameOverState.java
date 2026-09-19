package io.github.asher0913.platformer.mvc.controller.state;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.view.GameOverView;
import javafx.scene.layout.Pane;

/**
 * The {@code GameOverState} class represents the "Game Over" state in the game.
 * It is responsible for displaying the "Game Over" screen and handling user interactions
 * such as restarting the game or exiting to the main menu.
 *
 * <p>This class implements the {@link GameState} interface to define the behavior specific
 * to the "Game Over" state and the {@link GameOverListener} interface to handle events triggered
 * by the {@link GameOverView}.
 *
 * <p>This class uses the Singleton design pattern to ensure that only one instance of the
 * "Game Over" state exists during the game's lifecycle, reducing memory overhead and simplifying
 * state transitions.
 *
 * @see GameState
 * @see GameOverView
 * @see GameOverListener
 */
public class GameOverState implements GameState, GameOverListener {
    private static GameOverState instance;
    private final Pane appRoot;

    /**
     * Private constructor to enforce Singleton pattern.
     * @param appRoot      the root pane for the game application
     */
    private GameOverState(Pane appRoot) {
        this.appRoot = appRoot;
    }

    /**
     * Retrieves the current instance of {@code GameOverState}.
     *
     * @return the existing instance of {@code GameOverState}
     */
    public static GameOverState getInstance() {
        return instance;
    }

    /**
     * Retrieves the existing instance of {@code GameOverState}, or creates a new one
     * if none exists.
     * @param appRoot      the root pane for the game application
     * @return the single instance of {@code GameOverState}
     */
    public static GameOverState getInstance(Pane appRoot) {
        if (instance == null) {
            instance = new GameOverState(appRoot);
        }
        return instance;
    }

    /**
     * Enters the "Game Over" state by displaying the "Game Over" screen and setting up event listeners.
     */
    @Override
    public void enterState() {
        GameOverView.getInstance().showGameOverView(this);
    }

    /**
     * Exits the "Game Over" state by closing the "Game Over" screen and clearing player input states.
     */
    @Override
    public void exitState() {
        GameOverView.getInstance().close();
        GameController.getInstance().getKeys().clear();
    }

    /**
     * Handles the "Restart" button click event. Closes the "Game Over" screen and restarts the current level.
     */
    @Override
    public void onRestartClicked() {
        GameOverView.getInstance().close();
        GameController.getInstance().getRestartController().restartGame();
    }

    /**
     * Handles the "Exit" button click event. Closes the "Game Over" screen and transitions back to the main menu.
     */
    @Override
    public void onExitClicked() {
        GameOverView.getInstance().close();
        GameController.getInstance().getBackController().backGame();
    }
}