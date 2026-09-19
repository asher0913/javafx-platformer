package io.github.asher0913.platformer.mvc.controller.state;

/**
 * The {@code LevelCompleteListener} interface defines the contract for handling user actions
 * when a game level is successfully completed.
 *
 * <p>Implementing this interface allows classes to respond to user interactions, such as
 * proceeding to the next level, restarting the current level, or exiting the game.
 * This is typically used in conjunction with level completion screens or menus where
 * users make decisions after successfully finishing a level.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *     <li>Handle the transition to the next level.</li>
 *     <li>Handle restarting the current level.</li>
 *     <li>Handle exiting to the main menu or ending the game.</li>
 * </ul>
 */
public interface LevelCompleteListener {

    /**
     * Triggered when the user opts to proceed to the next level.
     *
     * <p>This method should contain logic to load and initialize the next level
     * in the game.</p>
     */
    void onNextLevel();

    /**
     * Triggered when the user chooses to restart the current level.
     *
     * <p>This method should reset the game state to its initial configuration
     * for the current level, allowing the player to retry.</p>
     */
    void onRestartClicked();

    /**
     * Triggered when the user decides to exit the level completion screen.
     *
     * <p>This method typically transitions the game back to the main menu or
     * ends the game based on the implementation.</p>
     */
    void onExitClicked();
}
