package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Represents the pause button view within the game's UI, allowing players to pause and resume gameplay.
 *
 * <p>This class is responsible for creating and managing the pause button, including its
 * position, text, and interaction behavior. It provides a customizable interface for defining
 * the button's action when clicked.</p>
 *
 * <p>The {@code PauseView} is designed to be integrated into the game's UI layer, and its
 * behavior can be updated dynamically to reflect the current pause state.</p>
 */
public class PauseView {
    private final Button pauseButton;

    /**
     * Constructs a new {@code PauseView} and adds the pause button to the specified UI root pane.
     *
     * @param uiRoot the root pane of the game's UI where the pause button will be added.
     */
    public PauseView(Pane uiRoot) {
        pauseButton = new Button("Pause");
        pauseButton.setTranslateX(10);
        pauseButton.setTranslateY(90);
        uiRoot.getChildren().add(pauseButton);
    }

    /**
     * Retrieves the pause button instance managed by this view.
     *
     * @return the {@link Button} representing the pause button.
     */
    public Button getPauseButton() {
        return pauseButton;
    }

    /**
     * Sets the action to be executed when the pause button is clicked.
     *
     * @param action a {@link Runnable} representing the action to execute on button click.
     */
    public void setPauseAction(Runnable action) {
        pauseButton.setOnAction(event -> action.run());
    }

    /**
     * Updates the text displayed on the pause button based on the current pause state.
     *
     * @param isPaused a boolean indicating whether the game is currently paused.
     *                 If {@code true}, the button text is set to "Resume"; otherwise, it is set to "Pause".
     */
    public void updatePauseButtonText(boolean isPaused) {
        pauseButton.setText(isPaused ? "Resume" : "Pause");
    }
}
