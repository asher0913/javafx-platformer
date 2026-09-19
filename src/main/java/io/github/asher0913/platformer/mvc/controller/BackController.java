package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.controller.state.MainMenuState;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.BackView;
import javafx.scene.layout.Pane;

/**
 * Controls the behavior of the "Back" button, allowing the player to return
 * to the main menu from within the game.
 *
 * <p>This class integrates the {@link BackView} for the UI representation
 * and associates the back action with clearing the current game state and transitioning
 * back to the main menu. It ensures that the game stops cleanly and resets the necessary
 * elements when the back button is activated.</p>
 */
public class BackController {
    /**
     * The view component representing the "Back" button.
     */
    private final BackView backView;

    /**
     * The game model associated with the current game state.
     */
    private final GameModel model;

    /**
     * The game controller managing the current game logic.
     */
    private final GameController gameController;

    /**
     * Constructs a {@code BackController} instance and initializes the back button action.
     *
     * @param uiRoot        The root pane where the back button is displayed.
     * @param model         The game model representing the state of the game.
     * @param gameController The controller managing the game loop and player interactions.
     */
    public BackController(Pane uiRoot, GameModel model, GameController gameController) {
        backView = new BackView(uiRoot);
        this.model = model;
        this.gameController = gameController;
        setupBackAction();
    }

    /**
     * Configures the action to be performed when the back button is clicked.
     */
    private void setupBackAction() {
        backView.setBackAction(this::backGame);
    }

    /**
     * Handles the back button action. Stops the game loop, clears the game state,
     * resets necessary elements, and transitions to the main menu.
     */
    public void backGame() {
        gameController.getTimer().stop();
        gameController.getCountDownTimerController().stopCountdown();
        gameController.getKeys().clear();
        model.getGameRoot().getChildren().clear();
        model.getPlayer().resetPlayerState();
        model.stopAllMonsterShooting();
        StateController.getInstance().setState(MainMenuState.getInstance());
    }
}