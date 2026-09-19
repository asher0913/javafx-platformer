package io.github.asher0913.platformer.mvc.controller.state;

/**
 * The {@code GameEndListener} interface defines a callback method for handling user actions
 * when the game ends.
 *
 * <p>Implementing this interface allows classes to respond to the user's decision to exit
 * from the game end screen.</p>
 */
public interface GameEndListener {

    /**
     * Triggered when the user clicks the "Exit" button on the game end screen.
     *
     * <p>This method should contain logic to transition to the main menu or close the game application.</p>
     */
    void onExitClicked();
}

