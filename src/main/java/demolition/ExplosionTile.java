package demolition;

import processing.core.PImage;
import java.util.List;

public class ExplosionTile extends GameObject {
    private Level currentLevel;
    private static final int duration = 500;
    private int timeSinceExplosion;

    /**Construct explosion tiles 
     * @param x coord 
     * @param y y coord
     * @param startingImage image for the explosion tile
     */
    public ExplosionTile(int x, int y, PImage startingImage){
        super(x, y, false, startingImage);
       
    } 

    /**@return true if the explosiontile is colliding with a broken tile */
    public boolean collideWithBroken(){
        List<BrokenWall> brokenWalls = currentLevel.getBrokenWalls();
        return this.collideWithObjects(brokenWalls);
    }
    
    /**@return true if the explosiontile is colliding with a solid unbreakable wall */
    public boolean collideWithSolid(){
        List<SolidWall> solidWalls = currentLevel.getSolidWalls();
        return this.collideWithObjects(solidWalls);
    }
    
    /**@return the level object that the explosiontile resides in */
    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    /**@param level The level to set the current explosiontile to belong to */
    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    /**Ticks the explosion tile so that it will disappear after 0.5 seconds */
    @Override
    public void tick() {
        timeSinceExplosion++;
        float secondsLasted = (float) duration/1000;
        if (timeSinceExplosion >= secondsLasted*App.FPS){
            this.remove();
        } 
    }
    
}
