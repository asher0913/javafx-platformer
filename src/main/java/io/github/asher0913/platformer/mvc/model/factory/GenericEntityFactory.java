package io.github.asher0913.platformer.mvc.model.factory;

import javafx.scene.layout.Pane;

/**
 * A factory class for creating instances of {@link GameEntity} subclasses dynamically.
 * <p>
 * This class uses reflection to instantiate game entities, such as {@link Coin}, {@link Platform}, or {@link Goal}.
 * The created entities are automatically added to the game world ({@code gameRoot}).
 * </p>
 */
public class GenericEntityFactory {

    /** The game root pane where the entities will be added. */
    protected final Pane gameRoot;

    /**
     * Constructs a {@code GenericEntityFactory} with a reference to the game root pane.
     *
     * @param gameRoot the root pane representing the game world
     */
    public GenericEntityFactory(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    /**
     * Creates an instance of the specified entity type and adds it to the game world.
     *
     * @param entityType the class type of the entity to be created (e.g., {@link Coin}, {@link Goal})
     * @param x          the x-coordinate for the entity's position
     * @param y          the y-coordinate for the entity's position
     * @param width      the width of the entity
     * @param height     the height of the entity
     * @param <T>        the type of the {@link GameEntity} to be created
     * @return the created entity instance
     * @throws RuntimeException if the entity creation fails
     */
    public <T extends GameEntity> T createEntity(Class<T> entityType, double x, double y, double width, double height) {
        try {
            T entity = entityType.getDeclaredConstructor(double.class, double.class, double.class, double.class)
                    .newInstance(x, y, width, height);
            gameRoot.getChildren().add(entity.getEntity());
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create entity: " + entityType.getSimpleName());
        }
    }
}