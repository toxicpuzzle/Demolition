package demolition;

import processing.core.PImage; 

public class SolidWall extends GameObject {

    
    /**Constructor for solidwall objects
     * @param x x coord to create the object
     * @param y y coord to create the object
     * @param startingImage the image for the solidwall
    */
    public SolidWall(int x, int y, PImage startingImage){ 
        super(x, y, true, startingImage);
    } 
    
}
