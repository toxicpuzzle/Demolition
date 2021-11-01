package demolition;

import processing.core.PImage;

/**Empty background tile that players and enemies stand on*/
public class Empty extends GameObject {

    /**Construct emptytiles
     * @param x x coord 
     * @param y y coord
     * @param startingImage image for emptytile
     */
    public Empty(int x, int y, PImage startingImage){ 
        super(x, y, false, startingImage);
    } 

}
