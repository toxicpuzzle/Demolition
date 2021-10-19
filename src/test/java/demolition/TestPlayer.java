package demolition;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TestPlayer {

    @BeforeEach
    public void setup(){
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
    }
    
    @Test 
    public void checkPlayerMovement() {
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
