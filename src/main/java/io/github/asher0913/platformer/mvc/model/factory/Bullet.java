package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.strategy.BulletCollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * The {@code Bullet} class represents a projectile in the platformer game.
 * It extends the {@link GameEntity} class and provides functionality for
 * movement, collision detection, and lifecycle management (adding/removing bullets).
 *
 * <p>A bullet is fired in a specified direction and moves horizontally at
 * a constant speed. If it collides with a platform or moves out of the level bounds,
 * it is removed from the game world. The collision behavior is handled using the
 * {@link BulletCollisionHandler} strategy.</p>
 *
 * <b>Key Features:</b>
 * <ul>
 *     <li>Visual representation of the bullet using an image.</li>
 *     <li>Horizontal movement with a fixed speed in a given direction.</li>
 *     <li>Collision detection with platforms or out-of-bounds removal.</li>
 *     <li>Integration into the game world through the {@code GameModel} class.</li>
 * </ul>
 */
public class Bullet extends GameEntity {
    /** The direction of the bullet's movement: -1 for left, 1 for right. */
    private int direction;

    /**
     * Constructs a {@code Bullet} instance at the specified position, dimensions, and direction.
     *
     * <p>The bullet is visually represented by an image and initialized with
     * a {@link BulletCollisionHandler} to handle collision logic.</p>
     *
     * @param x         The X-coordinate where the bullet is initially placed.
     * @param y         The Y-coordinate where the bullet is initially placed.
     * @param width     The width of the bullet's hitbox and image.
     * @param height    The height of the bullet's hitbox and image.
     * @param direction The direction of the bullet's movement; -1 for left, 1 for right.
     * @throws IllegalArgumentException If the bullet image file is not found.
     */
    public Bullet(double x, double y, double width, double height, int direction) {
        super(x, y, width, height);
        this.direction = direction;

        // Assign the collision handler for bullets.
        collisionHandler = new BulletCollisionHandler();

        // Load and set the visual representation of the bullet.
        Image image = new Image(getClass().getResourceAsStream("/Images/bullet.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/bullet.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        entity.getChildren().add(imageView);
    }

    /**
     * Moves the bullet horizontally based on its direction and checks for collisions.
     *
     * <p>The bullet moves a fixed distance in its designated direction each frame.
     * If the bullet collides with a platform or goes out of the level bounds,
     * it is removed from the game world using {@link #removeBullet()}.</p>
     *
     * @param model The {@link GameModel} instance managing the game world.
     */
    public void move(GameModel model) {
        // Calculate the new x-coordinate based on the bullet's direction.
        double newX = hitbox.getTranslateX() + direction * 5;
        setPosition(newX, hitbox.getTranslateY());

        // Remove the bullet if it moves out of bounds.
        if (newX < 0 || newX > model.getLevelWidth()) {
            removeBullet();
        }

        // Check for collisions with platforms.
        for (Rectangle platformHitbox : model.getPlatforms()) {
            GameEntity platform = model.getEntityFromHitbox(platformHitbox);
            if (platform != null && intersects(platform)) {
                removeBullet();
                return;
            }
        }
    }

    /**
     * Adds the bullet to the game world.
     *
     * <p>The bullet's hitbox is added to the {@link GameModel#getBullets()} list,
     * and its visual representation is added to the game root pane.</p>
     */
    public void addBullet() {
        GameModel model = GameModel.getInstance();
        model.getBullets().add(hitbox);                      // Add the bullet hitbox.
        model.getEntityMap().put(hitbox, this);              // Map the bullet's hitbox to this instance.
        model.getGameRoot().getChildren().add(entity);       // Add the bullet's visual representation.
    }

    /**
     * Removes the bullet from the game world.
     *
     * <p>The bullet's hitbox is removed from the {@link GameModel#getBullets()} list,
     * and its visual representation is removed from the game root pane.</p>
     */
    public void removeBullet() {
        GameModel model = GameModel.getInstance();
        model.getBullets().remove(hitbox);                   // Remove the bullet hitbox.
        model.getEntityMap().remove(hitbox);                 // Remove the hitbox-to-entity mapping.
        model.getGameRoot().getChildren().remove(entity);    // Remove the bullet's visual representation.
    }
}
