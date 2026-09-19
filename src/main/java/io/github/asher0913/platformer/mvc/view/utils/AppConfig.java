package io.github.asher0913.platformer.mvc.view.utils;

/**
 * Manages the global application configuration, including customizable settings
 * like the background color of the application's UI.
 *
 * <p>This class implements the Singleton design pattern, ensuring that only one
 * instance of the configuration exists throughout the application.</p>
 */
public class AppConfig {

    /**
     * The single instance of the AppConfig class.
     */
    private static final AppConfig instance = new AppConfig();

    /**
     * Stores the background color of the application's UI.
     * Defaults to "white".
     */
    private String backgroundColor = "white";

    /**
     * Private constructor to enforce Singleton behavior.
     */
    private AppConfig() {}

    /**
     * Retrieves the singleton instance of the AppConfig class.
     *
     * @return The single instance of AppConfig.
     */
    public static AppConfig getInstance() {
        return instance;
    }

    /**
     * Retrieves the current background color of the application's UI.
     *
     * @return The current background color as a string.
     */
    public static String getBackgroundColor() {
        return instance.backgroundColor;
    }

    /**
     * Updates the background color of the application's UI.
     *
     * @param color The new background color as a string.
     */
    public static void setBackgroundColor(String color) {
        instance.backgroundColor = color;
    }
}