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


    @AfterEach
    public void remove(){
        
    }

    @Test
    // Check that the player created created using the sprite factory class is not null
    public void notNull(){
        assertNotNull(this.player);
    }

    @Test
    // Check moveleft without obstacles, coords are right and that the player has changed directions
    public void checkMoveLeftNoObstacles(){
        player.moveLeft();
        assertEquals(player.getX(), -32);
        assertEquals(player.justChangedDirection, true);
    } 

    @Test
    public void checkMoveRightNoObstacles(){
        player.moveRight();
        assertEquals(player.getX(), 32);
        assertEquals(player.justChangedDirection, true);

    }

    @Test
    public void checkMoveUpNoObstacles(){
        player.moveUp();
        assertEquals(player.getY(), -32);
        assertEquals(player.justChangedDirection, true);

    }

    @Test
    public void checkMoveRightObstacles(){
        player.moveDown();
        assertEquals(player.getY(), 32);
        assertEquals(player.justChangedDirection, true);
    }

    // Test movements with obstacles

    @Test
    // Check moveleft with obstacles
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
    }

    // Test collision with explosions and bombs

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
        this.player.setCurrentLevel(this.level); // Gamemanager does this automatically
        this.level.addObject(eTile);
        player.moveRight();     
        assertEquals(true, player.collideWithExplosion());
    }

    @Test
    //Check that the collision with explosion returns false if player does not collides with explosion if it has already expired
    public void checkCollisionWithExplosionNegative(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        GameObject eTile = SpriteFactory.makeExplosionCentre(this.player.getX() + 32, player.getY(), app);
        eTile.remove();
        this.player.setCurrentLevel(this.level); // Gamemanager does this automatically
        this.level.addObject(eTile);
        assertEquals(false, player.collideWithExplosion());
    }

    @Test
    //Check that the collision with explosion returns false if player does not collides with explosion if the player is not standing on explosion tile
    public void checkCollisionWithExplosionNegativeNoExplosion(){
        List<Level> levels = new ArrayList<Level>();
        levels.add(this.level);
        this.player = this.level.getPlayer();
        this.player.setCurrentLevel(this.level); // Gamemanager does this automatically
        assertEquals(false, player.collideWithExplosion());
    }

    

}
