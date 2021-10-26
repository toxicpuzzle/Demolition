package demolition;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import demolition.App;
import demolition.GameObject;

import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


import java.util.ArrayList;

public class TestEnemyRed extends AppTester {

    private final static double SECONDS_TO_WALK = 0.8;
    private EnemyRed enemy;
    private Level level;

    @BeforeEach
    public void getEnemy(){ //! Don't implicitly override parent method      
        this.enemy = SpriteFactory.makeEnemyRed(32, 32*3, app); 
        this.level = Loader.loadFromFile("src/test/resources/empty.txt", 100, 100, app);
        level.addObject(enemy);
        enemy.setCurrentLevel(this.level);
    }

    @Test
    // Check that the player created created using the sprite factory class is not null
    public void notNull(){
        assertNotNull(this.enemy);
    }

    // Movement without obstacles
    @Test
    // Check moveleft without obstacles, coords are right and that the player has changed directions
    public void checkAutoMoveDown(){
        app.draw();
    } 

    @Test
    // Check that the enemy's coords have moved down and that the correct animation cycle is being played post movement
    public void checkMoveDownNoObstacles(){
        for (int i = 0; i < SECONDS_TO_WALK*App.FPS; i++) {
            this.enemy.tick();
        }
        assertEquals(128, enemy.yPos);
        assertEquals(enemy.animations.get(Direction.DOWN), enemy.currentAnimation);
    }

    // Test movement against obstacles (solid wall acts as placeholder for all solid objects)

    @Test
    // Check that the enemy changes direction after hitting obstacle (case that enemy can only go right because every other direction is blocked)
    public void checkMoveDownWithObstaclesAllSides(){
        level.addObject(SpriteFactory.makeSolidWall(32, 32*4, app));
        for (int i = 0; i < SECONDS_TO_WALK*App.FPS; i++) {
            this.enemy.tick();
        }
        assertEquals(32*3, enemy.yPos);
        assertEquals(32*2, enemy.xPos);
        assertEquals(enemy.animations.get(Direction.RIGHT), enemy.currentAnimation);
    }

    @Test
    // Check that the enemy changes direction after hitting obstacle (case where all enemyred could either go back up, left or right)
    public void checkMoveDownWithObstacles(){
        level.addObject(SpriteFactory.makeSolidWall(32*2, 32*4, app));
        enemy.moveRight();
        for (int i = 0; i < SECONDS_TO_WALK*App.FPS; i++) {
            this.enemy.tick();
        }
        assertNotEquals(enemy.animations.get(Direction.DOWN), enemy.currentAnimation);
    }

    // Collission with explosions

    @Test
    //Check that the collision with explosion returns true if enemy collides with explosion and is removed from the game on the next tick
    public void checkCollisionWithExplosion(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        GameManager manager = new GameManager(levels);
        GameObject eTile = SpriteFactory.makeExplosionCentre(this.enemy.getX() + 32, this.enemy.getY(), app); 
        this.level.addObject(eTile);
        enemy.moveRight();
        assertEquals(true, enemy.collideWithExplosion());
        this.enemy.tick();
        assertEquals(true, this.enemy.isRemoved);
    }

    @Test
    // Check that the enemy does not move at all if it is stuck
    public void checkStuck(){
        //!  ERROR: Stackoverflow since you'll run into infinit recursion if an enemy is stuck between 4 walls.
    }

}
