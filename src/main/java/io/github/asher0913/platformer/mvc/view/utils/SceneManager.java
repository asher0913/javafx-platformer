package io.github.asher0913.platformer.mvc.view.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the loading and setting of scenes in the application.
 *
 * <p>This class implements the Singleton design pattern to ensure a single
 * point of control for managing scenes across the application. It integrates
 * with {@link AppConfig} to apply global settings like the background color.</p>
 */
public class SceneManager {

    /**
     * The single instance of the SceneManager class.
     */
    private static SceneManager instance;

    /**
     * Private constructor to enforce Singleton behavior.
     */
    private SceneManager() {}

    /**
     * Retrieves the singleton instance of the SceneManager class.
     *
     * @return The single instance of SceneManager.
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    /**
     * Loads a scene from the specified FXML file and applies the global
     * application settings to the scene's root.
     *
     * @param stage    The primary stage to set the scene on.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @return The root node of the loaded scene, or {@code null} if loading fails.
     */
    public static Parent loadSceneWithAppRoot(Stage stage, String fxmlPath) {
        return getInstance().loadSceneWithAppRootInstance(stage, fxmlPath);
    }

    /**
     * Internal method to load a scene from an FXML file and apply global
     * settings. This method is invoked by the public {@link #loadSceneWithAppRoot}
     * method.
     *
     * @param stage    The primary stage to set the scene on.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @return The root node of the loaded scene, or {@code null} if loading fails.
     */
    private Parent loadSceneWithAppRootInstance(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Apply the global background color from AppConfig
            String backgroundColor = AppConfig.getBackgroundColor();
            root.setStyle("-fx-background-color: " + backgroundColor + ";");

            // Create and set the scene on the stage
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML file: " + fxmlPath);
            return null;
        }
    }
}