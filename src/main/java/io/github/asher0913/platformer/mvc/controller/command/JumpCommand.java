package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Initiates a jump action for the player if conditions allow.
 * <p>
 * This command checks the player's vertical position to ensure jumping is possible. If allowed,
 * it applies an upward velocity to the player, enabling them to leave the ground.
 * </p>
 */
public class JumpCommand implements Command {
    /**
     * Makes the player jump if the player's vertical position permits it.
     *
     * @param player the player attempting to jump
     * @param model  the current game model (not used directly by this command)
     */
    @Override
    public void execute(Player player, GameModel model) {
        if (player.getHitbox().getTranslateY() >= 5) {
            player.jump();
        }
    }
}