package demolition;

import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;

public class Bomb extends MovingObject{
    private int timeSincePlaced;
    private PApplet app; // TEMP

    public Bomb(int x, int y, HashMap<Direction, Animation> animations, int timePlaced){
        super(x, y, animations);
    }

    public void setApp(PApplet app){
        this.app = app;
    }

    //Repetitive code, try to remove
    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    @Override
    public void draw(PApplet app) {
        super.draw(app);
    }

    @Override
    public void tick() {
        if (this.isRemoved){
            return;
        }
        // System.out.println(this.currentAnimation.getFrameNumber());
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
