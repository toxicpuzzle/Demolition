package demolition;

import java.util.List;
import processing.core.PApplet;
import processing.core.PImage; 
import java.util.HashMap;

public abstract class DirectionalObject extends GameObject{
    protected HashMap<Direction, Animation> animations; 
    protected Animation currentAnimation;
    protected Direction direction;
    protected boolean justChangedDirection;
    protected Level currentLevel;
    
    public DirectionalObject(int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, false, animations.get(Direction.DOWN).getNextFrame()); 
        this.animations = animations;
        this.direction = Direction.DOWN;
        updateCurrentAnimation();
    }

    public void setCurrentLevel(Level currentLevel){
        this.currentLevel = currentLevel;
    }
    
    public boolean collideWithSolid(){
        List<GameObject> otherObjects = currentLevel.getGameObjects();
        otherObjects.remove(this);
        for (GameObject object: otherObjects){
            if (this.collisionWith(object) && !object.isRemoved && object.isSolid){
                // System.out.println(object.getClass());
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

    public Animation getCurrenAnimation(){
        return this.currentAnimation;
    }
}

// Maybe just remove directional object altogether