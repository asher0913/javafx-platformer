package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;

/**
 * Represents an executable command action that can be performed on the player within the game's world.
 * <p>
 * Implementations of this interface define discrete player actions such as moving, jumping, or crouching.
 * Calling {@link #execute(Player, GameModel)} applies the corresponding behavior to the player, possibly
 * modifying the player's state or interacting with the game model.
 * </p>
 * <p>
 * This approach decouples input handling from the specific player actions, enabling flexible bindings
 * of commands to input events.
 * </p>
 */
public interface Command {

    /**
     * Executes the defined action on the given player, optionally using information from the game model.
     *
     * @param player the player on whom the action is performed
     * @param model  the current game model providing environmental context
     */
    void execute(Player player, GameModel model);
}