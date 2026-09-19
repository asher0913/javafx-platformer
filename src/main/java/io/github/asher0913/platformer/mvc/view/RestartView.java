package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Represents the restart button view within the game's UI, allowing players to restart the game.
 *
 * <p>This class is responsible for creating and managing the restart button, including its position
 * and click action. It provides a customizable interface for defining the button's behavior when clicked.</p>
 *
 * <p>The {@code RestartView} is designed to be integrated into the game's UI layer, enabling seamless
 * interaction with the restart functionality.</p>
 */
public class RestartView {
    private final Button restartButton;

    /**
     * Constructs a new {@code RestartView} and adds the restart button to the specified UI root pane.
     *
     * @param uiRoot the root pane of the game's UI where the restart button will be added.
     */
    public RestartView(Pane uiRoot) {
        restartButton = new Button("Restart");
        restartButton.setTranslateX(10);
        restartButton.setTranslateY(50);
        uiRoot.getChildren().add(restartButton);
    }

    /**
     * Retrieves the restart button instance managed by this view.
     *
     * @return the {@link Button} representing the restart button.
     */
    public Button getRestartButton() {
        return restartButton;
    }

    /**
     * Sets the action to be executed when the restart button is clicked.
     *
     * @param action a {@link Runnable} representing the action to execute on button click.
     */
    public void setRestartAction(Runnable action) {
        restartButton.setOnAction(event -> action.run());
    }
}

