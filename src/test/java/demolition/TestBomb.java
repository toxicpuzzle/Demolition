package demolition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestBomb extends AppTester {
    
    private Bomb bomb; 
    private Level level;
    private static double TIME_PER_FRAME = 0.2;

    @BeforeEach
    public void getBombs(){
        this.bomb = SpriteFactory.makeBomb(0, 0, app);
        this.level = Loader.loadFromFile("src/test/resources/empty.txt", 100, 100, app);
        this.bomb.setCurrentLevel(this.level);
        this.bomb.setApp(app);
    }

    @Test
    public void notNull(){
        assertNotNull(this.bomb);;
    }

    @Test
    // Check that the bomb playes all animations and vanishes after being placed and generates an explosion on the same spot
    public void testVanishing(){
        for (int i = 0, j = 0; i < TIME_PER_FRAME*App.FPS*8; i++){
            this.bomb.tick();
        }
        assertEquals(this.bomb.isRemoved, true);
        assertTrue(this.level.getExplosionTiles().size() > 0);
        // bomb.tick();
        // assertEquals(this.bomb.isRemoved, true);
    } 

    @Test
    // Test that removed bomb does not tick at all
    public void testVanishedBomb(){
        bomb.remove();
        for (int i = 0, j = 0; i < TIME_PER_FRAME*App.FPS*8; i++){
            this.bomb.tick();
        }
        assertEquals(this.bomb.isRemoved, true);
        assertTrue(this.level.getExplosionTiles().size() == 0);
        // bomb.tick();
        // assertEquals(this.bomb.isRemoved, true);
    } 

    @Test
    // Check that the animation cycle for the bomb is played correctly
    public void testAnimation(){
        for (int i = 0, j = 0; i < TIME_PER_FRAME*App.FPS*8; i++){
            this.bomb.tick();
            app.draw();
            if (i >= TIME_PER_FRAME*App.FPS*j){
                assertEquals(j, bomb.currentAnimation.getFrameNumber());
                j++;
            }
        }
    }
    
}
