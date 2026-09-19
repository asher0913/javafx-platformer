package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.state.GameEndListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * The {@code GameEndView} class is responsible for displaying the game end screen.
 * It shows the player's total score, a high scores list, and provides an option to exit the game.
 *
 * <p>This class follows the Singleton Pattern to ensure only one instance of the
 * {@code GameEndView} exists throughout the application. It interacts with an {@code GameEndListener}
 * to handle user actions such as exiting the game.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *     <li>Displays the player's total score at the end of the game.</li>
 *     <li>Shows a dynamically updated high scores list.</li>
 *     <li>Provides an "Exit" button for navigation back to the main menu.</li>
 * </ul>
 *
 * <p>This class loads the {@code game_end.fxml} layout file, which defines the user interface for
 * the game end screen.</p>
 */
public class GameEndView {
    /** The single instance of {@code GameEndView} for the Singleton Pattern. */
    private static GameEndView instance;

    /** The stage used to display the game end screen. */
    private Stage gameEndStage;

    /**
     * Private constructor to prevent external instantiation.
     * This enforces the Singleton Pattern for {@code GameEndView}.
     */
    private GameEndView() {
        // Prevent instantiation from outside the class
    }

    /**
     * Provides the single instance of {@code GameEndView}.
     *
     * @return The single instance of {@code GameEndView}.
     */
    public static GameEndView getInstance() {
        if (instance == null) {
            instance = new GameEndView();
        }
        return instance;
    }

    /**
     * Displays the game end screen with the player's total score and a high scores list.
     * It also sets up a button to handle the exit action via the provided {@code GameEndListener}.
     *
     * <p>The method loads the {@code game_end.fxml} file to create the user interface,
     * updates the total score display, and dynamically populates the high scores list.</p>
     *
     * @param listener   The {@code GameEndListener} to handle the "Exit" button click event.
     * @param totalScore The player's total score to display.
     * @param highScores A list of high scores to display in descending order.
     */
    public void showGameEndView(GameEndListener listener, int totalScore, List<Integer> highScores) {
        try {
            // Load the FXML layout for the game end screen
            FXMLLoader loader = new FXMLLoader(GameEndView.class.getResource("/fxml/game_end.fxml"));
            Parent root = loader.load();

            // Initialize and configure the stage
            gameEndStage = new Stage();
            gameEndStage.setTitle("Game End");
            gameEndStage.setScene(new Scene(root, 600, 400));
            gameEndStage.show();

            // Display the player's total score
            Text totalScoreText = (Text) root.lookup("#totalScoreText");
            if (totalScoreText != null) {
                totalScoreText.setText("Total Score: " + totalScore);
            }

            // Populate the high scores list dynamically
            VBox highScoresBox = (VBox) root.lookup("#highScoresBox");
            if (highScoresBox != null) {
                highScoresBox.getChildren().clear();
                for (int i = 0; i < highScores.size(); i++) {
                    Text scoreText = new Text((i + 1) + ". " + highScores.get(i));
                    scoreText.setStyle("-fx-font-size: 16px;");
                    highScoresBox.getChildren().add(scoreText);
                }
            }

            // Set up the exit button to trigger the listener's callback
            Button exitButton = (Button) root.lookup("#mainMenuButton");
            if (exitButton != null) {
                exitButton.setOnAction(event -> listener.onExitClicked());
            }

        } catch (IOException e) {
            // Print an error trace if the FXML file fails to load
            e.printStackTrace();
        }
    }

    /**
     * Closes the game end screen.
     *
     * <p>If the game end stage has been initialized, it will be closed and hidden from view.</p>
     */
    public void close() {
        if (gameEndStage != null) {
            gameEndStage.close();
        }
    }
}

