package demolition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TestEnemyYellowStrategy extends AppTester{
    
    private final static double SECONDS_TO_WALK = 0.8;
    private EnemyYellow enemy;
    private Level level;


    @BeforeEach
    public void getEnemy(){ 
        this.enemy = SpriteFactory.makeEnemyYellow(32, 32*3, app); 
        this.level = Loader.loadFromFile("src/test/resources/empty.txt", 100, 100, app);
        level.addObject(enemy);
        enemy.setCurrentLevel(this.level);
    }

    @Test
    // Check that the yellow enemy rotates clockwise after hitting an obstacle when moving down
    public void checkMoveDownWithObstacles(){
        level.addObject(SpriteFactory.makeSolidWall(32, 32*4, app));
        for (int i = 0; i < SECONDS_TO_WALK*App.FPS; i++) {
            this.enemy.tick();
        }
        assertEquals(enemy.animations.get(Direction.RIGHT), enemy.currentAnimation);
    }

    @Test
    // Check that yellow enemy  rotates clockwise to down after hitting an obstacle when moving right
    public void checkMoveRightWithObstacles(){
        level.addObject(SpriteFactory.makeSolidWall(2*32, 32*3, app));
        this.enemy.direction = Direction.RIGHT;
        for (int i = 0; i < SECONDS_TO_WALK*App.FPS; i++) {
            this.enemy.tick();
        }
        assertEquals(enemy.animations.get(Direction.DOWN), enemy.currentAnimation);
    }
}
