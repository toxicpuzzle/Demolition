package demolition;

import processing.core.PApplet;
import processing.core.PImage; 

public abstract class GameObject {
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;
    protected boolean isRemoved;
    protected boolean isSolid;
    protected PImage currentFrame;
    protected int lastDisplayedTime;

    /**Creates the gameobject
     * @param x the x coordinate for the gameobject to be created
     * @param y the y coordinate for hte gameobject to be created
     * @param isSolid whether the object is breakable 
     * @param startingImage the image of the object to be displayed (when it is initiated)
     */
    public GameObject(int x, int y, boolean isSolid, PImage startingImage){
        this.xPos = x;
        this.yPos = y;
        this.currentFrame = startingImage;
        this.width = startingImage.width;
        this.height = startingImage.height;
        this.isRemoved = false;
        this.isSolid = isSolid;
    }

    /**Draws the object using the app object */
    public void draw(PApplet app){
        app.image(currentFrame, xPos, yPos);
    }

    public void tick(){};

    /**Sets the object to be removed */
    public void remove() {
        this.isRemoved = true;
    } 

    /**@return the x coord of the object */
    public int getX(){
        return this.xPos;
    }

    /**@return the y coord of the object */
    public int getY(){
        return this.yPos;
    }

    /**@return the x coord of the object */
    public void setX(int x){
        this.xPos = x;
    }

    /**@return the y coord of the object */
    public void setY(int y){
        this.yPos = y;
    }

    /**@return the width of the object */
    public int getWidth(){
        return this.width;
    }

    /**@return the height of the object */
    public int getHeight(){
        return this.height;
    }

    /**@return true if the object has been removed */
    public boolean hasRemoved(){
        return this.isRemoved;
    }

    /**@return true if the object is solid i.e. cannot be walked through */
    public boolean isSolid(){
        return this.isSolid;
    }

    /**Changes the current image of the gameobject
     * @param image the image to be changed to
    */
    public void setCurrentFrame(PImage image){
        this.currentFrame = image;
    }

    /**@param object the object to check collision with
     * @return true if the current object is colliding with the specified object */
    public boolean collisionWith(GameObject object) {
        int objectLeft = object.xPos;
        int objectRight = object.xPos + object.width;
        int objectTop = object.yPos;
        int objectBottom = object.yPos + object.height;
        int thisTop = this.yPos;
        int thisBottom = this.yPos + this.height;
        int thisRight = this.xPos + this.width;
        int thisLeft = this.xPos;

        if (thisRight > objectLeft && thisLeft < objectRight && thisBottom > objectTop && thisTop < objectBottom) {
            return true;
        }
        return false;
    }
}
