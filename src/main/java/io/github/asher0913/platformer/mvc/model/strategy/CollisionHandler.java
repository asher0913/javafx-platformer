package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Represents a strategy for handling collisions between game entities.
 *
 * <p>The {@code CollisionHandler} interface defines a single method, {@link #handleCollision(Player, GameModel, GameEntity)},
 * that specifies the behavior to execute when a specific type of game entity collides with the player.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Defines collision logic for specific game entities (e.g., coins, bullets).</li>
 *   <li>Supports the Strategy design pattern to allow interchangeable collision handling behaviors.</li>
 * </ul>
 *
 * <b>Usage:</b>
 * <ul>
 *   <li>Create a class that implements {@code CollisionHandler} to define the collision behavior for a specific entity type.</li>
 *   <li>Assign the collision handler to the entity using the {@code GameEntity} class's collision handling property.</li>
 * </ul>
 *
 * @see GameEntity
 */
public interface CollisionHandler {

    /**
     * Handles the collision between a game entity and the player.
     *
     * @param player the player involved in the collision
     * @param model  the game model managing the game's state
     * @param entity the game entity colliding with the player
     */
    void handleCollision(Player player, GameModel model, GameEntity entity);
}