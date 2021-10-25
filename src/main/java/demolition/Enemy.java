package demolition;

import java.util.HashMap;
import java.util.List;
import processing.core.PApplet;

public abstract class Enemy extends MovingObject implements Movable {

    
    protected int lastWalked;
    protected boolean justChangedDirection;
    protected int yHeadOffset = 16;
    protected int yStarting;
    protected int xStarting;

    public Enemy(int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, animations);
        this.yStarting = y;
        this.xStarting = x;
        this.width = 32; // WARNING: Hard code
        this.height = 32;
        // Need to find a way to load all of the animations in
    }
    @Override
    
    public void draw(PApplet app) {
        app.image(currentFrame, xPos, yPos-yHeadOffset);
    }

    @Override
    public void moveUp() {
        this.yPos -= 32;
        this.direction = direction.UP;
        updateCurrentAnimation();
    }

    @Override
    public void moveRight() {
        this.xPos += 32;
        this.direction = direction.RIGHT;
        updateCurrentAnimation();
    }

    @Override
    public void moveDown() {
        this.yPos += 32;
        this.direction = direction.DOWN;
        updateCurrentAnimation();
    }

    @Override
    public void moveLeft() {
        this.xPos -= 32;
        this.direction = direction.LEFT;
        updateCurrentAnimation();
    }

    public abstract void walk();

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
    public void tick(int currentTime) {
        // Duplicate from player : try to refactor

        if (collideWithExplosion()){
            this.isRemoved = true;
        }

        if (currentTime >= lastWalked + currentAnimation.getFrameDuration()*4){
            walk();
            lastWalked = currentTime;
        }

        if (currentTime >= lastDisplayedTime + currentAnimation.getFrameDuration() || justChangedDirection){
            justChangedDirection = false;
            this.currentFrame = currentAnimation.getNextFrame();
            lastDisplayedTime = currentTime;
        }

        
        
    }
    
}
