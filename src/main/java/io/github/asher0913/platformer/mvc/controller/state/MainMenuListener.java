package io.github.asher0913.platformer.mvc.controller.state;

/**
 * Defines the listener interface for actions performed on the main menu screen.
 *
 * <p>This interface is implemented by classes that handle user interactions on
 * the main menu, such as starting a new game, displaying the options screen, or showing information about the game.</p>
 */
public interface MainMenuListener {

    /**
     * Invoked when the user clicks the "Start Game" button on the main menu.
     * <p>This action typically transitions the application to the gameplay state.</p>
     */
    void onStartGame();

    /**
     * Invoked when the user clicks the "Info" button on the main menu.
     * <p>This action typically displays a screen with information about the game.</p>
     */
    void onShowInfo();

    /**
     * Invoked when the user clicks the "Options" button on the main menu.
     * <p>This action typically navigates the user to the options screen for customizing game settings.</p>
     */
    void onShowOptions();
}
