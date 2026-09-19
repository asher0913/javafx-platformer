package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.strategy.CollisionHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Represents a generic game entity with a hitbox and visual representation.
 *
 * <p>The {@code GameEntity} class serves as a base for all game objects within the platformer,
 * such as {@link Player}, {@link Spike}, {@link Coin}, and other entities. Each entity includes
 * a hitbox for collision detection, a visual component (e.g., images or shapes), and optional
 * collision handling behavior.</p>
 *
 * <p>This class also provides utility methods for collision detection, position management,
 * and rendering in the game world.</p>
 *
 * @see CollisionHandler
 * @see GameModel
 */
public abstract class GameEntity {
    /**
     * The visual representation of the entity, containing its components.
     */
    protected Pane entity;

    /**
     * The hitbox used for collision detection.
     */
    protected Rectangle hitbox;

    /**
     * The collision handler defining the entity's behavior when a collision occurs.
     */
    protected CollisionHandler collisionHandler;

    /**
     * Constructs a new {@code GameEntity} with the specified position and dimensions.
     *
     * @param x      The x-coordinate of the entity's initial position.
     * @param y      The y-coordinate of the entity's initial position.
     * @param width  The width of the entity.
     * @param height The height of the entity.
     */
    public GameEntity(double x, double y, double width, double height) {
        // Initialize the hitbox for collision detection.
        hitbox = new Rectangle(width, height);
        hitbox.setTranslateX(x);
        hitbox.setTranslateY(y);
        hitbox.setVisible(false);

        // Initialize the visual entity.
        entity = new Pane();
        entity.setPrefSize(width, height);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.getChildren().add(hitbox);
    }

    /**
     * Returns the visual representation of the entity.
     *
     * @return The {@link Pane} containing the entity's components.
     */
    public Pane getEntity() {
        return entity;
    }

    /**
     * Returns the hitbox of the entity used for collision detection.
     *
     * @return The {@link Rectangle} representing the entity's hitbox.
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Returns the current x-coordinate of the entity.
     *
     * @return The x-coordinate of the entity.
     */
    public double getX() {
        return hitbox.getTranslateX();
    }

    /**
     * Returns the current y-coordinate of the entity.
     *
     * @return The y-coordinate of the entity.
     */
    public double getY() {
        return hitbox.getTranslateY();
    }

    /**
     * Sets the position of the entity in the game world.
     *
     * @param x The new x-coordinate for the entity.
     * @param y The new y-coordinate for the entity.
     */
    public void setPosition(double x, double y) {
        hitbox.setTranslateX(x);
        hitbox.setTranslateY(y);

        entity.setTranslateX(x);
        entity.setTranslateY(y);
    }

    /**
     * Checks if this entity intersects with another entity.
     *
     * @param other The {@link GameEntity} to check for an intersection with.
     * @return {@code true} if the two entities intersect, {@code false} otherwise.
     */
    public boolean intersects(GameEntity other) {
        return hitbox.getBoundsInParent().intersects(other.getHitbox().getBoundsInParent());
    }

    /**
     * Checks for a collision with the player and delegates handling to the entity's collision handler.
     *
     * @param player The {@link Player} instance to check for collisions with.
     * @param model  The {@link GameModel} representing the game's current state.
     */
    public void checkCollision(Player player, GameModel model) {
        if (intersects(player)) {
            if (collisionHandler != null) {
                collisionHandler.handleCollision(player, model, this);
            } else {
                System.err.println("Warning: collisionHandler is not set for " + this.getClass().getSimpleName());
            }
        }
    }
}