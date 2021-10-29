package demolition;

import java.util.HashMap;
import java.util.List;
import processing.core.PApplet;
import java.util.ArrayList;
public abstract class Enemy extends MovingObject implements Movable {

    
    protected int lastWalked;
    protected boolean justChangedDirection;
    protected int yHeadOffset = 16;
    protected int yStarting;
    protected int xStarting;
    protected int walkTimer;
    protected List<Direction> directionsTried;

     /**Default constructor for enemyObjects, creates animations, and sets all starting directions for objects to be down 
     * @param x x coord to create the object
     * @param y y coord to create the object
     * @param animations animations corresponding to each direction the directional object is moving
    */
    public Enemy(int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, animations);
        this.yStarting = y;
        this.xStarting = x;
        this.width = 32; // WARNING: Hard code
        this.height = 32;
        this.directionsTried = new ArrayList<Direction>();
        // Need to find a way to load all of the animations in
    }
    @Override
    
    /**Draws the enemy's current frame on screen
     * @param app the app object associated with the enemy
     */
    public void draw(PApplet app) {
        app.image(currentFrame, xPos, yPos-yHeadOffset);
    }

    /**Moves the enemy up */
    @Override
    public void moveUp() {
        this.yPos -= 32;
    }

    /**Moves the enemy down */
    @Override
    public void moveDown() {
        this.yPos += 32;
    }
    
    /**Moves the enemy right */
    @Override
    public void moveRight() {
        this.xPos += 32;
    }

    /**Moves the enemy left */
    @Override
    public void moveLeft() {
        this.xPos -= 32;
    }

    /**Automatically walks the enemy in the appropriate direction/changes its coords and 
     * updates its direction when it hits a wall based on the specific enemy's concrete 
     * implementation of the abstract movementstrategy method */
    protected void walk(){
        // keep trying random direction until clear path opens up and animation updates
        int oldX = this.xPos;
        int oldY = this.yPos;
        Direction oldDirection = this.direction;

        switch(direction){
            case DOWN: 
                moveDown();
                break;
            case UP: 
                moveUp();
                break;
            case LEFT: 
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break; 
        }

        // TODO: Change it so that this only checks collision with tiles and not hard bodies such as the player -> temp fix

        if (collideWithSolid()){

            // Add the current direction tried to the failed list
            if (!directionsTried.contains(oldDirection)){ directionsTried.add(direction);} //Won't need .equals() for enums since ther is only one instance
            resetPosition(oldX, oldY, oldDirection);
            
            if (directionsTried.size() >= 4){
                justChangedDirection = false;
                directionsTried.clear();
                return;
            }

            // Ensures the new direction is one that has not yet tried
            Direction newDirection = getDirectionStrategy();
            while (directionsTried.contains(newDirection)){
                newDirection = getDirectionStrategy();
            }

            // Set the object's direction to the new direction chosen and try walking in that direction;
            this.direction = newDirection;
            justChangedDirection = true;
            walk();
        } else {
            directionsTried.clear();
            updateCurrentAnimation();
            return;
        }
    }

    /**@return the direction for the enemy to move in when it is facing a solid object */
    public abstract Direction getDirectionStrategy();

    /**@return true if the enemy is colliding with an explosion */
    //TODO: put this method as well as the collide with solid method into the gameobject class since it is also used elsewhere e.g. in explosion class.
    public boolean collideWithExplosion() {
        List<ExplosionTile> explosions = this.currentLevel.getExplosionTiles();
        for (ExplosionTile e: explosions){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 

    /**Updates the state of the enemy based on if has touched an explosion or if it has changed direction/walked */
    @Override
    public void tick() {
        walkTimer++;
        
        
        // System.out.println(this.currentAnimation.getFrameNumber());

        if (collideWithExplosion()){
            this.isRemoved = true;
        }

        float secondsBetweenFrames = (float)currentAnimation.getFrameDuration()/1000;
        if (this.walkTimer >= secondsBetweenFrames*App.FPS*4){
            walk();
            if (justChangedDirection){
                this.currentFrame = this.currentAnimation.getFrameAtIndex(0); 
            }
            // ERROR: Moves before updating frame -> Fixed
            walkTimer = 0;
        }

        super.tick();
    }
    
}
