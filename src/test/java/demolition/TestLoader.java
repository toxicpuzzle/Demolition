package demolition;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestLoader extends AppTester {

    @Test
    // Check that levels is empty if config file is empty 
    public void loadAllLevelsNoConfigFile() {
        List<Level> levels = Loader.loadAllFiles(app, "src/test/resources/configs.json");
        assertNotNull(levels);
        assert(levels.size() == 0);
    }

    @Test
    // Check that level without valid filename is not loaded i.e. null object returened
    public void loadInvalidLevelDoesNotExist() {
        Level level = Loader.loadFromFile("src/test/resources/donnotexist", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that loader returns a null level if level file has two goal tiles
    public void loadInvalidLevelTwoGoals(){
        Level level = Loader.loadFromFile("src/test/resources/twogoals.txt", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that loader returns a null level if level file has invalid tile
    public void loadInvalidLevelInvalidTile(){
        Level level = Loader.loadFromFile("src/test/resources/invalidtile.txt", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that loader returns a null level if level file has an invalid height
    public void loadInvalidLevelInvalidHeight(){
        Level level = Loader.loadFromFile("src/test/resources/invalidheight.txt", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that loader returns a null level if level file has an invalid height
    public void loadInvalidLevelNoGoal(){
        Level level = Loader.loadFromFile("src/test/resources/nogoal.txt", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that loader returns a null level if level file has an invalid height
    public void loadInvalidLevelNoPlayer(){
        Level level = Loader.loadFromFile("src/test/resources/noplayer.txt", 200, 10, app);
        assertNull(level);
    }


    @Test
    // Check that loader returns a null level if level file has two starting locations 
    public void loadInvalidLevelTwoStartingLocations(){
        Level level = Loader.loadFromFile("src/test/resources/twoplayers.txt", 200, 10, app);
        assertNull(level);
    }

    @Test
    // Check that all levels from config file are loaded 
    public void loadAllLevelsCorrectSize() {
        List<Level> levels = Loader.loadAllFiles(app, "src/test/resources/config.json");
        assertNotNull(levels);
        assert(levels.size() == 2);
    }

    @Test
    // Test loading single valid file, check that the file is not null
    public void loadSingleLevelNotNull() {
        Level level = Loader.loadFromFile("src/test/resources/level1.txt", 10, 10, app);
        assertNotNull(level);
    }

    @Test
    // Check that a valid level loaded contains the right items of every type
    public void loadSingleLevelCorrectItems1() {
        Level level = Loader.loadFromFile("src/test/resources/level1.txt", 10, 10, app);
        assert(level.getBombs().size() == 0);
        assert(level.getBrokenWalls().size() == 23);
        assert(level.getEnemies().size() == 2);
        assert(level.getExplosionTiles().size() == 0);
        assertNotNull(level.getGoal());
        assertNotNull(level.getPlayer());
    }
    
    @Test
    // Check that another valid level loaded contains the right items of every type
    public void loadSingleLevelCorrectItems2() {
        Level level = Loader.loadFromFile("src/test/resources/level2.txt", 10, 10, app);
        assert(level.getBombs().size() == 0);
        assert(level.getBrokenWalls().size() == 1);
        assert(level.getEnemies().size() == 0);
        assert(level.getExplosionTiles().size() == 0);
        assertNotNull(level.getGoal());
        assertNotNull(level.getPlayer());
    }

}
