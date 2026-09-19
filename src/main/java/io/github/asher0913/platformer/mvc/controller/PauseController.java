package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.view.PauseView;
import javafx.scene.layout.Pane;

/**
 * The {@code PauseController} class manages the game's pause and resume functionality,
 * serving as a bridge between the game logic and the user interface.
 *
 * <p>This class:
 * <ul>
 *   <li>Handles the initialization and configuration of the pause button action via the {@link PauseView}.</li>
 *   <li>Interacts with the {@link GameController} to control the game's paused or running state.</li>
 *   <li>Ensures the pause button text reflects the current game state (e.g., "Pause" or "Resume").</li>
 * </ul>
 *
 * <p>This controller adheres to the MVC architecture by acting as the mediator between the game logic
 * and the UI components, ensuring separation of concerns and maintainability.
 *
 * @see GameController
 * @see PauseView
 */
public class PauseController {
    private final PauseView pauseView;
    private final GameController gameController;

    /**
     * Constructs a new {@code PauseController} with the given UI root and game controller.
     *
     * @param uiRoot        the root {@link Pane} where the pause button will be displayed
     * @param gameController the {@link GameController} instance managing the game loop and state
     */
    public PauseController(Pane uiRoot, GameController gameController) {
        pauseView = new PauseView(uiRoot);
        this.gameController = gameController;
        setupPauseAction();
    }

    /**
     * Sets up the pause button action in the {@link PauseView}.
     * <p>
     * Toggles the game's paused state when the button is clicked and updates the button's text accordingly.
     */
    private void setupPauseAction() {
        pauseView.setPauseAction(() -> {
            if (gameController.isPaused()) {
                resumeGame();
                pauseView.updatePauseButtonText(false);
            } else {
                pauseGame();
                pauseView.updatePauseButtonText(true);
            }
        });
    }

    /**
     * Pauses the game by stopping the game loop and countdown timer.
     * <p>
     * This method sets the game controller's paused state to true and halts any active animations or timers.
     */
    public void pauseGame() {
        gameController.setPaused(true);
        if (gameController.getTimer() != null) {
            gameController.getTimer().stop();
            gameController.getCountDownTimerController().stopCountdown();
        }
    }

    /**
     * Resumes the game by restarting the game loop and countdown timer.
     * <p>
     * This method sets the game controller's paused state to false and resumes any previously halted animations or timers.
     */
    public void resumeGame() {
        gameController.setPaused(false);
        if (gameController.getTimer() != null) {
            gameController.getTimer().start();
            gameController.getCountDownTimerController().continueCountdown();
        }
    }
}