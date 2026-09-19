package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.view.utils.AppConfig;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the main view component of the platformer game.
 * Provides a hierarchical structure for managing different parts of the UI, including
 * the application root, game root, and UI root.
 *
 * <p>This class implements the Singleton design pattern, ensuring that only one instance
 * of {@code GameView} exists during the application lifecycle. It manages the visual
 * elements and bindings required for the game.</p>
 */
public class GameView {

    /**
     * Singleton instance of {@code GameView}.
     */
    private static GameView instance;

    /**
     * The root container for all game elements, including the background, game, and UI layers.
     */
    private Pane appRoot;

    /**
     * The root container for game-specific elements, such as platforms and the player.
     */
    private Pane gameRoot;

    /**
     * The root container for user interface components, such as health bars and scores.
     */
    private Pane uiRoot;

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the root containers for the view.
     */
    private GameView() {
        appRoot = new Pane();
        gameRoot = new Pane();
        uiRoot = new Pane();
    }

    /**
     * Retrieves the singleton instance of {@code GameView}.
     * If no instance exists, it initializes a new one.
     *
     * @return the singleton instance of {@code GameView}.
     */
    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }

    /**
     * Initializes the view by setting up the background and clearing any existing child elements.
     * <p>The background color is determined by {@link AppConfig#getBackgroundColor()}.</p>
     */
    public void initView() {
        appRoot.getChildren().clear();
        Rectangle bg = new Rectangle(1280, 720);
        bg.setFill(Color.web(AppConfig.getBackgroundColor()));
        appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
    }

    /**
     * Binds the player's position to the view, ensuring the camera follows the player
     * as they move horizontally within the level boundaries.
     *
     * @param player     the {@link Rectangle} representing the player character.
     * @param levelWidth the width of the game level, used to calculate boundaries.
     */
    public void bindPlayerToView(Rectangle player, int levelWidth) {
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < levelWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });
    }

    /**
     * Resets the singleton instance, allowing it to be reinitialized.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Retrieves the root container for all game elements.
     *
     * @return the {@link Pane} representing the application root.
     */
    public Pane getAppRoot() {
        return appRoot;
    }

    /**
     * Retrieves the root container for game-specific elements.
     *
     * @return the {@link Pane} representing the game root.
     */
    public Pane getGameRoot() {
        return gameRoot;
    }

    /**
     * Retrieves the root container for user interface components.
     *
     * @return the {@link Pane} representing the UI root.
     */
    public Pane getUiRoot() {
        return uiRoot;
    }
}