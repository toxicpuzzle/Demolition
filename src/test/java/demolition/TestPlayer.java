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

public class TestPlayer extends AppTester {

    private Player player;
    private Level level;

    @BeforeEach
    public void makePlayer(){ //! Don't implicitly override parent method      
        this.player = SpriteFactory.makePlayer(0, 0, app); 
        this.level = Loader.loadFromFile("src/test/resources/level1.txt", 10, 10, app);
        player.setCurrentLevel(level);
    }

    @Test
    // Check that the player created created using the sprite factory class is not null
    public void notNull(){
        assertNotNull(this.player);
    }

    // Movement without obstacles

    @Test
    // Check moveleft without obstacles, coords are right and that the player has changed directions
    public void checkMoveLeftNoObstacles(){
        player.moveLeft();
        assertEquals(player.getX(), -32);
        assertEquals(player.justChangedDirection, true);
        app.draw();
        assertEquals(player.currentAnimation, player.animations.get(Direction.LEFT));
    } 

    @Test
    // Check that the player's coords have moved right and that the correct animation cycle is being played post movement
    public void checkMoveRightNoObstacles(){
        player.moveRight();
        assertEquals(player.getX(), 32);
        assertEquals(player.justChangedDirection, true);
        app.draw();
        assertEquals(player.currentAnimation, player.animations.get(Direction.RIGHT));
    }

    @Test
    // Check that the player's coords have moved up and that the correct animation cycle is being played post movement
    public void checkMoveUpNoObstacles(){
        player.moveUp();
        assertEquals(player.getY(), -32);
        assertEquals(player.justChangedDirection, true);
        app.draw();
        assertEquals(player.currentAnimation, player.animations.get(Direction.UP));
    }

    @Test
    // Check that the player's coords have moved down and that the correct animation cycle is being played post movement
    public void checkMoveDownNoObstacles(){
        player.moveDown();
        assertEquals(player.getY(), 32);
        assertEquals(player.justChangedDirection, true);
        app.draw();
        assertEquals(player.currentAnimation, player.animations.get(Direction.DOWN));
    }

    // Test movements with obstacles

    @Test
    // Check that the player does not move in any direction if there is a obstacle in that direction, and that the animation cycle does not change after the attempts
    public void moveLeftRightUpDownObstacles(){
        this.level = Loader.loadFromFile("src/test/resources/allwalls.txt", 10, 10, app);
        player.setY(32*3);
        player.setX(32);
        player.setCurrentLevel(level);
        player.moveLeft();
        player.moveRight();
        player.moveUp();
        player.moveDown();
        assertEquals(player.getX(), 32);
        assertEquals(player.getY(), 32*3);
        app.draw();
        assertEquals(player.currentAnimation, player.animations.get(Direction.DOWN));
    }

    // Test placing bombs and colliding with explosions

    @Test 
    // Test if player can place a bomb, and that the bomb placed in the level is not null;
    public void testPlaceBomb(){
        this.player.placeBomb(app);
        List<Bomb> bombs = level.getBombs();
        assertEquals(1, bombs.size());
    }

    @Test
    //Check that the collision with explosion returns true if player collides with explosion
    public void checkCollisionWithExplosion(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        GameManager manager = new GameManager(levels);
        this.player = this.level.getPlayer();
        GameObject eTile = SpriteFactory.makeExplosionCentre(this.player.getX() + 32, player.getY(), app);
        this.player.setCurrentLevel(this.level); 
        this.level.addObject(eTile);
        player.moveRight();     
        assertEquals(true, player.collideWithExplosion());
    }

    // Test colliding with enemies

    @Test
    //Check that the collision with explosion returns false if player does not collides with explosion if it has already expired
    public void checkCollisionWithExplosionNegative(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        GameObject eTile = SpriteFactory.makeExplosionCentre(this.player.getX() + 32, player.getY(), app);
        eTile.remove();
        this.player.setCurrentLevel(this.level); 
        this.level.addObject(eTile);
        assertEquals(false, player.collideWithExplosion());
    }

    @Test
    //Check that the collision with explosion returns false if player does not collides with explosion if the player is not standing on explosion tile
    public void checkCollisionWithExplosionNegativeNoExplosion(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        this.player.setCurrentLevel(this.level); 
        assertEquals(false, player.collideWithExplosion());
    }

    @Test
    //Check that the player is not colliding with any enemies in a level with enemies
    public void checkCollisionWithEnemyNegative(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        this.player.setCurrentLevel(this.level); 
        assertEquals(false, player.collideWithEnemy());
    }

    @Test
    //Check that the player is colliding with an enemy when it moves into an enemy
    public void checkCollisionWithEnemyPositive(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        this.player.setCurrentLevel(this.level); 
        level.addObject(SpriteFactory.makeEnemyRed(player.getX()+32, player.getY(), app));
        player.moveRight();
        assertEquals(true, player.collideWithEnemy());
    }

    // Test animation cycle is changing at correct pace.
    public void checkAnimationCycle(){
        // TODO: implement framerate counting method of checking speed rather than relying on millis();
    }



}
