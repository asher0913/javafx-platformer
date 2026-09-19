package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Bullet;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Handles the collision logic when a bullet collides with the player.
 *
 * <p>The {@code BulletCollisionHandler} class implements the {@link CollisionHandler} interface
 * and defines the behavior that occurs when a bullet collides with the player. Specifically,
 * the player's health is reduced, and the bullet is removed from the game.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Reduces the player's health by a fixed amount when a bullet collides.</li>
 *   <li>Removes the bullet from the game model to prevent further interactions.</li>
 * </ul>
 *
 * @see CollisionHandler
 * @see Bullet
 */
public class BulletCollisionHandler implements CollisionHandler {

    /**
     * Handles the collision between a bullet and the player.
     *
     * @param player the player involved in the collision
     * @param model  the game model managing the game's state
     * @param entity the bullet entity causing the collision
     */
    @Override
    public void handleCollision(Player player, GameModel model, GameEntity entity) {
        if (entity instanceof Bullet bullet) {
            model.decreaseHealth(20); // Reduce player health
            bullet.removeBullet(); // Remove the bullet from the game
        }
    }
}