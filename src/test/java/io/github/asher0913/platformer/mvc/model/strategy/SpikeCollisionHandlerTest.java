package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import io.github.asher0913.platformer.mvc.model.factory.Spike;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpikeCollisionHandlerTest {
    private GameModel model;
    private Player player;
    private Spike spike;
    private SpikeCollisionHandler handler;

    @BeforeEach
    void setUp() {
        Pane gameRoot = new Pane();
        model = GameModel.getInstance(gameRoot);
        model.initLevel(new String[]{"D"});
        model.resetScoreHealth(0, GameModel.MAX_HEALTH);
        player = model.getPlayer();
        spike = (Spike) model.getEntityFromHitbox(model.getSpikes().get(0));
        handler = new SpikeCollisionHandler();
    }

    @Test
    void testHandleCollision() {
        int initialHealth = model.getPlayerHealth();

        handler.handleCollision(player, model, spike);

        // Check that the player's health decreased
        assertEquals(initialHealth - 10, model.getPlayerHealth());

        // Check that the spike is marked as triggered
        assertTrue(spike.isTriggered());
    }

    @Test
    void aSpikeOnlyHurtsOnce() {
        int initialHealth = model.getPlayerHealth();
        handler.handleCollision(player, model, spike);
        handler.handleCollision(player, model, spike);
        assertEquals(initialHealth - 10, model.getPlayerHealth());
    }
}
