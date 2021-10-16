package demolition;

import processing.core.PImage;
import java.util.List;

public class ExplosionTile extends GameObject {
    private Level currentLevel;
    private int explosionTime;
    private static final int duration = 500;
    public ExplosionTile(int x, int y, PImage startingImage){
        super(x, y, false, startingImage);
       
    } 

    public void setExplosionTime(int time){
        this.explosionTime = time;
    }

    // TODO: Remove duplicate code
    public boolean collideWithBroken(){
        List<BrokenWall> brokenWalls = currentLevel.getBrokenWalls();
        for (GameObject object: brokenWalls){
            if (this.collisionWith(object) && !object.isRemoved){
                return true;
            }
        }
        return false;
    }

    public boolean collideWithSolid(){
        List<SolidWall> solidWalls = currentLevel.getSolidWalls();
        for (GameObject object: solidWalls){
            if (this.collisionWith(object) && !object.isRemoved){
                return true;
            }
        }
        return false;
    }

    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    @Override
    public void tick(int currentTime) {
        // TODO Auto-generated method stub
        if (currentTime >= explosionTime + duration){
            this.remove();
        } 
    }
    
}
