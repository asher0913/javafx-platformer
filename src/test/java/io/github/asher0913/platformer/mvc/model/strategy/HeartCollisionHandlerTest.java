package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeartCollisionHandlerTest {
    private GameModel model;
    private Player player;
    private GameEntity heart;
    private final HeartCollisionHandler handler = new HeartCollisionHandler();

    @BeforeEach
    void setUp() {
        model = GameModel.getInstance(new Pane());
        model.initLevel(new String[]{"H"});
        model.resetScoreHealth(0, GameModel.MAX_HEALTH);
        player = model.getPlayer();
        heart = model.getEntityFromHitbox(model.getHearts().get(0));
    }

    @Test
    void healsAndRemovesTheHeart() {
        model.decreaseHealth(30);
        handler.handleCollision(player, model, heart);
        assertEquals(80, model.getPlayerHealth());
        assertTrue(model.getHearts().isEmpty());
    }

    @Test
    void healthNeverExceedsTheMaximum() {
        handler.handleCollision(player, model, heart);
        assertEquals(GameModel.MAX_HEALTH, model.getPlayerHealth());
    }
}
