package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Represents the score display view within the game's UI, showing the player's current score.
 *
 * <p>This class is responsible for creating and managing the score label, including its position,
 * styling, and updating mechanism. It provides an interface for dynamically updating the displayed score.</p>
 *
 * <p>The {@code ScoreView} is designed to be integrated into the game's UI layer, allowing real-time
 * score updates to enhance the player's experience.</p>
 */
public class ScoreView {
    private final Label scoreLabel;

    /**
     * Constructs a new {@code ScoreView} and adds the score label to the specified UI root pane.
     *
     * @param uiRoot the root pane of the game's UI where the score label will be added.
     */
    public ScoreView(Pane uiRoot) {
        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");
        scoreLabel.setTranslateX(600);
        scoreLabel.setTranslateY(50);
        uiRoot.getChildren().add(scoreLabel);
    }

    /**
     * Updates the score label to display the current score.
     *
     * @param score the current score to be displayed.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}