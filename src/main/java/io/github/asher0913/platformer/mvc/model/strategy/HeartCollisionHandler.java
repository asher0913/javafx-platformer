package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Handles the collision logic when the player collects a heart.
 *
 * <p>The {@code HeartCollisionHandler} class implements the {@link CollisionHandler} interface
 * and defines the behavior when the player collects a heart entity. Specifically, the player's
 * health is increased, and the heart entity is removed from the game.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Increases the player's health by a fixed amount.</li>
 *   <li>Removes the heart entity from the game model and game root.</li>
 * </ul>
 *
 * @see CollisionHandler
 */
public class HeartCollisionHandler implements CollisionHandler {

    /**
     * Handles the collision between the player and a heart entity.
     *
     * @param player the player collecting the heart
     * @param model  the game model managing the game's state
     * @param entity the heart entity being collected
     */
    @Override
    public void handleCollision(Player player, GameModel model, GameEntity entity) {
        model.increaseHealth(10); // Increase player's health

        model.getHearts().remove(entity.getHitbox()); // Remove the heart's hitbox
        model.getGameRoot().getChildren().remove(entity.getEntity()); // Remove the heart from the game root
    }
}