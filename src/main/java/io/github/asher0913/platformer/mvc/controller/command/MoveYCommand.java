package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Moves the player horizontally to the right if space allows.
 * <p>
 * This command attempts to shift the player along the X-axis by a fixed amount (5), similar to
 * {@link MoveXCommand} but in the opposite direction. It checks for collisions to ensure the player
 * does not pass through platforms or other solid objects.
 * </p>
 */
public class MoveYCommand implements Command {
    /**
     * Moves the player horizontally by a small positive increment, simulating rightward movement.
     * The command checks platform collisions before each incremental move.
     *
     * @param player the player to move
     * @param model  the current game model, used to retrieve platforms for collision detection
     */
    @Override
    public void execute(Player player, GameModel model) {
        if (player.getHitbox().getTranslateX() + player.getHitbox().getWidth() <= model.getLevelWidth() - 5) {
            player.moveX(5, model.getPlatforms());
        }
    }
}