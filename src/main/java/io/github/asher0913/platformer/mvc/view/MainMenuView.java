package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.state.MainMenuListener;
import io.github.asher0913.platformer.mvc.view.utils.SceneManager;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Represents the main menu screen of the game, allowing the user to interact with
 * key options such as starting the game, adjusting settings, and viewing information.
 *
 * <p>This class follows the Singleton design pattern to ensure a single instance of the
 * main menu view is used throughout the application.</p>
 *
 * <p>The {@link MainMenuView} is responsible for loading the main menu UI and managing
 * user interactions through the {@link MainMenuListener} interface.</p>
 */
public class MainMenuView {
    private static MainMenuView instance;
    private final Stage primaryStage;

    /**
     * Private constructor for initializing the main menu view with the primary stage.
     *
     * @param primaryStage the main application stage on which the main menu is displayed.
     */
    private MainMenuView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Retrieves the singleton instance of the main menu view.
     * If the instance does not exist, it is created with the provided stage.
     *
     * @param primaryStage the main application stage on which the main menu is displayed.
     * @return the singleton instance of the {@code MainMenuView}.
     */
    public static MainMenuView getInstance(Stage primaryStage) {
        if (instance == null) {
            instance = new MainMenuView(primaryStage);
        }
        return instance;
    }

    /**
     * Displays the main menu on the primary stage and sets up event handlers for user interactions.
     *
     * <p>The main menu UI includes buttons for starting the game, opening the options menu, and
     * viewing game information. Button actions are delegated to the provided {@link MainMenuListener}.</p>
     *
     * <p>The method also applies a background image to the main menu screen.</p>
     *
     * @param listener the listener for handling user interactions on the main menu.
     */
    public void show(MainMenuListener listener) {
        Parent root = SceneManager.loadSceneWithAppRoot(primaryStage, "/fxml/main_menu.fxml");
        primaryStage.setTitle("Main Menu");

        root.setStyle(
                "-fx-background-image: url('/images/mainMenuBackground.png');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;"
        );

        Button startButton = (Button) root.lookup("#startButton");
        Button optionsButton = (Button) root.lookup("#optionsButton");
        Button infoButton = (Button) root.lookup("#infoButton");

        if (startButton != null) {
            startButton.setOnAction(event -> listener.onStartGame());
        }

        if (optionsButton != null) {
            optionsButton.setOnAction(event -> listener.onShowOptions());
        }

        if (infoButton != null) {
            infoButton.setOnAction(event -> listener.onShowInfo());
        }
    }
}
