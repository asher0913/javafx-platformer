package io.github.asher0913.platformer.mvc.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Represents the visual component responsible for displaying the player's health.
 * The health is displayed as a label on the user interface (UI).
 *
 * <p>This class manages the health label's styling, positioning, and updates during the game.</p>
 */
public class HealthView {

    /**
     * The label displaying the player's health on the UI.
     */
    private final Label healthLabel;

    /**
     * Constructs a {@code HealthView} and initializes the health label with a default value of 100.
     * The label is styled and positioned on the UI and added to the specified {@code uiRoot}.
     *
     * @param uiRoot the {@link Pane} representing the root container for user interface elements.
     */
    public HealthView(Pane uiRoot) {
        healthLabel = new Label("Health: 100");
        healthLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");
        healthLabel.setTranslateX(600);
        healthLabel.setTranslateY(20);
        uiRoot.getChildren().add(healthLabel);
    }

    /**
     * Updates the displayed health value on the health label.
     *
     * <p>The label's text is dynamically updated to reflect the current health of the player.
     * For example, if the health is reduced to 80, the label will display "Health: 80".</p>
     *
     * @param health the current health value of the player.
     */
    public void updateHealth(int health) {
        healthLabel.setText("Health: " + health);
    }
}
