package io.github.asher0913.platformer.mvc.controller.state;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.GameEndView;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the "Game End" state of the game.
 * <p>
 * This class manages the transition to the game-end screen and handles high scores,
 * player actions, and navigation after the game ends. It implements the {@link GameState}
 * and {@link GameEndListener} interfaces to integrate with the game state machine
 * and handle user interactions.
 * </p>
 * <p>
 * The game-end screen displays the player's total score and the top 10 high scores
 * recorded from previous gameplay sessions. High scores are persisted in a local
 * file and updated dynamically when the player achieves a new score.
 * </p>
 *
 * @see GameEndView
 * @see GameModel
 */
public class GameEndState implements GameState, GameEndListener {
    private static GameEndState instance;
    private final Pane appRoot;

    // File path to store high scores
    private static final String SCORE_FILE = "src/main/resources/scores.txt";

    /**
     * Private constructor for creating the singleton instance.
     * @param appRoot      The root pane for the application's layout.
     */
    private GameEndState(Pane appRoot) {
        this.appRoot = appRoot;
    }

    /**
     * Retrieves the singleton instance of the {@code GameEndState}.
     * If the instance does not exist, it creates one.
     * @param appRoot      The root pane for the application's layout.
     * @return The singleton instance of the {@code GameEndState}.
     */
    public static GameEndState getInstance(Pane appRoot) {
        if (instance == null) {
            instance = new GameEndState(appRoot);
        }
        return instance;
    }

    /**
     * Enters the "Game End" state and displays the game-end screen.
     * <p>
     * This method calculates the player's total score, updates the high scores,
     * and displays them on the game-end screen.
     * </p>
     */
    @Override
    public void enterState() {
        // Update total score
        GameModel.getInstance().increaseTotalScore(GameModel.getInstance().getCurrentScore());
        int totalScore = GameController.getInstance().getModel().getTotalScore();

        // Retrieve and update high scores
        List<Integer> highScores = getHighScores();
        highScores.add(totalScore);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10);
        }
        saveHighScores(highScores);

        // Show game end view
        GameEndView.getInstance().showGameEndView(this, totalScore, highScores);
    }

    /**
     * Exits the "Game End" state and cleans up the view and game data.
     * <p>
     * This method closes the game-end screen and clears the key input map.
     * </p>
     */
    @Override
    public void exitState() {
        GameEndView.getInstance().close();
        GameController.getInstance().getKeys().clear();
    }

    /**
     * Handles the action when the "Exit" button is clicked on the game-end screen.
     * <p>
     * This method navigates the user back to the main menu.
     * </p>
     */
    @Override
    public void onExitClicked() {
        GameEndView.getInstance().close();
        GameController.getInstance().getBackController().backGame();
    }

    /**
     * Retrieves the list of high scores from the local file.
     * <p>
     * This method reads high scores from the specified file and returns them as a list.
     * If the file does not exist or is unreadable, an empty list is returned.
     * </p>
     *
     * @return A list of high scores in descending order.
     */
    private List<Integer> getHighScores() {
        List<Integer> scores = new ArrayList<>();
        File file = new File(SCORE_FILE);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    scores.add(Integer.parseInt(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return scores;
    }

    /**
     * Saves the updated list of high scores to the local file.
     * <p>
     * This method writes the high scores to the specified file, overwriting the existing data.
     * </p>
     *
     * @param scores A list of high scores to save.
     */
    private void saveHighScores(List<Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (int score : scores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
