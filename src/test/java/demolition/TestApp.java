package demolition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestApp extends AppTester {

    @Test
    // Test that the app moves the player correctly based on keypresses, and does not move again until key is released
    public void testMovePlayer(){
        Player p = app.game.getCurrentLevel().getPlayer();
        this.app.keyCode = App.RIGHT;
        app.keyPressed();
        assertEquals(32*2, p.getX());        
        assertEquals(32*3, p.getY());        
    }
    
    @Test
    // Test the app does not move the player unless another key is pressed
    public void testMovePlayerKeyRelease(){
        Player p = app.game.getCurrentLevel().getPlayer();
        this.app.keyCode = App.RIGHT;
        app.keyPressed();
        assertEquals(32*2, p.getX());        
        assertEquals(32*3, p.getY());        
        app.keyPressed();
        assertEquals(32*2, p.getX());        
        assertEquals(32*3, p.getY()); 
        this.app.keyReleased();
        this.app.keyCode = App.RIGHT;
        app.keyPressed();
        assertEquals(32*3, p.getX());        
        assertEquals(32*3, p.getY());
    }

    @Test
    // Test the app does not allow the player to move into obstacles
    public void testMovePlayerInputCollisionWithObstacle(){
        Player p = app.game.getCurrentLevel().getPlayer();
        this.app.keyCode = App.LEFT;
        app.keyPressed();
        assertEquals(32, p.getX());  //Player's starting position      
        assertEquals(32*3, p.getY()); //Player's starting position       
    }

    @Test
    //Test that a new bomb is created in the player's position after the player presses a space bar
    public void testPlayerPlaceBombUsingSpace(){
        Player p = app.game.getCurrentLevel().getPlayer();
        this.app.keyCode = 32; //32 = key for space
        app.keyPressed();
        assertEquals(32, p.getX());  //Player's starting position      
        assertEquals(32*3, p.getY()); //Player's starting position
        assertEquals(1, app.game.getCurrentLevel().getBombs().size()); 
    }
}
