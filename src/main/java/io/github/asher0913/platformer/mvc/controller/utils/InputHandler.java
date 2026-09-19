package io.github.asher0913.platformer.mvc.controller.utils;

import io.github.asher0913.platformer.mvc.controller.command.Command;
import io.github.asher0913.platformer.mvc.controller.command.CrouchCommand;
import io.github.asher0913.platformer.mvc.controller.command.JumpCommand;
import io.github.asher0913.platformer.mvc.controller.command.MoveXCommand;
import io.github.asher0913.platformer.mvc.controller.command.MoveYCommand;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

/**
 * The <code>InputHandler</code> class binds keyboard inputs to player commands and executes them as needed.
 * <p>
 * This decouples input logic from direct player manipulation. The handler checks a map of key states and,
 * for each pressed key, runs the corresponding command on the player. It also handles the transition out
 * of crouching when the crouch key is released.
 * </p>
 *
 * <p>By using commands, changes to input mappings or player actions can be made without modifying the
 * player's core logic.</p>
 *
 * <table border="1">
 * <caption>Default Key Bindings</caption>
 * <tr><th>Key</th><th>Default Command</th></tr>
 * <tr><td>W</td><td>{@link JumpCommand}</td></tr>
 * <tr><td>A</td><td>{@link MoveXCommand} (moves left)</td></tr>
 * <tr><td>D</td><td>{@link MoveYCommand} (moves right)</td></tr>
 * <tr><td>S</td><td>{@link CrouchCommand}</td></tr>
 * </table>
 */
public class InputHandler {
    private final HashMap<KeyCode, Command> keyBindings = new HashMap<>();

    /**
     * Creates an InputHandler with default key-command bindings.
     */
    public InputHandler() {
        setupDefaultBindings();
    }

    /**
     * Sets up the default key-to-command mappings:
     * <ul>
     *   <li>W -> JumpCommand</li>
     *   <li>A -> MoveXCommand (left movement)</li>
     *   <li>D -> MoveYCommand (right movement)</li>
     *   <li>S -> CrouchCommand</li>
     * </ul>
     */
    private void setupDefaultBindings() {
        bindKey(KeyCode.W, new JumpCommand());
        bindKey(KeyCode.A, new MoveXCommand());
        bindKey(KeyCode.D, new MoveYCommand());
        bindKey(KeyCode.S, new CrouchCommand());
    }

    /**
     * Associates a specific key with a command action.
     *
     * @param key     the key to bind
     * @param command the command to execute when the key is pressed
     */
    public void bindKey(KeyCode key, Command command) {
        keyBindings.put(key, command);
    }

    /**
     * Processes input states, executing the appropriate commands for each pressed key.
     * <p>
     * For each key in the binding map:
     * <ul>
     *   <li>If the key is pressed, execute its command on the player.</li>
     *   <li>If the S key (crouch) is not pressed and the player is crouching, have the player stand up.</li>
     * </ul>
     *
     * @param keys   a map of key states, where true indicates the key is currently pressed
     * @param player the player to be manipulated by the commands
     * @param model  the current game model for environment and data context
     */
    public void handleInput(HashMap<KeyCode, Boolean> keys, Player player, GameModel model) {
        for (KeyCode key : keyBindings.keySet()) {
            if (keys.getOrDefault(key, false)) {
                keyBindings.get(key).execute(player, model);
            }
        }

        // Handle standing up if crouch key is released
        if (!keys.getOrDefault(KeyCode.S, false)) {
            if (player.isCrouching()) {
                player.standUp();
            }
        }
    }
}