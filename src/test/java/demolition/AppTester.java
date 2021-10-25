package demolition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import processing.core.PApplet;

public class AppTester {
    protected App app;

    @BeforeEach
    public void setup(){
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        this.app = app;
    }

    @AfterEach 
    public void teardown(){
        app.exitCalled();

    }
}
