package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Handles the collision logic when a coin is collected by the player.
 *
 * <p>The {@code CoinCollisionHandler} class implements the {@link CollisionHandler} interface
 * and defines the behavior when a coin is collected. Specifically, the player's score is increased,
 * and the coin is removed from the game.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Increases the player's current and total scores upon coin collection.</li>
 *   <li>Removes the coin from the game model and the game root.</li>
 * </ul>
 *
 * @see CollisionHandler
 */
public class CoinCollisionHandler implements CollisionHandler {

    /**
     * Handles the collision between a player and a coin.
     *
     * @param player the player collecting the coin
     * @param model  the game model managing the game's state
     * @param entity the coin entity being collected
     */
    @Override
    public void handleCollision(Player player, GameModel model, GameEntity entity) {
        model.increaseCurrentScore(10); // Increase the current score

        model.getCoins().remove(entity.getHitbox()); // Remove the coin hitbox
        model.getGameRoot().getChildren().remove(entity.getEntity()); // Remove the coin from the game root
    }
}