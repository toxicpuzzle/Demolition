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
     * @param animations animation objects for the explosion tile
     */
    public ExplosionTile(int x, int y, PImage startingImage){
        super(x, y, false, startingImage);
       
    } 

    // // TODO: Remove duplicate code -> problem is that this is from moving object -> problem with adding level attribute to gameobject is that not all gameobjects need to know level -> null pointer
    // public boolean collideWithBroken(){
    //     List<BrokenWall> brokenWalls = currentLevel.getBrokenWalls();
    //     for (GameObject object: brokenWalls){
    //         if (this.collisionWith(object) && !object.isRemoved){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean collideWithSolid(){
    //     List<SolidWall> solidWalls = currentLevel.getSolidWalls();
    //     for (GameObject object: solidWalls){
    //         if (this.collisionWith(object) && !object.isRemoved){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean collideWithBroken(){
        List<BrokenWall> brokenWalls = currentLevel.getBrokenWalls();
        return this.collideWithObjects(brokenWalls);
    }

    public boolean collideWithSolid(){
        List<SolidWall> solidWalls = currentLevel.getSolidWalls();
        return this.collideWithObjects(solidWalls);
    }
    
    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    @Override
    public void tick() {
        timeSinceExplosion++;
        float secondsLasted = (float) duration/1000;
        if (timeSinceExplosion >= secondsLasted*App.FPS){
            this.remove();
        } 
    }
    
}
