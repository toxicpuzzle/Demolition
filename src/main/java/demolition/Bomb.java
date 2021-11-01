package demolition;

import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;
/**Bomb object that can be placed by the player, and which spawns an explosion that destroys walls*/
public class Bomb extends MovingObject{
    private int timeSincePlaced;
    private PApplet app; // TEMP

    /**Constructor for bomb objects, creates animations, and the time placed for the bomb. 
     * @param x x coord to create the object
     * @param y y coord to create the object
     * @param animations animations corresponding to each direction the directional object is moving
     * @param timePlaced is deprecated parameter for when the bomb was placed
    */
    public Bomb(int x, int y, HashMap<Direction, Animation> animations, int timePlaced){
        super(x, y, animations);
    }

    /**Sets the app object that is used by the bomb 
     * @param app the app instance used to run the game.
    */
    public void setApp(PApplet app){
        this.app = app;
    }

    /**@param level the level in which the bomb is to be placed */
    //Repetitive code, try to remove
    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    // /**Draws the bomb onto the screen */
    // @Override
    // public void draw(PApplet app) {
    //     super.draw(app);
    // }

    /**Updates the bomb so that it will be removed and generate an explosion if all of its animation frames have been played */
    @Override
    public void tick() {
        if (this.isRemoved){
            return;
        }
        timeSincePlaced++;

        float secondsBetweenFrames = (float) currentAnimation.getFrameDuration()/1000;
        if (timeSincePlaced >= secondsBetweenFrames*App.FPS*8){
            Explosion explosion = new Explosion(this.xPos, this.yPos, this.currentLevel, app);
            explosion.addAllExpTiles();
            this.isRemoved = true;
        }

        super.tick();        
    }
    
}
