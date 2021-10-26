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
    
    public void draw(PApplet app) {
        app.image(currentFrame, xPos, yPos-yHeadOffset);
    }

    @Override
    public void moveUp() {
        this.yPos -= 32;
    }

    @Override
    public void moveDown() {
        this.yPos += 32;
    }
    

    @Override
    public void moveRight() {
        this.xPos += 32;
    }

    @Override
    public void moveLeft() {
        this.xPos -= 32;
    }

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

    public abstract Direction getDirectionStrategy();

    public boolean collideWithExplosion() {
        List<ExplosionTile> explosions = this.currentLevel.getExplosionTiles();
        for (ExplosionTile e: explosions){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 

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
