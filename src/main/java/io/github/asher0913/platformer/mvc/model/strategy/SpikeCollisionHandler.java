package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import io.github.asher0913.platformer.mvc.model.factory.Spike;

/**
 * Handles the collision logic when the player collides with a spike.
 *
 * <p>The {@code SpikeCollisionHandler} class implements the {@link CollisionHandler} interface
 * and defines the behavior when the player collides with a spike entity. Specifically, the player's
 * health is reduced, and the spike is flagged as triggered to prevent repeated damage.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Reduces the player's health by a fixed amount upon collision.</li>
 *   <li>Flags the spike entity as triggered to prevent further collisions from causing damage.</li>
 * </ul>
 *
 * @see CollisionHandler
 * @see Spike
 */
public class SpikeCollisionHandler implements CollisionHandler {

    /**
     * Handles the collision between the player and a spike entity.
     *
     * @param player the player colliding with the spike
     * @param model  the game model managing the game's state
     * @param entity the spike entity
     */
    @Override
    public void handleCollision(Player player, GameModel model, GameEntity entity) {
        if (entity instanceof Spike spike && !spike.isTriggered()) {
            model.decreaseHealth(10); // Decrease player's health
            spike.setTriggered(true); // Flag the spike as triggered
        }
    }
}