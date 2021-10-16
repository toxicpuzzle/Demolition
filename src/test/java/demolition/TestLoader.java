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
    public void testCreatingSprite(){
        ProcessingRELP sketch = new ProcessingRELP();
        PApplet.runSketch(new String[]{"--location=0,0", ""}, sketch);
        GameObject sprite = Sprites.BOMB.make(100, 100, null);
        System.out.println(sprite);
        // assertNotNull(sprite);

    }
}
