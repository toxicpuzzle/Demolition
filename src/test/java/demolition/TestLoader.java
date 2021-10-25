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
    // Check that a valid level loaded contains the right items of every type
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
