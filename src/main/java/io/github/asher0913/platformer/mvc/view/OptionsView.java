package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.StateController;
import io.github.asher0913.platformer.mvc.controller.state.MainMenuState;
import io.github.asher0913.platformer.mvc.view.utils.AppConfig;
import io.github.asher0913.platformer.mvc.view.utils.SceneManager;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Represents the options menu screen of the game, where users can customize settings such as background color.
 *
 * <p>This class follows the Singleton design pattern to ensure a single instance of the
 * options view is used throughout the application, preserving the settings and reducing resource usage.</p>
 *
 * <p>The {@link OptionsView} is responsible for loading the options menu UI, handling user interactions,
 * and applying changes to the application's configuration.</p>
 */
public class OptionsView {
    private static OptionsView instance;
    private final Stage primaryStage;
    private final Pane appRoot;

    /**
     * Private constructor for initializing the options menu view with the primary stage and root pane.
     *
     * @param primaryStage the main application stage on which the options menu is displayed.
     * @param appRoot      the root pane of the application.
     */
    private OptionsView(Stage primaryStage, Pane appRoot) {
        this.primaryStage = primaryStage;
        this.appRoot = appRoot;
    }

    /**
     * Retrieves the singleton instance of the options view.
     * If the instance does not exist, it is created with the provided stage and root pane.
     *
     * @param primaryStage the main application stage on which the options menu is displayed.
     * @param appRoot      the root pane of the application.
     * @return the singleton instance of the {@code OptionsView}.
     */
    public static OptionsView getInstance(Stage primaryStage, Pane appRoot) {
        if (instance == null) {
            instance = new OptionsView(primaryStage, appRoot);
        }
        return instance;
    }

    /**
     * Displays the options menu on the primary stage and sets up event handlers for user interactions.
     *
     * <p>The options menu UI includes a color selection combo box for changing the background color
     * and a back button for returning to the main menu. User-selected colors are applied dynamically
     * and saved in the application's configuration.</p>
     */
    public void showOptions() {
        Parent root = SceneManager.loadSceneWithAppRoot(primaryStage, "/fxml/options.fxml");
        primaryStage.setTitle("Options");
        primaryStage.show();

        ComboBox<String> colorComboBox = (ComboBox<String>) root.lookup("#colorComboBox");
        Button backButton = (Button) root.lookup("#backButton");

        colorComboBox.getItems().addAll("Red", "Pink", "Brown", "Yellow", "Purple", "Orange", "Black", "White");

        colorComboBox.setOnAction(event -> {
            String selectedColor = colorComboBox.getValue();
            if (selectedColor != null) {
                AppConfig.setBackgroundColor(selectedColor.toLowerCase());
                root.setStyle("-fx-background-color: " + selectedColor.toLowerCase() + ";");
            }
        });

        backButton.setOnAction(event -> {
            StateController.getInstance().setState(MainMenuState.getInstance(primaryStage, appRoot));
        });
    }
}