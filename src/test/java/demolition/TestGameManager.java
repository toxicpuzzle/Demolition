package demolition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
public class TestGameManager extends AppTester {
    //Test to next level
    private GameManager manager;

    @BeforeEach
    public void getManager(){
        List<Level> levels = Loader.loadAllFiles(app, "src/test/resources/configs.json");
        this.manager = new GameManager(levels);
    }

    @Test
    public void notNull(){
        assertNotNull(this.manager);
    }

    // Test win and gameover conditions

    @Test
    // Check manager goes to gameover if the player runs out of time
    public void testGameOverOutOfTime(){
        Level level = manager.getCurrentLevel();
        for (int i = 0; i < 100*App.FPS; i++){
            level.tick();
        }
        assertTrue(manager.hasGameOver());
    }

    // Test player goes to the next level if they step on the goal tile (spawn player on goal tile)

    // Test player moves goes to the win screen if they step on the goal tile in the last level.

    //Test player loses life if they collide with the enemy -> create enemy and player in the same spot

    // Test level restarts if the player hits the enemy


    



    //Test removing broken walls after explosion + fix explosiontile code copy paste issue
}
