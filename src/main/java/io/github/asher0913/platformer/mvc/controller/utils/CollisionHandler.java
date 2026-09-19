package io.github.asher0913.platformer.mvc.controller.utils;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>CollisionHandler</code> class processes collisions between the player and various
 * interactive game entities, such as coins, hearts, spikes, goals, and bullets.
 * <p>
 * By iterating through each category of entity hitboxes (retrieved from the {@link GameModel}),
 * this handler checks if the player intersects with these entities. If so, the corresponding
 * entity's collision logic is invoked, enabling effects like increasing score, restoring health,
 * dealing damage, or triggering level completion.
 * </p>
 * <p>
 * This approach centralizes collision checks, making it easier to maintain and update collision
 * logic as the game expands with more entity types.
 * </p>
 */
public class CollisionHandler {

    /**
     * Processes collisions between the player and all relevant entity types managed by the game model.
     * <p>
     * This method:
     * <ul>
     *   <li>Retrieves lists of coins, hearts, spikes, goals, and bullets from the model.</li>
     *   <li>Checks each entity list for collisions with the player.</li>
     *   <li>Invokes collision logic (e.g., increase score, damage player) via {@link GameEntity#checkCollision(Player, GameModel)}.</li>
     * </ul>
     *
     * @param model  the current {@link GameModel}, providing entity data
     * @param player the player's character to check for collisions
     */
    public void handleCollisions(GameModel model, Player player) {
        handleEntityCollisions(model.getCoins(), model, player);
        handleEntityCollisions(model.getHearts(), model, player);
        handleEntityCollisions(model.getSpikes(), model, player);
        handleEntityCollisions(model.getGoals(), model, player);
        handleEntityCollisions(model.getBullets(), model, player);
    }

    /**
     * Checks collisions for a specific list of entity hitboxes and triggers the entity's collision logic if intersected.
     *
     * @param hitboxes a list of entity hitboxes (rectangles)
     * @param model    the current game model
     * @param player   the player character
     */
    private void handleEntityCollisions(List<Rectangle> hitboxes, GameModel model, Player player) {
        List<Rectangle> copy = new ArrayList<>(hitboxes);
        for (Rectangle hitbox : copy) {
            GameEntity entity = model.getEntityFromHitbox(hitbox);
            if (entity != null) {
                entity.checkCollision(player, model);
            }
        }
    }
}