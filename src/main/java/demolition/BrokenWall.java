package demolition;

import processing.core.PImage;

public class BrokenWall extends GameObject {

    /**Construct brokenwalls
     * @param x x coord 
     * @param y y coord
     * @param startingImage image for brokenwall
     */
    public BrokenWall(int x, int y, PImage startingImage){ 
        super(x, y, true, startingImage);
    } 

}
