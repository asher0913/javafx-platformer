package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Represents the "Back" button view in the game's user interface.
 * <p>
 * The {@code BackView} class creates and manages a button labeled "Back" that allows users to navigate
 * back to the main menu or other screens.
 * </p>
 */
public class BackView {
    private final Button backButton;

    /**
     * Constructs a new {@code BackView} and adds the "Back" button to the specified user interface root pane.
     *
     * @param uiRoot the parent pane to which the "Back" button is added
     */
    public BackView(Pane uiRoot) {
        backButton = new Button("Back");
        backButton.setTranslateX(10);
        backButton.setTranslateY(10);
        uiRoot.getChildren().add(backButton);
    }

    /**
     * Returns the {@link Button} instance representing the "Back" button.
     *
     * @return the "Back" button
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Sets the action to be performed when the "Back" button is clicked.
     *
     * @param action a {@link Runnable} defining the action to execute on button click
     */
    public void setBackAction(Runnable action) {
        backButton.setOnAction(event -> action.run());
    }
}