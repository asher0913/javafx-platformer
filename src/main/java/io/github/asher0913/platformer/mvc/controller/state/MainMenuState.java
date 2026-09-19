package io.github.asher0913.platformer.mvc.controller.state;

import io.github.asher0913.platformer.mvc.controller.StateController;
import io.github.asher0913.platformer.mvc.view.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Represents the state of the game when the main menu is displayed.
 * Implements the {@link GameState} interface for managing transitions
 * and the {@link MainMenuListener} interface for handling user interactions
 * within the main menu.
 *
 * <p>This state is responsible for displaying the main menu, allowing
 * users to start a game, view options, or access information about the game.</p>
 *
 * <p>Follows the Singleton design pattern to ensure only one instance
 * of the main menu state exists during the application's lifecycle.</p>
 *
 * @see GameState
 * @see MainMenuListener
 */
public class MainMenuState implements GameState, MainMenuListener {
    private static MainMenuState instance;
    private final Stage primaryStage;
    private final Pane appRoot;
    private final MainMenuView mainMenuView;

    /**
     * Private constructor to enforce Singleton pattern.
     *
     * @param primaryStage the primary stage of the application
     * @param appRoot the root pane for the application's UI
     */
    private MainMenuState(Stage primaryStage, Pane appRoot) {
        this.primaryStage = primaryStage;
        this.appRoot = appRoot;
        this.mainMenuView = MainMenuView.getInstance(primaryStage);
    }

    /**
     * Retrieves the current instance of {@code MainMenuState}.
     *
     * @return the existing {@code MainMenuState} instance
     */
    public static MainMenuState getInstance() {
        return instance;
    }

    /**
     * Retrieves or creates the singleton instance of {@code MainMenuState}.
     *
     * @param primaryStage the primary stage of the application
     * @param appRoot the root pane for the application's UI
     * @return the {@code MainMenuState} instance
     */
    public static MainMenuState getInstance(Stage primaryStage, Pane appRoot) {
        if (instance == null) {
            instance = new MainMenuState(primaryStage, appRoot);
        }
        return instance;
    }

    /**
     * Displays the main menu when entering the state.
     */
    @Override
    public void enterState() {
        mainMenuView.show(this);
    }

    /**
     * Cleans up resources or resets variables when exiting the state.
     */
    @Override
    public void exitState() {
        // No specific actions required when exiting the main menu state
    }

    /**
     * Starts the game when the "Start Game" button is clicked in the main menu.
     */
    @Override
    public void onStartGame() {
        StateController.getInstance().setState(GameStartState.getInstance(primaryStage));
    }

    /**
     * Displays the options menu when the "Options" button is clicked in the main menu.
     */
    @Override
    public void onShowOptions() {
        OptionsView.getInstance(primaryStage, appRoot).showOptions();
    }

    /**
     * Displays the game information screen when the "Info" button is clicked in the main menu.
     */
    @Override
    public void onShowInfo() {
        InfoView.getInstance(primaryStage, appRoot).showInfo();
    }

    /**
     * Returns the root pane for the application's UI.
     *
     * @return the application's root pane
     */
    public Pane getAppRoot() {
        return appRoot;
    }
}