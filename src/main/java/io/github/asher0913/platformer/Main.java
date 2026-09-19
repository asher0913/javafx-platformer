package io.github.asher0913.platformer;

import io.github.asher0913.platformer.mvc.controller.state.MainMenuState;
import io.github.asher0913.platformer.mvc.controller.StateController;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * The entry point for the game application.
 *
 * <p>The {@code Main} class initializes the application, sets up the main game state, and displays
 * the primary stage. This class integrates the main menu state with the application's root layout,
 * providing a seamless starting point for the game.</p>
 *
 * <p>It extends {@link javafx.application.Application} to utilize JavaFX's application lifecycle.</p>
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application and initializes the main game state.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        Pane appRoot = new Pane();
        appRoot.setPrefSize(800, 600);
        StateController stateController = StateController.getInstance();
        stateController.setState(MainMenuState.getInstance(primaryStage, appRoot));
        primaryStage.show();
    }
    /**
     * The main entry point for launching the game application.
     *
     * @param args the command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
