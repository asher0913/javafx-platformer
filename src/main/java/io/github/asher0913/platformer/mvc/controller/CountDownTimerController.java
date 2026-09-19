package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.controller.state.GameOverState;
import io.github.asher0913.platformer.mvc.view.CountDownTimerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controls the countdown timer logic for the game. The timer tracks the remaining time
 * for a specific game phase and triggers an event when the time is up.
 *
 * <p>This controller manages interactions with the {@link CountDownTimerView} to update
 * the UI and integrates with the {@link GameController} to handle game logic when the timer ends.</p>
 *
 * <p>It uses a {@link Timeline} for the countdown and allows operations like starting,
 * stopping, and continuing the countdown.</p>
 */
public class CountDownTimerController {
    private final int timeDuration = 30;
    private final GameController gameController;
    private int remainingTime;
    private final CountDownTimerView timerView;
    private Runnable onTimeUp;
    private Timeline timeline;

    /**
     * Constructs a new {@code CountDownTimerController} with the specified UI root and game controller.
     *
     * @param uiRoot        the root pane of the game's UI where the timer label is displayed
     * @param gameController the game controller managing game state
     */
    public CountDownTimerController(Pane uiRoot, GameController gameController) {
        remainingTime = timeDuration;
        timerView = new CountDownTimerView(uiRoot);
        this.gameController = gameController;
        updateTimerLabel();
    }

    /**
     * Updates the timer label in the UI to reflect the remaining time.
     * Ensures the operation is run on the JavaFX application thread.
     */
    private void updateTimerLabel() {
        Platform.runLater(() -> timerView.updateTimerLabel(remainingTime));
    }

    /**
     * Starts the countdown timer. Resets the remaining time and initializes the {@link Timeline}.
     * The timer decreases the remaining time by 1 second per cycle until it reaches 0.
     * When time is up, the {@code handleTimeUp} method is invoked.
     */
    public void startCountdown() {
        if (timeline != null) {
            timeline.stop();
        }
        remainingTime = timeDuration;
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingTime--;
                    updateTimerLabel();
                    if (remainingTime <= 0) {
                        stopCountdown();
                        handleTimeUp();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Stops the countdown timer. Pauses the {@link Timeline} to halt further time updates.
     */
    public void stopCountdown() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Continues the countdown timer from the paused state.
     * Resumes the {@link Timeline} to update the time.
     */
    public void continueCountdown() {
        if (timeline != null) {
            timeline.play();
        }
    }

    /**
     * Handles the event when the timer reaches 0.
     * Transitions the game to the "Game Over" state using {@link GameOverState}.
     */
    private void handleTimeUp() {
        Platform.runLater(() -> StateController.getInstance().setState(GameOverState.getInstance()));
    }
}