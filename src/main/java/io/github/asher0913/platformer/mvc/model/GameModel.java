package io.github.asher0913.platformer.mvc.model;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.model.factory.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.*;

/**
 * The {@code GameModel} class maintains the central data and state of the game world.
 * It stores the current level's entities (platforms, coins, hearts, spikes, monsters, goals, bullets),
 * player information, and scoring/health values. It also provides methods to initialize levels,
 * handle transitions between levels, and update game data as the player interacts with the environment.
 *
 * <p>As a singleton, {@code GameModel} ensures a single source of truth for game state throughout
 * the application's lifecycle. By accessing a single {@code GameModel} instance, other components
 * (like controllers and views) can query and modify the game world data consistently.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *     <li>Initialize levels based on data from {@link LevelData}.</li>
 *     <li>Create and manage all game entities (player, platforms, coins, etc.) via {@link GenericEntityFactory}.</li>
 *     <li>Track the player's score, total score, and health.</li>
 *     <li>Support transitions to the next level and reset necessary states.</li>
 *     <li>Provide accessors for game entities and their hitboxes, enabling collision detection and interactions.</li>
 * </ul>
 *
 * <b>Usage Example:</b>
 * <pre>{@code
 * Pane gameRoot = new Pane();
 * GameModel model = GameModel.getInstance(gameRoot);
 * model.initLevel(LevelData.Level1);
 * Player player = model.getPlayer();
 * int currentScore = model.getCurrentScore();
 * }</pre>
 *
 * @see LevelData
 * @see GenericEntityFactory
 * @see Player
 */
public class GameModel {
    private static GameModel instance;
    private Pane gameRoot;
    private int levelWidth;
    private int currentLevelIndex = 0;
    private String[][] levels = {LevelData.Level1, LevelData.Level2, LevelData.Level3, LevelData.Level4};
    private GenericEntityFactory entityFactory;

    private List<Rectangle> platforms = new ArrayList<>();
    private List<Rectangle> coins = new ArrayList<>();
    private List<Rectangle> hearts = new ArrayList<>();
    private List<Rectangle> spikes = new ArrayList<>();
    private List<Rectangle> monsters = new ArrayList<>();
    private List<Rectangle> goals = new ArrayList<>();
    private List<Rectangle> bullets = new ArrayList<>();

    private final Map<Rectangle, GameEntity> entityMap = new HashMap<>();
    private Player player;
    private int currentScore = 0;
    private int totalScore = 0;
    private int playerHealth = 100;

    /**
     * Private constructor to enforce singleton pattern. Use {@link #getInstance(Pane)} to obtain
     * the singleton instance with a specified game root.
     *
     * @param gameRoot the pane representing the game rendering surface
     */
    private GameModel(Pane gameRoot) {
        this.gameRoot = gameRoot;
        entityFactory = new GenericEntityFactory(gameRoot);
    }

    /**
     * Retrieves the current {@code GameModel} instance without specifying a game root.
     * This will return null if {@link #getInstance(Pane)} has not been called previously.
     *
     * @return the current {@code GameModel} instance, or null if not initialized
     */
    public static GameModel getInstance() {
        return instance;
    }

    /**
     * Retrieves the {@code GameModel} instance, creating it if it does not exist.
     *
     * @param gameRoot the pane that serves as the game's rendering root
     * @return the singleton {@code GameModel} instance
     */
    public static GameModel getInstance(Pane gameRoot) {
        if (instance == null) {
            instance = new GameModel(gameRoot);
        }
        return instance;
    }

    /**
     * Initializes a level from the provided level data. Clears any existing entities and reloads
     * platforms, coins, hearts, spikes, monsters, and goals. Also creates a new player entity.
     *
     * @param levelData an array of strings representing the layout of the level
     */
    public void initLevel(String[] levelData) {
        gameRoot.getChildren().clear();
        entityMap.clear();
        platforms.clear();
        coins.clear();
        hearts.clear();
        spikes.clear();
        monsters.clear();
        goals.clear();
        bullets.clear();

        levelWidth = levelData[0].length() * 60;

        for (int i = 0; i < levelData.length; i++) {
            String line = levelData[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        // Empty space, no entity
                        break;
                    case '1':
                        Platform platform = entityFactory.createEntity(Platform.class, j * 60, i * 60, 60, 60);
                        platforms.add(platform.getHitbox());
                        entityMap.put(platform.getHitbox(), platform);
                        break;
                    case 'C':
                        Coin coin = entityFactory.createEntity(Coin.class, j * 60, i * 60, 60, 60);
                        coins.add(coin.getHitbox());
                        entityMap.put(coin.getHitbox(), coin);
                        break;
                    case 'H':
                        Heart heart = entityFactory.createEntity(Heart.class, j * 60, i * 60, 60, 60);
                        hearts.add(heart.getHitbox());
                        entityMap.put(heart.getHitbox(), heart);
                        break;
                    case 'D':
                        Spike spike = entityFactory.createEntity(Spike.class, j * 60, i * 60, 60, 60);
                        spikes.add(spike.getHitbox());
                        platforms.add(spike.getHitbox());
                        entityMap.put(spike.getHitbox(), spike);
                        break;
                    case 'M':
                        Monster monster = entityFactory.createEntity(Monster.class, j * 60, i * 60, 60, 60);
                        monsters.add(monster.getHitbox());
                        platforms.add(monster.getHitbox());
                        entityMap.put(monster.getHitbox(), monster);
                        break;
                    case 'E':
                        Goal goal = entityFactory.createEntity(Goal.class, j * 60, i * 60, 60, 60);
                        goals.add(goal.getHitbox());
                        entityMap.put(goal.getHitbox(), goal);
                        break;
                    default:
                        // Unrecognized character in the level data
                        break;
                }
            }
        }

        // Create a new player
        player = entityFactory.createEntity(Player.class, 0, 600, 40, 40);
    }

    /**
     * Stops all monsters from shooting bullets. Useful when transitioning levels or resetting state.
     */
    public void stopAllMonsterShooting() {
        for (Rectangle monsterHitbox : monsters) {
            GameEntity entity = entityMap.get(monsterHitbox);
            if (entity instanceof Monster monster) {
                monster.stopShooting();
            }
        }
    }

    /**
     * Loads the next level in the levels array. Clears player input states, resets player position,
     * stops monsters from shooting, increments the current level index, and initializes the new level.
     */
    public void loadNextLevel() {
        GameController.getInstance().getKeys().clear();
        player.resetPlayerState();
        gameRoot.setLayoutX(0);
        stopAllMonsterShooting();
        currentLevelIndex++;
        if (currentLevelIndex >= levels.length) {
            currentLevelIndex = 0;
        }
        initLevel(levels[currentLevelIndex]);
        GameController.getInstance().startGameLoop();
    }

    /**
     * Resets the player's score and health to the specified values.
     *
     * @param score  the new score to set
     * @param health the new health value to set for the player
     */
    public void resetScoreHealth(int score, int health) {
        currentScore = score;
        playerHealth = health;
    }

    /**
     * Retrieves the {@link GameEntity} associated with a given hitbox.
     *
     * @param hitbox the hitbox rectangle
     * @return the game entity mapped to this hitbox, or null if none is found
     */
    public GameEntity getEntityFromHitbox(Rectangle hitbox) {
        return entityMap.get(hitbox);
    }

    /**
     * @return the current level index, indicating which level is currently active
     */
    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    /**
     * Decreases the player's health by the specified amount.
     *
     * @param decrement the amount of health to reduce
     * @return the updated player's health value
     */
    public int decreaseHealth(int decrement) {
        return playerHealth -= decrement;
    }

    /**
     * Increases the player's health by the specified amount.
     *
     * @param increment the amount of health to add
     * @return the updated player's health value
     */
    public int increaseHealth(int increment) {
        return playerHealth += increment;
    }

    /**
     * Increases the player's current score by the given amount.
     *
     * @param increment the score increment
     * @return the updated current score
     */
    public int increaseCurrentScore(int increment) {
        return currentScore += increment;
    }

    /**
     * Increases the total score accumulated over multiple levels by the given amount.
     *
     * @param increment the increment to total score
     * @return the updated total score
     */
    public int increaseTotalScore(int increment) {
        return totalScore += increment;
    }

    /**
     * @return the player's current score for the current level
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * @return the player's current health value
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    /**
     * @return the {@link Player} instance representing the player character
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return a list of platform hitboxes present in the current level
     */
    public List<Rectangle> getPlatforms() {
        return platforms;
    }

    /**
     * @return the width of the current level (in pixels)
     */
    public int getLevelWidth() {
        return levelWidth;
    }

    /**
     * @return the root pane that visually represents the game world
     */
    public Pane getGameRoot() {
        return gameRoot;
    }

    /**
     * @return the array of all level data strings
     */
    public String[][] getLevels() {
        return levels;
    }

    /**
     * @return a list of heart hitboxes present in the current level
     */
    public List<Rectangle> getHearts() {
        return hearts;
    }

    /**
     * @return a list of goal hitboxes present in the current level
     */
    public List<Rectangle> getGoals() {
        return goals;
    }

    /**
     * @return a list of coin hitboxes present in the current level
     */
    public List<Rectangle> getCoins() {
        return coins;
    }

    /**
     * @return a list of spike hitboxes present in the current level
     */
    public List<Rectangle> getSpikes() {
        return spikes;
    }

    /**
     * @return a list of bullet hitboxes present in the current level
     */
    public List<Rectangle> getBullets() {
        return bullets;
    }

    /**
     * @return the total score accumulated over multiple levels
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Increments the current level index by one.
     *
     * @return the updated current level index
     */
    public int increaseCurrentLevelIndex() {
        return ++currentLevelIndex;
    }

    /**
     * @return the map associating hitboxes with their corresponding {@link GameEntity} instances
     */
    public Map<Rectangle, GameEntity> getEntityMap() {
        return entityMap;
    }
}