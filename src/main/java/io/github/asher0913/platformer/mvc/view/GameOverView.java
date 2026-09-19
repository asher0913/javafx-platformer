package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.state.GameOverListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the view for the "Game Over" screen in the platformer game.
 * Implements the Singleton design pattern to ensure a single instance is used across the application.
 *
 * <p>This class is responsible for displaying the "Game Over" screen using an FXML layout.
 * It allows users to restart the game or exit to the main menu by invoking appropriate methods
 * on the provided {@link GameOverListener}.</p>
 */
public class GameOverView {

    /**
     * The singleton instance of {@code GameOverView}.
     */
    private static GameOverView instance;

    /**
     * The stage used to display the "Game Over" screen.
     */
    private Stage gameOverStage;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private GameOverView() {}

    /**
     * Retrieves the singleton instance of {@code GameOverView}.
     *
     * @return the single instance of {@code GameOverView}
     */
    public static GameOverView getInstance() {
        if (instance == null) {
            instance = new GameOverView();
        }
        return instance;
    }

    /**
     * Displays the "Game Over" screen with interactive options for the user.
     * <p>The screen layout is loaded from an FXML file, and buttons are initialized
     * to invoke actions via the provided {@link GameOverListener}.</p>
     *
     * @param listener the listener for handling user interactions on the "Game Over" screen
     * @throws RuntimeException if the FXML file cannot be loaded
     */
    public void showGameOverView(GameOverListener listener) {
        try {
            FXMLLoader loader = new FXMLLoader(GameOverView.class.getResource("/fxml/game_over.fxml"));
            Parent root = loader.load();

            gameOverStage = new Stage();
            gameOverStage.setTitle("Game Over");
            gameOverStage.setScene(new Scene(root, 400, 300));
            gameOverStage.show();

            Button restartButton = (Button) root.lookup("#restartButton");
            if (restartButton != null) {
                restartButton.setOnAction(event -> listener.onRestartClicked());
            }

            Button exitButton = (Button) root.lookup("#exitButton");
            if (exitButton != null) {
                exitButton.setOnAction(event -> listener.onExitClicked());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load game_over.fxml", e);
        }
    }

    /**
     * Closes the "Game Over" screen.
     */
    public void close() {
        if (gameOverStage != null) {
            gameOverStage.close();
        }
    }
}