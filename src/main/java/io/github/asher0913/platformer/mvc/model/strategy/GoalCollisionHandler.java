package io.github.asher0913.platformer.mvc.model.strategy;

import io.github.asher0913.platformer.mvc.controller.GameController;
import io.github.asher0913.platformer.mvc.controller.StateController;
import io.github.asher0913.platformer.mvc.controller.state.GameEndState;
import io.github.asher0913.platformer.mvc.controller.state.LevelCompleteState;
import io.github.asher0913.platformer.mvc.model.GameModel;
import io.github.asher0913.platformer.mvc.model.factory.GameEntity;
import io.github.asher0913.platformer.mvc.model.factory.Player;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Handles the collision logic when the player reaches the goal.
 *
 * <p>The {@code GoalCollisionHandler} class implements the {@link CollisionHandler} interface
 * and defines the behavior that occurs when the player collides with a goal. Specifically,
 * the game transitions to the "Level Complete" state, stopping any active countdown timer and
 * game loop.</p>
 *
 * <b>Key Responsibilities:</b>
 * <ul>
 *   <li>Stops the game countdown timer and animation timer.</li>
 *   <li>Transitions the game to the "Level Complete" state.</li>
 * </ul>
 *
 * @see CollisionHandler
 * @see LevelCompleteState
 */
public class GoalCollisionHandler implements CollisionHandler {

    /**
     * Handles the collision between the player and the goal entity.
     *
     * @param player the player reaching the goal
     * @param model  the game model managing the game's state
     * @param entity the goal entity
     */
    @Override
    public void handleCollision(Player player, GameModel model, GameEntity entity) {
        GameController controller = GameController.getInstance();
        controller.getCountDownTimerController().stopCountdown(); // Stop countdown timer
        controller.getTimer().stop(); // Stop animation timer

        if(model.getCurrentLevelIndex() != model.getLevels().length - 1){
            Platform.runLater(() -> StateController.getInstance().setState(LevelCompleteState.getInstance()));
        }
        else{
            model.increaseCurrentLevelIndex();
            Platform.runLater(() -> StateController.getInstance().setState(GameEndState.getInstance(model.getGameRoot())));
        }
    }
}