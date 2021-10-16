package demolition;

import processing.core.PImage; 

public class SolidWall extends GameObject {

    public SolidWall(int x, int y, PImage startingImage){ //TODO: Is it necssary to have this class if we the only thing it does is handles the sold setting? we should handle image loading here too.
        super(x, y, true, startingImage);
    } //TODO: Mayb

    @Override
    public void tick(int currentTime) {
        // TODO Auto-generated method stub
        
    }
    
}
