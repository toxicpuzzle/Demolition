package demolition;

import java.util.List;
import processing.core.PApplet;
import java.util.HashMap;

public class Player extends DirectionalObject implements Movable {

    private static int yHeadOffset = 16;
    private int lives;
   
    
    public Player(int lives, int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, animations);
        this.lives = lives;
        this.width = 32; // WARNING: Hard code
        this.height = 32;
        // Need to find a way to load all of the animations in
    }

    

    @Override
    public void draw(PApplet app) {
        app.image(currentFrame, xPos, yPos-yHeadOffset);
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    @Override
    public void tick(int currentTime) {

        if (collideWithEnemy() || collideWithExplosion()) { 
            this.lives--;
            System.out.println("You lost a life!");
            // INSERT automatic restart here
        }

        // Animate the object
        if (currentTime >= lastDisplayedTime + currentAnimation.getFrameDuration() || justChangedDirection){
            justChangedDirection = false;
            this.currentFrame = currentAnimation.getNextFrame();
            lastDisplayedTime = currentTime;
        } 
    }
    
    public boolean collideWithEnemy() {
        List<Enemy> enemies = this.currentLevel.getEnemies();
        for (Enemy e: enemies){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 

    public boolean collideWithExplosion() {
        List<ExplosionTile> explosions = this.currentLevel.getExplosionTiles();
        for (ExplosionTile e: explosions){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 

    public void placeBomb(PApplet app){
        Bomb bomb = (Bomb) Sprites.BOMB.make(this.xPos, this.yPos, app);
        bomb.setCurrentLevel(this.currentLevel);
        bomb.setApp(app);
        currentLevel.addObject(bomb);
    }
    
    @Override
    public void moveUp() {
        int oldY = this.yPos;
        int oldX = this.xPos;
        Direction oldDirection = this.direction;
        
        this.yPos -= 32;
    
        if (collideWithSolid()){
            resetPosition(oldX, oldY, oldDirection);
        } else {
            this.direction = direction.UP;
            justChangedDirection = true;
        }

        updateCurrentAnimation();
    }

    @Override
    public void moveRight() {
        int oldY = this.yPos;
        int oldX = this.xPos;
        Direction oldDirection = this.direction;
        
        this.xPos += 32;

        if (collideWithSolid()){
            resetPosition(oldX, oldY, oldDirection);
        } else {
            this.direction = direction.RIGHT;
            justChangedDirection = true;
        }

        updateCurrentAnimation();
    }

    @Override
    public void moveDown() {
        int oldY = this.yPos;
        int oldX = this.xPos;
        Direction oldDirection = this.direction;
        
        this.yPos += 32;

        if (collideWithSolid()){
            resetPosition(oldX, oldY, oldDirection);
        } else {
            this.direction = direction.DOWN;
            justChangedDirection = true;
        }

        updateCurrentAnimation();
    }

    @Override
    public void moveLeft() {
        int oldY = this.yPos;
        int oldX = this.xPos;
        Direction oldDirection = this.direction;
        
        this.xPos -= 32;
        this.direction = direction.LEFT;

        if (collideWithSolid()) {
            resetPosition(oldX, oldY, oldDirection);
        } else {
            justChangedDirection = true;
        }
        
        updateCurrentAnimation();
    }

    
}
