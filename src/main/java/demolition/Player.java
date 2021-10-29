package demolition;

import java.util.List;
import processing.core.PApplet;
import java.util.HashMap;

public class Player extends MovingObject implements Movable {

    private static int yHeadOffset = 16;
    private int lives;
    private int xStarting;
    private int yStarting;
    private int timer; 
    
    /**Constructor for player objects
     * @param lives the number of lives 
     * @param x x coord to create the object
     * @param y y coord to create the object
     * @param animations animations corresponding to each direction the directional object is moving
    */
    public Player(int lives, int x, int y, HashMap<Direction, Animation> animations){
        super(x, y, animations);
        this.lives = lives;
        this.width = 32; // WARNING: Hard code
        this.height = 32;
        this.xStarting = x;
        this.yStarting = y;
        // Need to find a way to load all of the animations in
    }
    
    /**@return the object's X starting position i.e. when it was first constructed on screen -> used to restart levels */
    public int getXStarting(){
        return this.xStarting;
    }

    /**@return the object's Y starting position i.e. when it was first constructed on screen -> used to restart levels */        
    public int getYStarting(){
        return this.yStarting;
    }

    /**Draws the object onto the screen but with an offset since the player object is larger than a normal tile 
     * @param app the app object that is used to load the game
    */
    @Override
    public void draw(PApplet app) {
        app.image(currentFrame, xPos, yPos-yHeadOffset);
    }

    /**@param lives The player's new lives*/
    public void setLives(int lives){
        this.lives = lives;
    }

    /**@return the player's current lives count */
    public int getLives(){
        return this.lives;
    }

    //! Might be redundant so just remove
    @Override
    public void tick() {
        this.timer++;
        // Animate the object
        float secondsBetweenFrames = (float)currentAnimation.getFrameDuration()/1000;
        if ((this.timer > (secondsBetweenFrames * App.FPS)) || justChangedDirection){
            justChangedDirection = false;
            this.currentFrame = currentAnimation.getNextFrame(); //TODO: Fix issue of skipping frames when changing directions.
            this.timer = 0;
        } 
    }
    
    /**@return true if the player is colliding with an enemy that has not been removed from the screen */
    public boolean collideWithEnemy() {
        List<Enemy> enemies = this.currentLevel.getEnemies();
        for (Enemy e: enemies){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 
    
    /**@return true if the player is colliding with an explosion that has not yet expired */
    public boolean collideWithExplosion() {
        List<ExplosionTile> explosions = this.currentLevel.getExplosionTiles();
        for (ExplosionTile e: explosions){
            if (this.collisionWith(e) && !e.isRemoved){
                return true;
            }
        }
        return false;
    } 

    /**Places a bomb onto the level the player is in
     * @param app the applet that is used to load the game
     */
    public void placeBomb(PApplet app){
        Bomb bomb = SpriteFactory.makeBomb(this.xPos, this.yPos, app);
        bomb.setCurrentLevel(this.currentLevel);
        bomb.setApp(app);
        currentLevel.addObject(bomb);
    }
    
    /**Moves the player up and resets the player's current direction as well as animation */
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
            justChangedDirection = !(this.direction == oldDirection);
        }

        updateCurrentAnimation();
    }

    /**Moves the player right and resets the player's current direction as well as animation */
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
            justChangedDirection = !(this.direction == oldDirection);
        }

        updateCurrentAnimation();
    }
    
    /**Moves the player down and resets the player's current direction as well as animation */
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
            justChangedDirection = !(this.direction == oldDirection);
        }

        updateCurrentAnimation();
    }

    /**Moves the player left and resets the player's current direction as well as animation */
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
            justChangedDirection = !(this.direction == oldDirection);
        }
        
        updateCurrentAnimation();
    }

    
}
