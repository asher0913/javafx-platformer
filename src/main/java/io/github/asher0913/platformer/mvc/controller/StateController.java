package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.controller.state.GameState;

/**
 * Manages the state transitions of the game, ensuring that only one {@link GameState}
 * is active at any time. Implements the Singleton design pattern to provide a single
 * global instance of the controller.
 *
 * <p>The {@code StateController} is responsible for transitioning between states,
 * such as the main menu, gameplay, and game over phases. It invokes the appropriate
 * methods on the {@link GameState} interface to handle entering and exiting states.</p>
 */
public class StateController {

    /**
     * The singleton instance of the {@code StateController}.
     */
    private static StateController instance;

    /**
     * The current active {@link GameState}.
     */
    private GameState currentState;

    /**
     * Private constructor to prevent direct instantiation.
     * <p>Ensures that the Singleton design pattern is adhered to.</p>
     */
    private StateController() {}

    /**
     * Retrieves the singleton instance of the {@code StateController}.
     *
     * @return the single instance of {@code StateController}
     */
    public static StateController getInstance() {
        if (instance == null) {
            instance = new StateController();
        }
        return instance;
    }

    /**
     * Sets the current game state and handles the transition from the previous state.
     * <p>If there is an existing state, its {@code exitState} method is called before
     * entering the new state via its {@code enterState} method.</p>
     *
     * @param newState the new {@link GameState} to transition to
     */
    public void setState(GameState newState) {
        if (currentState != null) {
            currentState.exitState();
        }
        currentState = newState;
        currentState.enterState();
    }

    /**
     * Retrieves the current active game state.
     *
     * @return the current {@link GameState}, or {@code null} if no state is set
     */
    public GameState getCurrentState() {
        return currentState;
    }
}