package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Moves the player horizontally to the left if space allows.
 * <p>
 * This command attempts to shift the player along the X-axis by a fixed amount (-5), checking for
 * platform collisions. If a collision is detected at any step, the player stops moving, ensuring
 * realistic movement within the level.
 * </p>
 */
public class MoveXCommand implements Command {
    /**
     * Moves the player horizontally by a small negative increment, simulating leftward movement.
     * The command checks for collisions with platforms before each step of the movement.
     *
     * @param player the player to move
     * @param model  provides platform data for collision checking
     */
    @Override
    public void execute(Player player, GameModel model) {
        if (player.getHitbox().getTranslateX() >= 5) {
            player.moveX(-5, model.getPlatforms());
        }
    }
}