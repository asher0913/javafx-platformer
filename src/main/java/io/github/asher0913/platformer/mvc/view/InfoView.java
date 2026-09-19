package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.StateController;
import io.github.asher0913.platformer.mvc.controller.state.MainMenuState;
import io.github.asher0913.platformer.mvc.view.utils.SceneManager;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Represents the information screen view of the game.
 * The information screen provides details about the game and includes a button to return to the main menu.
 *
 * <p>This class implements the Singleton design pattern to ensure that only one instance of the
 * information view is created and reused throughout the application.</p>
 */
public class InfoView {

    /**
     * The singleton instance of the {@code InfoView}.
     */
    private static InfoView instance;

    /**
     * The primary stage used to display the information view.
     */
    private final Stage primaryStage;

    /**
     * The root pane for the user interface elements.
     */
    private final Pane appRoot;

    /**
     * Private constructor to initialize the {@code InfoView} with the primary stage and root pane.
     *
     * @param primaryStage the {@link Stage} used to display the information screen.
     * @param appRoot the {@link Pane} that serves as the root container for user interface elements.
     */
    private InfoView(Stage primaryStage, Pane appRoot) {
        this.primaryStage = primaryStage;
        this.appRoot = appRoot;
    }

    /**
     * Returns the singleton instance of the {@code InfoView}.
     * If the instance does not exist, it creates a new one using the provided parameters.
     *
     * @param primaryStage the {@link Stage} used to display the information screen.
     * @param appRoot the {@link Pane} that serves as the root container for user interface elements.
     * @return the singleton instance of the {@code InfoView}.
     */
    public static InfoView getInstance(Stage primaryStage, Pane appRoot) {
        if (instance == null) {
            instance = new InfoView(primaryStage, appRoot);
        }
        return instance;
    }

    /**
     * Displays the information screen by loading the associated FXML layout.
     * The screen contains a back button that returns the user to the main menu.
     *
     * <p>Handles exceptions that may occur during the FXML loading process.</p>
     */
    public void showInfo() {
        try {
            Parent root = SceneManager.loadSceneWithAppRoot(primaryStage, "/fxml/info.fxml");
            primaryStage.setTitle("Game Info");

            Button backButton = (Button) root.lookup("#backButton");
            backButton.setOnAction(event -> StateController.getInstance().setState(MainMenuState.getInstance(primaryStage, appRoot)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}