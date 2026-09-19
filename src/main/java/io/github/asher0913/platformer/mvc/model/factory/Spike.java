package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.strategy.SpikeCollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Spike entity in the game.
 *
 * <p>The {@code Spike} class extends {@link GameEntity} and represents an obstacle in the game
 * that can interact with the player. When the player collides with a spike, it triggers
 * specific collision logic defined in the {@link SpikeCollisionHandler}.</p>
 *
 * <p>Spikes are visually represented using an image loaded from the game resources.
 * Each spike is associated with a "triggered" state that indicates whether the spike
 * has been activated (e.g., when the player collides with it).</p>
 *
 * <p>This class is part of the entity system, where game objects are instantiated and
 * configured dynamically based on level data. The behavior of the spike is delegated
 * to its associated collision handler, making it easy to extend or modify the collision logic.</p>
 *
 * @see GameEntity
 * @see SpikeCollisionHandler
 */
public class Spike extends GameEntity {
    private boolean triggered = false; // Indicates whether the spike is activated or "triggered"

    /**
     * Constructs a new {@code Spike} object at the specified position with the given dimensions.
     *
     * <p>This constructor initializes the spike's visual representation and assigns its
     * collision handler. The spike image is loaded from the resources folder, and an
     * {@link IllegalArgumentException} is thrown if the image cannot be found.</p>
     *
     * @param x      The x-coordinate of the spike's position.
     * @param y      The y-coordinate of the spike's position.
     * @param width  The width of the spike.
     * @param height The height of the spike.
     * @throws IllegalArgumentException if the spike image cannot be loaded.
     */
    public Spike(double x, double y, double width, double height) {
        super(x, y, width, height);

        // Assign the collision handler specific to spikes
        collisionHandler = new SpikeCollisionHandler();

        // Load the spike image from the resources folder
        Image image = new Image(getClass().getResourceAsStream("/Images/spike.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/spike.png");
        }

        // Configure the image view for the spike
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        // Add the spike's image to its entity container
        entity.getChildren().add(imageView);
    }

    /**
     * Checks whether the spike is currently triggered.
     *
     * <p>This state is used to determine whether the spike has been activated,
     * for example, due to a collision with the player.</p>
     *
     * @return {@code true} if the spike is triggered, {@code false} otherwise.
     */
    public boolean isTriggered() {
        return triggered;
    }

    /**
     * Updates the triggered state of the spike.
     *
     * <p>This method allows the game's logic to mark the spike as activated
     * or deactivated based on interactions with the player or other entities.</p>
     *
     * @param triggered {@code true} to mark the spike as triggered, {@code false} otherwise.
     */
    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}