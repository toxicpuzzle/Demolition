package demolition;

import processing.core.PImage;

public class Icon extends GameObject {

    public Icon(int x, int y, PImage icon){
        super(x , y, false, icon); 
    }

    @Override
    public void tick(int currentTime) {
        return;
    }
    
}
