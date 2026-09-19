package io.github.asher0913.platformer.mvc.model.factory;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * The {@code Player} class represents the player's character within the game world.
 * It extends {@link GameEntity} and adds specialized logic for movement, jumping, and crouching.
 * The player interacts with platforms, can jump when allowed, and can crouch to fit into smaller spaces.
 *
 * <b>Key Features:</b>
 * <ul>
 *   <li>Horizontal and vertical movement with collision detection against platforms.</li>
 *   <li>Ability to jump when conditions allow, adjusting velocity accordingly.</li>
 *   <li>Crouch and stand-up mechanics that modify the player's hitbox height.</li>
 *   <li>Resetting player state (position, velocity, jump availability) for level transitions or restarts.</li>
 * </ul>
 *
 * <b>Movement and Collision:</b>
 * The player moves step-by-step along the X or Y axis. Before each increment:
 * <ul>
 *   <li>Checks for platform collisions and stops if a collision is detected.</li>
 *   <li>Updates position if no collision occurs.</li>
 * </ul>
 *
 * <b>Jumping:</b>
 * Calling {@link #jump()} applies an upward velocity if the player can jump. Once airborne,
 * the player cannot jump again until they land on a platform.
 *
 * <b>Crouching and Standing:</b>
 * Crouching reduces player height and changes the hitbox and image size.
 * Standing up restores the original height.
 *
 * <b>Properties and Their Roles:</b>
 * <table border="1">
 * <caption>Player Properties</caption>
 * <tr><th>Property</th><th>Type</th><th>Description</th></tr>
 * <tr><td>velocity</td><td>{@link Point2D}</td><td>Current horizontal and vertical velocity of the player.</td></tr>
 * <tr><td>canJump</td><td>boolean</td><td>Indicates if the player can currently jump.</td></tr>
 * <tr><td>originalHeight</td><td>double</td><td>The player's original standing height.</td></tr>
 * <tr><td>crouchHeight</td><td>double</td><td>The player's height when crouched.</td></tr>
 * <tr><td>deltaHeight</td><td>double</td><td>The difference in height between standing and crouching states.</td></tr>
 * <tr><td>isCrouching</td><td>boolean</td><td>Tracks whether the player is currently crouched.</td></tr>
 * <tr><td>imageView</td><td>{@link ImageView}</td><td>The visual representation of the player.</td></tr>
 * </table>
 *
 * <b>Usage Example:</b>
 * <pre>{@code
 * Player player = new Player(0, 600, 40, 40);
 * player.moveX(5, platforms); // Move right 5 steps if no collisions
 * player.jump(); // If canJump is true, player velocity adjusts and player leaves the ground
 * player.crouch(); // Lower the player's height for passing under obstacles
 * }</pre>
 */
public class Player extends GameEntity {
    private Point2D velocity = new Point2D(0, 0);
    private boolean canJump = true;
    private final double originalHeight = 40;
    private final double crouchHeight = 20;
    private final double deltaHeight = 20;
    private boolean isCrouching = false;
    private ImageView imageView;

    /**
     * Constructs a new {@code Player} at the specified coordinates with the given width and height.
     * Loads the player image and initializes the player's hitbox and visual representation.
     *
     * @param x      the initial X-coordinate of the player
     * @param y      the initial Y-coordinate of the player
     * @param width  the width of the player's hitbox
     * @param height the height of the player's hitbox
     * @throws IllegalArgumentException if the player image cannot be found
     */
    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);

        Image image = new Image(getClass().getResourceAsStream("/Images/player.png"));
        if (image == null) {
            throw new IllegalArgumentException("Image not found: /Images/player.png");
        }

        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        entity.getChildren().add(imageView);
    }

    /**
     * Moves the player horizontally by the specified value, checking for collisions at each step.
     *
     * <p>If a collision with a platform is detected, the player stops moving immediately.</p>
     *
     * @param value      the horizontal distance to move (positive for right, negative for left)
     * @param platforms  the list of platform rectangles to check for collisions
     */
    public void moveX(int value, List<Rectangle> platforms) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            boolean collided = false;

            for (Rectangle platform : platforms) {
                Bounds playerBounds = hitbox.getBoundsInParent();
                Bounds platformBounds = platform.getBoundsInParent();

                if (movingRight) {
                    if (playerBounds.getMaxX() >= platformBounds.getMinX() &&
                            playerBounds.getMinX() < platformBounds.getMinX() &&
                            playerBounds.getMaxY() > platformBounds.getMinY() &&
                            playerBounds.getMinY() < platformBounds.getMaxY()) {
                        collided = true;
                        break;
                    }
                } else {
                    if (playerBounds.getMinX() <= platformBounds.getMaxX() &&
                            playerBounds.getMaxX() > platformBounds.getMaxX() &&
                            playerBounds.getMaxY() > platformBounds.getMinY() &&
                            playerBounds.getMinY() < platformBounds.getMaxY()) {
                        collided = true;
                        break;
                    }
                }
            }

            if (collided) {
                return;
            }

            hitbox.setTranslateX(hitbox.getTranslateX() + (movingRight ? 1 : -1));
            entity.setTranslateX(hitbox.getTranslateX());
        }
    }

    /**
     * Moves the player vertically by the specified value, checking for platform collisions at each step.
     *
     * <p>If a collision is detected (e.g., landing on a platform from above), the movement stops and
     * the player regains the ability to jump if landing from above.</p>
     *
     * @param value      the vertical distance to move (positive for down, negative for up)
     * @param platforms  the list of platform rectangles to check for collisions
     */
    public void moveY(int value, List<Rectangle> platforms) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Rectangle platform : platforms) {
                if (hitbox.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        // If moving down and lands on top of platform
                        if (hitbox.getTranslateY() + hitbox.getHeight() == platform.getTranslateY()) {
                            canJump = true;
                            return;
                        }
                    } else {
                        // If moving up and hits the bottom of a platform
                        if (hitbox.getTranslateY() == platform.getTranslateY() + platform.getHeight()) {
                            return;
                        }
                    }
                }
            }

            hitbox.setTranslateY(hitbox.getTranslateY() + (movingDown ? 1 : -1));
            entity.setTranslateY(hitbox.getTranslateY());
        }
    }

    /**
     * Makes the player jump if {@code canJump} is true.
     *
     * <p>Adjusts the vertical velocity upwards and sets {@code canJump} to false until landing on a platform.</p>
     */
    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    /**
     * Transitions the player into a crouching state, reducing the hitbox height and adjusting the image.
     */
    public void crouch() {
        if (isCrouching) return;

        isCrouching = true;
        hitbox.setHeight(crouchHeight);
        hitbox.setTranslateY(hitbox.getTranslateY() + deltaHeight);

        imageView.setFitHeight(crouchHeight);
        entity.setTranslateY(hitbox.getTranslateY());
    }

    /**
     * Returns the player to a standing state after crouching, restoring the hitbox and image heights.
     */
    public void standUp() {
        if (!isCrouching) return;

        isCrouching = false;
        hitbox.setHeight(originalHeight);
        hitbox.setTranslateY(hitbox.getTranslateY() - deltaHeight);

        imageView.setFitHeight(originalHeight);
        entity.setTranslateY(hitbox.getTranslateY());
    }

    /**
     * Resets the player's state to a default starting position and velocity.
     * Restores jump ability and returns the player to a default vertical position.
     */
    public void resetPlayerState() {
        hitbox.setTranslateX(0);
        hitbox.setTranslateY(600);
        velocity = new Point2D(0, 0);
        canJump = true;
    }

    /**
     * @return the player's current velocity as a {@link Point2D}
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * Sets the player's velocity to the specified value.
     *
     * @param velocity the new velocity vector
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /**
     * @return true if the player is currently crouching, false otherwise
     */
    public boolean isCrouching() {
        return isCrouching;
    }

    /**
     * Sets the player's ability to jump.
     *
     * @param b true to allow jumping, false to disallow
     */
    public void setCanJump(boolean b) {
        canJump = b;
    }

    /**
     * Sets the player's velocity, typically used when adjusting movement or jump forces.
     *
     * @param velocity the new velocity vector
     */
    public void setPlayerVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
}