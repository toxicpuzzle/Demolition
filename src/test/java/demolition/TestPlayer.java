package demolition;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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

        //! app is not linked iwth player or level
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
        app.draw(); // Redundant
        assertEquals(player.animations.get(Direction.LEFT), player.currentAnimation);
    } 

    @Test
    // Check that the player's coords have moved right and that the correct animation cycle is being played post movement
    public void checkMoveRightNoObstacles(){
        player.moveRight();
        assertEquals(player.getX(), 32);
        assertEquals(player.justChangedDirection, true);
        app.draw();
        assertEquals(player.animations.get(Direction.RIGHT), player.currentAnimation);
    }

    @Test
    // Check that the player's coords have moved up and that the correct animation cycle is being played post movement
    public void checkMoveUpNoObstacles(){
        player.moveUp();
        assertEquals(player.getY(), -32);
        assertEquals(true, player.justChangedDirection);
        app.draw();
        assertEquals(player.animations.get(Direction.UP), player.currentAnimation);
    }

    @Test
    // Check that the player's coords have moved down and that the correct animation cycle is being played post movement
    public void checkMoveDownNoObstacles(){
        player.moveDown();
        assertEquals(player.getY(), 32);
        assertEquals(false, player.justChangedDirection);
        app.draw();
        assertEquals(player.animations.get(Direction.DOWN), player.currentAnimation);
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
        assertEquals(player.animations.get(Direction.DOWN), player.currentAnimation);
    }

    // Test placing bombs and colliding with explosions

    @Test 
    // Test if player can place a bomb, and that the bomb placed in the level is not null;
    public void testPlaceBomb(){
        this.player.placeBomb(app);
        List<Bomb> bombs = level.getBombs();
        Bomb bomb = bombs.get(0);
        assertEquals(1, bombs.size());
        assertEquals(this.player.getX(), bomb.getX());
        assertEquals(this.player.getY(), bomb.getY());
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

    private void tickPlayer(int milliSeconds){
        double seconds = milliSeconds*0.001;
        double frames = App.FPS*seconds;
        long iFrames = Math.round(frames);
        for (long i = 0; i < iFrames; i++){
            long ith = i+1;
            // System.out.println("ticked on the " + ith + "th" + " time.");
            this.player.tick();
        }
    }

    // TODO: Potential edge cases
    

    // Player walking into a broken tile after it has been borken -> can walk through

    @Test
    // Check that the animation is being played at the correct speed and cycles back to original animation frame after all frames are played
    public void checkAnimationCycleNoDirectionMovement(){
        Animation animation = this.player.getCurrentAnimation();
        assertEquals(0, animation.getFrameNumber());
        tickPlayer(200);
        assertEquals(1, animation.getFrameNumber());        
        tickPlayer(200);
        assertEquals(2, animation.getFrameNumber());
        tickPlayer(200);
        assertEquals(3, animation.getFrameNumber());
        tickPlayer(200);
        assertEquals(0, animation.getFrameNumber());
    }
    @Test
    // Check that the correct animation cycle is played when the player changes direction to up in the middle of an animation
    public void checkAnimationCycleWithSuccessfulDirectionChangeCaseUp(){
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());        
        tickPlayer(200);
        this.player.moveUp();
        assertEquals(this.player.animations.get(Direction.UP), this.player.currentAnimation);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        this.player.moveUp();
        assertEquals(this.player.animations.get(Direction.UP), this.player.currentAnimation);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(3, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
    }

    @Test
    // Check that the correct animation cycle is played when the player changes direction to left in the middle of an animation
    public void checkAnimationCycleWithSuccessfulDirectionChangeCaseLeft(){
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());        
        tickPlayer(200);
        this.player.moveLeft();
        assertEquals(this.player.animations.get(Direction.LEFT), this.player.currentAnimation);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        this.player.moveLeft();
        assertEquals(this.player.animations.get(Direction.LEFT), this.player.currentAnimation);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(3, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
    }

    @Test
    // Check that the correct animation cycle is played when the player changes direction to right in the middle of an animation
    public void checkAnimationCycleWithSuccessfulDirectionChangeCaseRight(){
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());        
        tickPlayer(200);
        this.player.moveRight();
        assertEquals(this.player.animations.get(Direction.RIGHT), this.player.currentAnimation);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        this.player.moveRight();
        assertEquals(this.player.animations.get(Direction.RIGHT), this.player.currentAnimation);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(3, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
    }

    @Test
    // Check that the frame count does not reset when the player moves in the same direction
    public void checkAnimationCycleSameDirection(){
        this.player.moveUp();
        assertEquals(this.player.animations.get(Direction.UP), this.player.currentAnimation);
        assertEquals(0, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(1, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        this.player.moveUp();
        assertEquals(this.player.animations.get(Direction.UP), this.player.currentAnimation);
        assertEquals(2, this.player.getCurrentAnimation().getFrameNumber());
        tickPlayer(200);
        assertEquals(3, this.player.getCurrentAnimation().getFrameNumber());
    }



}
