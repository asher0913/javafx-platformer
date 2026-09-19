package io.github.asher0913.platformer.mvc.controller;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.view.HealthView;
import javafx.scene.layout.Pane;

/**
 * Handles the logic for updating and displaying the player's health in the game.
 * <p>
 * The {@code HealthController} interacts with the {@link GameModel} to fetch the player's current health
 * and updates the {@link HealthView} to reflect changes in the UI. It ensures that the health display is
 * always synchronized with the game state.
 */
public class HealthController {
    private final GameModel model;
    private final HealthView healthView;

    /**
     * Constructs a new {@code HealthController} with the given model and UI root.
     *
     * @param model  the {@link GameModel} instance containing the player's health data
     * @param uiRoot the root {@link Pane} where the health view will be displayed
     */
    public HealthController(GameModel model, Pane uiRoot) {
        this.model = model;
        this.healthView = new HealthView(uiRoot);
    }

    /**
     * Updates the health display in the {@link HealthView}.
     * <p>
     * Fetches the current health value from the {@link GameModel} and updates the health label in the UI.
     */
    public void updateHealthDisplay() {
        healthView.updateHealth(model.getPlayerHealth());
    }
}