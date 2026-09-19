package io.github.asher0913.platformer.mvc.model.factory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a platform entity in the game.
 * <p>
 * A {@code Platform} is a stationary {@link GameEntity} that provides support for the player to stand on or navigate.
 * It is visually represented by an image of a platform.
 * </p>
 */
public class Platform extends GameEntity {

    /**
     * Constructs a new {@code Platform} object at the specified position and size.
     *
     * @param x      the x-coordinate of the platform's position
     * @param y      the y-coordinate of the platform's position
     * @param width  the width of the platform
     * @param height the height of the platform
     * @throws IllegalArgumentException if the platform image resource is not found
     */
    public Platform(double x, double y, double width, double height) {
        super(x, y, width, height);

        Image image = new Image(getClass().getResourceAsStream("/Images/platform.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/platform.png");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);

        entity.getChildren().add(imageView);
    }
}