package io.github.asher0913.platformer.mvc.controller.state;

/**
 * Defines the listener interface for actions performed on the "Game Over" screen.
 *
 * <p>This interface is implemented by classes that handle user interactions during
 * the game over phase, such as restarting the game or exiting to the main menu.</p>
 */
public interface GameOverListener {

    /**
     * Invoked when the user clicks the "Restart" button on the "Game Over" screen.
     * <p>This action typically restarts the game from the beginning or the last checkpoint.</p>
     */
    void onRestartClicked();

    /**
     * Invoked when the user clicks the "Exit" button on the "Game Over" screen.
     * <p>This action typically navigates the user back to the main menu.</p>
     */
    void onExitClicked();
}
