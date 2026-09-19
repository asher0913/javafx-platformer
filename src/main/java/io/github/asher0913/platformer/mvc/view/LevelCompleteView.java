package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.controller.state.LevelCompleteListener;
import io.github.asher0913.platformer.mvc.model.GameModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code LevelCompleteView} class represents the UI screen displayed when a level is successfully completed.
 * This class is implemented as a singleton to ensure only one instance of the level completion stage is shown at a time.
 * It loads the level completion FXML, displays the current score, and provides options to restart, exit, or proceed
 * to the next level.
 *
 *
 * <p>The above snippet retrieves the singleton instance, shows the level complete screen,
 * and sets listeners for the various buttons displayed on the screen.</p>
 *
 * @see LevelCompleteListener
 * @see GameOverView
 */
public class LevelCompleteView {
    private static LevelCompleteView instance;
    private Stage levelCompleteStage;

    /**
     * Private constructor to enforce singleton pattern.
     * Use {@link #getInstance()} to obtain the singleton instance of this class.
     */
    private LevelCompleteView() {};

    /**
     * Retrieves the singleton instance of {@code LevelCompleteView}.
     * If the instance does not already exist, it is created.
     *
     * @return The singleton instance of {@code LevelCompleteView}.
     */
    public static LevelCompleteView getInstance() {
        if (instance == null) {
            instance = new LevelCompleteView();
        }
        return instance;
    }

    /**
     * Displays the level complete screen to the user.
     * This method:
     * <ul>
     *   <li>Loads the FXML layout resource for the level complete UI.</li>
     *   <li>Creates a new {@link Stage} and sets a scene containing the loaded layout.</li>
     *   <li>Shows the player's current score on the screen.</li>
     *   <li>Configures actions for the "Restart", "Exit", and "Next Level" buttons,
     *       all of which delegate their events to the provided {@link LevelCompleteListener}.</li>
     * </ul>
     *
     * <p>Note: This method must be called on the JavaFX Application Thread. It is typically called
     * from the controller or another view class after a level is finished.</p>
     *
     * @param listener A {@link LevelCompleteListener} that defines what happens when the user clicks
     *                 "Restart", "Exit", or "Next Level".
     * @throws RuntimeException If the FXML file cannot be loaded (an {@link IOException} occurs),
     *                          it prints the stack trace and the view may not be displayed correctly.
     */
    public void showLevelCompleteView(LevelCompleteListener listener) {
        try {
            FXMLLoader loader = new FXMLLoader(GameOverView.class.getResource("/fxml/level_complete.fxml"));
            Parent root = loader.load();

            levelCompleteStage = new Stage();
            levelCompleteStage.setTitle("Level Complete");
            levelCompleteStage.setScene(new Scene(root, 600, 400));
            levelCompleteStage.show();

            Text scoreText = (Text) root.lookup("#scoreText");
            if (scoreText != null) {
                // Retrieves the current score from GameModel (associated with the current GameView)
                int currentScore = GameModel.getInstance(GameView.getInstance().getGameRoot()).getCurrentScore();
                scoreText.setText("Score: " + currentScore);
            }

            Button restartButton = (Button) root.lookup("#restartButton");
            if (restartButton != null) {
                restartButton.setOnAction(event -> listener.onRestartClicked());
            }

            Button exitButton = (Button) root.lookup("#exitButton");
            if (exitButton != null) {
                exitButton.setOnAction(event -> listener.onExitClicked());
            }

            Button nextLevelButton = (Button) root.lookup("#nextLevelButton");
            if (nextLevelButton != null) {
                nextLevelButton.setOnAction(event -> listener.onNextLevel());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the level complete stage if it is currently open.
     * Once closed, calling {@link #showLevelCompleteView(LevelCompleteListener)} again
     * will create a new stage and display the level complete UI once more.
     */
    public void close() {
        if (levelCompleteStage != null) {
            levelCompleteStage.close();
        }
    }
}