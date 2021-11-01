package demolition;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;


public class TestAnimation {

    //NB: Animation is also tested in concrete implementations of game objects e.g. enemy and player classes.
    @Test
    // Check animation constructor successfully creates an animation when arguments are provided
    public void notNull(){
        List<PImage> animationImages = new ArrayList<PImage>();
        Animation animation = new Animation(animationImages, 0, 10);
        assertNotNull(animation);
    }
    @Test
    // Test null is returned when frame in animation is out of bounds
    public void gettingInvalidAnimationFrame(){
        List<PImage> animationImages = new ArrayList<PImage>();
        Animation animation = new Animation(animationImages, 0, 10);
        assertNull(animation.getFrameAtIndex(1));
    }
}
