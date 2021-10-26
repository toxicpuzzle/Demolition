package demolition;

import java.util.List;
import processing.core.PApplet;
import processing.core.PImage; 
import java.util.HashMap;

public abstract class MovingObject extends GameObject{
    protected HashMap<Direction, Animation> animations; 
    protected Animation currentAnimation;
    protected Direction direction;
    protected boolean justChangedDirection;
    protected Level currentLevel;
    protected int timer;
    
    public MovingObject(int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, false, animations.get(Direction.DOWN).getNextFrame()); 
        this.animations = animations;
        this.direction = Direction.DOWN;
        updateCurrentAnimation();
    }

    public void tick(){
        this.timer++;
        // Animate the object
        float secondsBetweenFrames = (float)currentAnimation.getFrameDuration()/1000;
        if ((this.timer > (secondsBetweenFrames * App.FPS))){
            this.currentFrame = currentAnimation.getNextFrame();
            this.timer = 0;
        } 

        if (this.justChangedDirection){
            this.currentFrame = currentAnimation.getFrameAtIndex(0);
            this.justChangedDirection = false;            
        }
    }

    public void setCurrentLevel(Level currentLevel){
        this.currentLevel = currentLevel;
    }
    
    public boolean collideWithSolid(){
        List<GameObject> otherObjects = currentLevel.getGameObjects();
        otherObjects.remove(this);
        for (GameObject object: otherObjects){
            if (this.collisionWith(object) && !object.isRemoved && object.isSolid){
                return true;
            }
        }
        return false;
    }

    public void resetPosition(int oldX, int oldY, Direction oldDirection){
        this.xPos = oldX;
        this.yPos = oldY;
        this.direction = oldDirection;
    }

    public Direction getDirection() { return this.direction;}

    
    // Check animation has changed as a result of direction
    public void updateCurrentAnimation() {
        this.currentAnimation = this.animations.get(direction);
    }

    public Animation getCurrentAnimation(){
        return this.currentAnimation;
    }

}

