package io.github.asher0913.platformer.mvc.model;

import javafx.scene.layout.Pane;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelDataTest {
    @org.junit.jupiter.api.BeforeAll
    static void startFx() throws InterruptedException {
        io.github.asher0913.platformer.FxRuntime.start(); // monsters schedule animations
    }

    private static final String[][] LEVELS = {LevelData.Level1, LevelData.Level2, LevelData.Level3};

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void everyLevelIsRectangularAndUsesKnownTiles(int index) {
        String[] level = LEVELS[index];
        int width = level[0].length();
        assertTrue(Arrays.stream(level).allMatch(row -> row.length() == width));
        assertTrue(Arrays.stream(level).allMatch(row -> row.matches("[01CHDME]+")));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void everyLevelHasAPlayerAGoalAndSomethingToCollect(int index) {
        String[] level = LEVELS[index];
        GameModel model = GameModel.getInstance(new Pane());
        model.initLevel(level);
        assertNotNull(model.getPlayer());
        assertEquals(1, model.getGoals().size());
        assertFalse(model.getCoins().isEmpty());
        assertFalse(model.getPlatforms().isEmpty());
        assertEquals(level[0].length() * 60, model.getLevelWidth());
    }
}
