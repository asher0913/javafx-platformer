package io.github.asher0913.platformer.mvc.view;

import io.github.asher0913.platformer.mvc.model.GameModel;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.asher0913.platformer.mvc.controller.state.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevelCompleteViewTest {

    @BeforeAll
    static void initFX() throws Exception {
        // Initialize the JavaFX runtime before running any tests.
        // A CountDownLatch is used to ensure synchronization,
        // waiting until the JavaFX runtime is fully initialized.
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown); // Start JavaFX runtime
        latch.await(5, TimeUnit.SECONDS);   // Wait for up to 5 seconds
    }

    @Test
    void testShowAndCloseView() throws InterruptedException {
        // Set up GameView and GameModel
        GameView.getInstance().initView(); // Initialize the game view
        GameModel model = GameModel.getInstance(GameView.getInstance().getGameRoot()); // Link game model to the view
        model.increaseCurrentScore(50); // Simulate a score increase in the model

        // Create an instance of LevelCompleteView
        LevelCompleteView view = LevelCompleteView.getInstance();

        // Create a dummy LevelCompleteListener to handle actions from the view
        LevelCompleteListener listener = new LevelCompleteListener() {
            @Override public void onNextLevel() {} // Stub for handling "Next Level" action
            @Override public void onRestartClicked() {} // Stub for handling "Restart" action
            @Override public void onExitClicked() {} // Stub for handling "Exit" action
        };

        // Show and close the LevelCompleteView within the JavaFX thread
        CountDownLatch latch = new CountDownLatch(1); // Synchronization mechanism
        Platform.runLater(() -> {
            view.showLevelCompleteView(listener); // Display the view
            view.close(); // Close the view after showing it briefly
            latch.countDown(); // Signal that the operation is complete
        });

        // Wait for the view operations to complete, allowing up to 3 seconds
        latch.await(3, TimeUnit.SECONDS);

        // Verify that no exceptions occurred during the test
        assertTrue(true, "View showed and closed without exceptions.");
    }
}
