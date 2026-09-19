package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.ScoreView;
import javafx.scene.layout.Pane;

/**
 * Controls the logic for managing and displaying the player's score during the game.
 * Connects the {@link GameModel}, which holds the current score, to the {@link ScoreView},
 * which visually represents the score to the player.
 *
 * <p>This class follows the MVC architecture by acting as the intermediary between the
 * model (data) and the view (UI).</p>
 */
public class ScoreController {

    /**
     * The game model that holds the current game state, including the player's score.
     */
    private final GameModel model;

    /**
     * The view responsible for displaying the player's score.
     */
    private final ScoreView scoreView;

    /**
     * Constructs a {@code ScoreController} that connects the provided model and view.
     *
     * @param model  the {@link GameModel} that contains the score data
     * @param uiRoot the root pane of the UI where the score view will be added
     */
    public ScoreController(GameModel model, Pane uiRoot) {
        this.model = model;
        this.scoreView = new ScoreView(uiRoot);
    }

    /**
     * Updates the score display in the {@link ScoreView}.
     * <p>This method retrieves the current score from the {@link GameModel} and passes it to the {@link ScoreView}
     * to refresh the UI.</p>
     */
    public void updateScoreDisplay() {
        scoreView.updateScore(model.getCurrentScore());
    }
}