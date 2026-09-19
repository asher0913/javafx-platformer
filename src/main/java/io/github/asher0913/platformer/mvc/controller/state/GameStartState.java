package io.github.asher0913.platformer.mvc.controller.state;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.LevelData;
import io.github.asher0913.platformer.mvc.view.GameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code GameStartState} class represents the state of the game when the player starts a new game.
 * This state initializes the game environment, including the level, game objects, and user interface.
 *
 * <p>This class implements the {@link GameState} interface, providing behavior for entering and exiting
 * the "Game Start" state. It utilizes the Singleton design pattern to ensure a single instance is maintained
 * throughout the game's lifecycle.</p>
 *
 * @see GameState
 * @see GameController
 * @see GameView
 * @see GameModel
 */
public class GameStartState implements GameState {
    private static GameStartState instance;
    private final Stage primaryStage;
    private final Scene scene;

    /**
     * Private constructor to initialize the game start state with the primary stage.
     *
     * @param primaryStage the primary stage of the application
     */
    private GameStartState(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GameView.getInstance().initView();
        scene = new Scene(GameView.getInstance().getAppRoot());
    }

    /**
     * Retrieves the single instance of the {@code GameStartState}, creating it if it does not exist.
     *
     * @param primaryStage the primary stage of the application
     * @return the single instance of {@code GameStartState}
     */
    public static GameStartState getInstance(Stage primaryStage) {
        if (instance == null) {
            instance = new GameStartState(primaryStage);
        }
        return instance;
    }

    /**
     * Enters the "Game Start" state by initializing the game level, configuring the game scene, and
     * starting the game loop.
     */
    @Override
    public void enterState() {
        GameModel.getInstance(GameView.getInstance().getGameRoot()).initLevel(LevelData.Level1);

        GameController controller = GameController.getInstance();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Platformer Game");

        scene.setOnKeyPressed(event -> controller.getKeys().put(event.getCode(), true));
        scene.setOnKeyReleased(event -> controller.getKeys().put(event.getCode(), false));

        controller.startGameLoop();
    }

    /**
     * Exits the "Game Start" state. Currently, no specific actions are performed when exiting this state.
     */
    @Override
    public void exitState() {
        // No specific logic for exiting the Game Start state
    }
}