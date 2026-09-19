package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Represents the countdown timer view in the game's user interface.
 * <p>
 * The {@code CountDownTimerView} class displays the remaining time in a game level. It provides methods to
 * update the timer label dynamically.
 * </p>
 */
public class CountDownTimerView {
    private final Label timerLabel;

    /**
     * Constructs a new {@code CountDownTimerView} and adds the timer label to the specified user interface root pane.
     *
     * @param uiRoot the parent pane to which the timer label is added
     */
    public CountDownTimerView(Pane uiRoot) {
        timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: blue;");
        timerLabel.setTranslateX(300);
        timerLabel.setTranslateY(20);
        uiRoot.getChildren().add(timerLabel);
    }

    /**
     * Returns the {@link Label} instance used to display the countdown timer.
     *
     * @return the timer label
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * Updates the text of the timer label to show the remaining time.
     *
     * @param remainingTime the remaining time to display, in seconds
     */
    public void updateTimerLabel(int remainingTime) {
        timerLabel.setText("Time: " + remainingTime);
    }
}