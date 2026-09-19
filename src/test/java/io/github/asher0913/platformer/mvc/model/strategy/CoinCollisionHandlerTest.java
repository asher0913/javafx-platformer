package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.Coin;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CoinCollisionHandlerTest {
    private GameModel model;
    private Player player;
    private Coin coin;
    private CoinCollisionHandler handler;

    @BeforeEach
    void setUp() {
        Pane gameRoot = new Pane();
        model = GameModel.getInstance(gameRoot);
        model.initLevel(new String[]{"C"});
        player = model.getPlayer();
        coin = (Coin) model.getEntityFromHitbox(model.getCoins().get(0));
        handler = new CoinCollisionHandler();
    }

    @Test
    void testHandleCollision() {
        int initialScore = model.getCurrentScore();

        handler.handleCollision(player, model, coin);

        // Check that the score increased
        assertEquals(initialScore + 10, model.getCurrentScore());

        // Check that the coin is removed from the game
        assertFalse(model.getCoins().contains(coin.getHitbox()));
        assertFalse(model.getGameRoot().getChildren().contains(coin.getEntity()));
    }
}
