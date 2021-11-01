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
    
    /**Default constructor for movingobjects, creates animations, and sets all starting directions for objects to be down 
     * @param x x coord to create the object
     * @param y y coord to create the object
     * @param animations animations corresponding to each direction the directional object is moving
    */
    public MovingObject(int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, false, animations.get(Direction.DOWN).getFrameAtIndex(0)); 
        this.animations = animations;
        this.direction = Direction.DOWN;
        updateCurrentAnimation();
    }

    /**Updates how the object is displayed on screen based on if the object has changed direction 
     * or if the frameDuration has elapsed between when the object was last drawn  */
    public void tick(){
        this.timer++;
        // Animate the object
        float secondsBetweenFrames = (float)currentAnimation.getFrameDuration()/1000;
        if ((this.timer >= (secondsBetweenFrames * App.FPS))){
            this.currentFrame = currentAnimation.getNextFrame();
            this.timer = 0;
        } 

        if (this.justChangedDirection){
            this.currentFrame = currentAnimation.getFrameAtIndex(0);
            this.justChangedDirection = false;            
        }
    }

    /**Sets the movingObject's level so it knows other objects that it could reference for interactions
     * @param currentLevel the level object containing all other objects in the level;
     */
    public void setCurrentLevel(Level currentLevel){
        this.currentLevel = currentLevel;
    }


    /**Checks if the object has collided with an object that has the "isSolid" attribute equal to true and is not removed
     * @return true if the object has collided with an object that is solid and is not removed else false
     */
    public boolean collideWithSolid(){
        List<GameObject> solids = this.currentLevel.getSolids();
        return this.collideWithObjects(solids);
    }


    /**@return true if the moving is colliding with an explosion that has not yet expired */
    public boolean collideWithExplosion() {
        List<ExplosionTile> explosions = this.currentLevel.getExplosionTiles();
        return this.collideWithObjects(explosions);
    } 

    /**Sets the object's position to an old X, old Y, old Direction
     * @param oldX the object's new X coord
     * @param oldY the object's new Y coord
     * @param oldDirection the object's new Direction
     */
    public void resetPosition(int oldX, int oldY, Direction oldDirection){
        this.xPos = oldX;
        this.yPos = oldY;
        this.direction = oldDirection;
    }

    /**@return the object's current direction */
    public Direction getDirection() { return this.direction;}

    
    // Check animation has changed as a result of direction
    /**Updates the current animation to match the object's current direction */
    public void updateCurrentAnimation() {
        this.currentAnimation = this.animations.get(direction);
    }

    /**@return the current animation object that the object is playing on screen */
    public Animation getCurrentAnimation(){
        return this.currentAnimation;
    }

}

