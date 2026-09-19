package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.strategy.GoalCollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the goal in the game that marks the end of a level.
 *
 * <p>The {@code Goal} class extends {@link GameEntity} and acts as the final target for the player
 * to complete a level. Upon collision with the player, specific behavior is executed, such as
 * transitioning to the next level or displaying a level complete screen.</p>
 *
 * <p>The class utilizes the {@link GoalCollisionHandler} to define collision behavior.
 * It is visually represented using an image loaded from the resources folder. If the image
 * cannot be found, an {@link IllegalArgumentException} is thrown.</p>
 *
 * @see GameEntity
 * @see GoalCollisionHandler
 */
public class Goal extends GameEntity {

    /**
     * Constructs a new {@code Goal} entity at the specified position with the given dimensions.
     *
     * <p>The goal is visually represented by an image and uses the {@link GoalCollisionHandler}
     * to define its interaction with the player. If the image cannot be loaded, an
     * {@link IllegalArgumentException} is thrown.</p>
     *
     * @param x      The x-coordinate of the goal's position.
     * @param y      The y-coordinate of the goal's position.
     * @param width  The width of the goal.
     * @param height The height of the goal.
     * @throws IllegalArgumentException if the goal image cannot be loaded.
     */
    public Goal(double x, double y, double width, double height) {
        super(x, y, width, height);

        // Assign collision behavior via strategy pattern.
        collisionHandler = new GoalCollisionHandler();

        // Load and set the visual representation of the goal.
        Image image = new Image(getClass().getResourceAsStream("/Images/goal.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/goal.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        entity.getChildren().add(imageView);
    }
}