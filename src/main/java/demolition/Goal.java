package demolition;

import processing.core.PImage;

/**Goal tile that moves the player to the next level if they step on it*/
public class Goal extends GameObject {

    /**Construct goal objects
     * @param x x coord 
     * @param y y coord
     * @param startingImage the image for the goal tile
     */
    public Goal(int x, int y, PImage startingImage){ 
        super(x, y, false, startingImage);
    } 
}
