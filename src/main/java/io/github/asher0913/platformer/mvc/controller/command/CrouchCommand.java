package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Causes the player to enter a crouching state.
 * <p>
 * When executed, this command adjusts the player's hitbox and visuals, lowering the player's height.
 * This can be useful for passing under low obstacles or hiding behind cover.
 * </p>
 */
public class CrouchCommand implements Command {
    /**
     * Instructs the player to crouch.
     *
     * @param player the player to modify
     * @param model  the current game model (not used directly by this command)
     */
    @Override
    public void execute(Player player, GameModel model) {
        player.crouch();
    }
}