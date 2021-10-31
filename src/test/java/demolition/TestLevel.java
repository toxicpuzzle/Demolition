package demolition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestLevel extends AppTester {
    //Test removeall objects
    private Level level;

    @BeforeEach
    public void getLevel(){
        this.level = Loader.loadFromFile("src/test/resources/empty.txt", 100, 100, app);
    }

    @Test
    public void notNull(){
        assertNotNull(this.level);
    }

    //Test getting all gameobjects and that the correct number of gameobjects is returned (includes empty tiles)
    @Test
    public void testGettingAll(){
        List<GameObject> tiles = this.level.getGameObjects();
        assertEquals(206, tiles.size());
    }

    //Test getting all gameobjects and that the correct number of solidwalls is returned
    @Test
    public void testGettingSolidWalls(){
        List<? extends GameObject> tiles = this.level.getSolidWalls();
        assertEquals(70, tiles.size());
    }

    //Test getting all gameobjects and that the correct number of broken walls is returned
    @Test
    public void testGettingBrokenWalls(){
        List<? extends GameObject> tiles = this.level.getBrokenWalls();
        assertEquals(9, tiles.size());
    }

    //Test getting all empty tiles and that the correct number of emptytiles is returned
    @Test
    public void testGettingEmptyTiles(){
        List<? extends GameObject> tiles = this.level.getEmpty();
        assertEquals(124, tiles.size());
    }

    //Test all gameobjects are removed from the level if the removeallgameobjects method is called
    @Test
    public void testRemoveAllGameObjects(){
        this.level.removeAllObjects();
        List<GameObject> tiles = this.level.getGameObjects();
        for (GameObject object: tiles){
            assertEquals(true, object.isRemoved);
        }
    }

    //Test all objects' status will be set to NOT removed if level resets (will get lots of code coverage)
    @Test 
    public void testObjectStatusAfterReset(){
        this.level.removeAllObjects();
        this.level.reset();
        List<GameObject> tiles = this.level.getGameObjects();
        for (GameObject object: tiles){
            // System.out.println(object.getClass().getName());
            // System.out.println(object.xPos);
            // System.out.println(object.yPos);
            assertEquals(false, object.isRemoved);
        }
    }
    

    //Test explosion tiles and bombs are removed from level if it resets
    @Test 
    public void testExplosionsAndBombsRemovedOnReset(){
        this.level.removeAllObjects();
        Bomb bomb = SpriteFactory.makeBomb(5*32, 3*32, app);
        Explosion explosion = new Explosion(5*32, 3*32, this.level, app);
        
        // Add the bomb and explosion tiles to the level
        this.level.addObject(bomb);
        explosion.addAllExpTiles();

        // Check that bombs and explosion tiles are removed on reset
        this.level.reset();
        assertEquals(0, this.level.getBombs().size());        
        assertEquals(0, this.level.getExplosionTiles().size());        
    }

    // TODO: Fix jacoco testcase branching issue in both player and enemy class -> get to 90% branch coverage in addition to 90% normal code coverage.
        // Offending line
        // if (this.collisionWith(e) && !e.isRemoved){
        //     return true;
        // }
    
    // TODO: Add testenemyyellow, testDirection, and optional: testexplosiontile

    
}
