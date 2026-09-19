package io.github.asher0913.platformer.mvc.model.factory;

import io.github.asher0913.platformer.mvc.model.GameModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The {@code Monster} class represents an enemy entity in the platformer game.
 * It extends the {@link GameEntity} class, inheriting its core behavior and properties,
 * and adds functionality for automated bullet shooting at regular intervals.
 *
 * <p>The monster is visually represented using an image and periodically fires bullets
 * toward the player or in a predefined direction. The shooting behavior is controlled
 * using a {@link Timeline} animation timer.</p>
 *
 * <b>Key Features:</b>
 * <ul>
 *     <li>Displays a monster image at a specified position and size.</li>
 *     <li>Fires bullets every 2 seconds using a {@link Timeline} animation.</li>
 *     <li>Stops shooting when necessary through the {@link #stopShooting()} method.</li>
 * </ul>
 *
 * <p>Bullets are instantiated as {@link Bullet} objects and added to the game world
 * for collision detection and rendering.</p>
 */
public class Monster extends GameEntity {
    /** The timeline controlling the periodic bullet shooting behavior. */
    private Timeline shootTimer;

    /**
     * Constructs a {@code Monster} instance at a given position with specified dimensions.
     *
     * <p>The constructor sets up the monster's visual representation using an image file
     * and initializes a {@link Timeline} to manage the shooting behavior. Bullets are fired
     * every 2 seconds from the monster's position.</p>
     *
     * @param x      The X-coordinate (horizontal position) where the monster is placed.
     * @param y      The Y-coordinate (vertical position) where the monster is placed.
     * @param width  The width of the monster's hitbox and visual representation.
     * @param height The height of the monster's hitbox and visual representation.
     * @throws IllegalArgumentException If the monster image file is not found.
     */
    public Monster(double x, double y, double width, double height) {
        super(x, y, width, height);

        // Load and set the visual representation of the monster
        Image image = new Image(getClass().getResourceAsStream("/Images/monster.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/monster.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        entity.getChildren().add(imageView);

        // Initialize and start the shooting timer
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), e -> shootBullet()));
        shootTimer.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        shootTimer.play();
    }

    /**
     * Fires a bullet from the monster's current position.
     *
     * <p>The bullet is created slightly to the left of the monster and is centered
     * vertically relative to the monster's height. The bullet is added to the game
     * world and moves with a predefined velocity.</p>
     *
     * <p>This method is called automatically every 2 seconds by the {@code shootTimer}.</p>
     */
    private void shootBullet() {
        Bullet bullet = new Bullet(
                hitbox.getTranslateX() - 20,                    // Slightly left of the monster
                hitbox.getTranslateY() + hitbox.getHeight() / 2, // Centered vertically
                20,                                             // Bullet width
                20,                                             // Bullet height
                -1                                              // Moving left
        );
        bullet.addBullet(); // Add the bullet to the game world
    }

    /**
     * Stops the monster's shooting behavior.
     *
     * <p>This method stops the {@code shootTimer} animation, preventing the monster
     * from firing any more bullets. It can be used when the monster is removed from
     * the game world or when the game is paused.</p>
     */
    public void stopShooting() {
        if (shootTimer != null) {
            shootTimer.stop();
        }
    }
}
