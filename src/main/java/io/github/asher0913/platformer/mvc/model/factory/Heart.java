package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.strategy.HeartCollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a collectible health item in the game that restores the player's health upon collision.
 *
 * <p>The {@code Heart} class extends {@link GameEntity} and is a collectible item designed to
 * restore health to the player. The class uses the {@link HeartCollisionHandler} strategy to define
 * specific collision behavior when interacting with the player.</p>
 *
 * <p>The heart is visually represented by an image loaded from the resources folder. If the
 * image cannot be located, an {@link IllegalArgumentException} is thrown.</p>
 *
 * @see GameEntity
 * @see HeartCollisionHandler
 */
public class Heart extends GameEntity {

    /**
     * Constructs a new {@code Heart} entity at the specified position with the given dimensions.
     *
     * <p>The heart is visually represented by an image and uses the {@link HeartCollisionHandler}
     * to define its interaction with the player. If the image cannot be loaded, an
     * {@link IllegalArgumentException} is thrown.</p>
     *
     * @param x      The x-coordinate of the heart's position.
     * @param y      The y-coordinate of the heart's position.
     * @param width  The width of the heart.
     * @param height The height of the heart.
     * @throws IllegalArgumentException if the heart image cannot be loaded.
     */
    public Heart(double x, double y, double width, double height) {
        super(x, y, width, height);

        // Assign collision behavior via strategy pattern.
        collisionHandler = new HeartCollisionHandler();

        // Load and set the visual representation of the heart.
        Image image = new Image(getClass().getResourceAsStream("/Images/heart.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/heart.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        entity.getChildren().add(imageView);
    }
}