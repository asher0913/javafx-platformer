package io.github.asher0913.platformer.mvc.controller.state;

/**
 * The {@code GameState} interface defines the contract for all game states within the application.
 * Each state represents a distinct phase of the game, such as the main menu, gameplay, or game over.
 *
 * <p>Implementing classes are expected to provide logic for entering and exiting the respective state,
 * ensuring smooth transitions and proper resource management between different game phases.</p>
 *
 * @see MainMenuState
 * @see GameStartState
 * @see GameOverState
 */
public interface GameState {

    /**
     * Handles the logic for entering the state. This typically involves initializing resources,
     * setting up the UI, and starting any processes specific to the state.
     */
    void enterState();

    /**
     * Handles the logic for exiting the state. This typically involves cleaning up resources,
     * saving state if necessary, and preparing for the transition to another game state.
     */
    void exitState();
}