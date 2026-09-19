package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.strategy.CoinCollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a collectible coin in the game that increases the player's score upon collection.
 *
 * <p>The {@code Coin} class extends {@link GameEntity} and is used to represent a collectible
 * item within the game. When the player collides with a coin, it triggers specific behavior,
 * such as incrementing the score. The collision behavior is defined by the {@link CoinCollisionHandler}
 * using the strategy pattern.</p>
 *
 * <p>The visual representation of the coin is handled by loading an image resource, and the coin
 * is added to the game's rendering system. If the image is not found, the class throws an
 * {@link IllegalArgumentException} to prevent runtime errors.</p>
 *
 * @see GameEntity
 * @see CoinCollisionHandler
 */
public class Coin extends GameEntity {

    /**
     * Constructs a new {@code Coin} entity at the specified position with the given dimensions.
     *
     * <p>The coin is visually represented by an image loaded from the resources folder and uses
     * the {@link CoinCollisionHandler} to define its interaction with the player. If the image
     * cannot be loaded, an {@link IllegalArgumentException} is thrown.</p>
     *
     * @param x      The x-coordinate of the coin's position.
     * @param y      The y-coordinate of the coin's position.
     * @param width  The width of the coin.
     * @param height The height of the coin.
     * @throws IllegalArgumentException if the coin image cannot be loaded.
     */
    public Coin(double x, double y, double width, double height) {
        super(x, y, width, height);

        // Assign collision behavior via the strategy pattern.
        collisionHandler = new CoinCollisionHandler();

        // Load and set the visual representation of the coin.
        Image image = new Image(getClass().getResourceAsStream("/Images/coin.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/coin.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        entity.getChildren().add(imageView);
    }
}