package demolition;

import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestLoader {
    
    @Test
    public void simpleTest() {
        assertEquals(480, App.HEIGHT);
    }

    @Test
    public void checkFrameRate(){
        // How can I create an applet here?
        assertEquals(App.FPS, 60);
    }

    @Test
    public void testLoadingFile() {
        App.main((String[]) null);

        // int size = app.allLevels.size(); // 
        // assertEquals(size, 3);
    }

    @Test 
    public void basicTest() {
        // Create an instance of your application
        App app = new App();

        // Set the program to not loop automatically
        app.noLoop();

        // Set the path of the config file to use
        // app.setConfig("src/test/resources/config.json");

        // Tell PApplet to create the worker threads for the program
        PApplet.runSketch(new String[] {"App"}, app);

        // Call App.setup() to load in sprites
        app.setup();

        // Set a 1 second delay to ensure all resources are loaded
        app.delay(1000);

        GameObject sprite = Sprites.BOMB.make(100, 100, app);
        System.out.println(sprite);
        assertNotNull(sprite);

        // Call draw to update the program.
        app.draw();

        // Call keyPressed() or similar

        // Call draw again to move onto the next frame
        app.draw();

        // Verify the new state of the program with assertions
    }
}
