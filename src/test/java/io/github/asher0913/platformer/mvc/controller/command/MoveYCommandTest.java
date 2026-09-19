package io.github.asher0913.platformer.mvc.controller.command;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveYCommandTest {
    private GameModel gameModel;
    private Player player;
    private MoveYCommand moveYCommand;

    @BeforeEach
    void setUp() {
        Pane gameRoot = new Pane();
        gameModel = GameModel.getInstance(gameRoot);
        gameModel.initLevel(new String[]{
                "111111",
                "100001",
                "100001",
                "100001",
                "111111"
        });

        player = gameModel.getPlayer();
        moveYCommand = new MoveYCommand();
    }

    @Test
    void testMoveYWithinBounds() {
        // Initial position
        double initialY = player.getHitbox().getTranslateY();

        // Execute command to move down
        player.getHitbox().setTranslateY(initialY + 10);
        moveYCommand.execute(player, gameModel);

        // Assert that the Y position has changed
        assertEquals(initialY + 10, player.getHitbox().getTranslateY(), 0.01);
    }


    @Test
    void testMoveYOutOfBounds() {
        // Set the player position at the bottom of the screen
        player.setPosition(60, gameModel.getLevelWidth() - 10);

        // Attempt to move further down
        moveYCommand.execute(player, gameModel);

        // Assert the player has not moved out of bounds
        assertTrue(player.getHitbox().getTranslateY() <= gameModel.getLevelWidth());
    }
}
