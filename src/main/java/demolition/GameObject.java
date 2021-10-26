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

    public GameObject(int x, int y, boolean isSolid, PImage startingImage){
        this.xPos = x;
        this.yPos = y;
        this.currentFrame = startingImage;
        this.width = startingImage.width;
        this.height = startingImage.height;
        this.isRemoved = false;
        this.isSolid = isSolid;
    }

    public void draw(PApplet app){
        app.image(currentFrame, xPos, yPos);
    }

    public void tick(){};

    public void remove() {
        this.isRemoved = true;
    } 

    public int getX(){
        return this.xPos;
    }

    public int getY(){
        return this.yPos;
    }

    public void setX(int x){
        this.xPos = x;
    }

    public void setY(int y){
        this.yPos = y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean hasRemoved(){
        return this.isRemoved;
    }

    public boolean isSolid(){
        return this.isSolid;
    }

    public void setCurrentFrame(PImage image){
        this.currentFrame = image;
    }

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
