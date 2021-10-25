package demolition;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import demolition.App;
import demolition.GameObject;
import demolition.Sprites;

import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestPlayer extends AppTester {

    private Player player;
    private Level level;

    @BeforeEach
    public void makePlayer(){ //! Don't implicitly override parent method      
        this.player = (Player) Sprites.PLAYER.make(0, 0, app); 
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
    // Check moveleft without obstacles
    public void checkMoveLeftNoObstacles(){
        player.moveLeft();
        assertEquals(player.getX(), -32);
    } 

    @Test
    public void checkMoveRightNoObstacles(){
        player.moveRight();
        assertEquals(player.getX(), 32);
    }

    @Test
    public void checkMoveUpNoObstacles(){
        player.moveUp();
        assertEquals(player.getY(), -32);
    }

    @Test
    public void checkMoveRightObstacles(){
        player.moveDown();
        assertEquals(player.getY(), 32);
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

    }

    @Test
    //Check that the player dies and their position resets if it hits an explosion tile;
    public void checkCollisionWithExplosion(){
        GameManager manager = new GameManager(levels);
        GameObject eTile = Sprites.EXPLOSION_BOTTOM.make(this.player.getX() + 32, player.getY(), app);
        this.player = this.level.getPlayer();
        this.player.setCurrentLevel(this.level); // Gamemanager does this automatically
        this.level.addObject(eTile);
        player.moveRight();
        assertEquals(player.getLives(), 9);
        assertEquals(player.getX(), player.getXStarting());
        assertEquals(player.getY(), player.getYStarting());
    }
}
