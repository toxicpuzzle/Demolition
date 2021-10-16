package demolition;

import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;

public class Bomb extends DirectionalObject{
    private int timePlaced;
    private PApplet app; // TEMP

    public Bomb(int x, int y, HashMap<Direction, Animation> animations, int timePlaced){
        super(x, y, animations);
        this.timePlaced = timePlaced;
        lastDisplayedTime = timePlaced;
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
        // TODO Auto-generated method stub
        super.draw(app);
        // System.out.println("Currently drawing bomb's frame: " + this.currentAnimation.getFrameNumber());
    }

    @Override
    public void tick(int currentTime) {
        // TODO Auto-generated method stub
        if (this.isRemoved){
            return;
        }

        if (currentTime >= timePlaced + currentAnimation.getFrameDuration()*8){
            // this.currentFrame = currentAnimation.getNextFrame();
            // lastDisplayedTime = currentTime;
            Explosion explosion = new Explosion(this.xPos, this.yPos, this.currentLevel, app);
            explosion.addAllExpTiles();
            this.isRemoved = true;
        }

        if (currentTime >= lastDisplayedTime + currentAnimation.getFrameDuration()){
            this.currentFrame = currentAnimation.getNextFrame();
            lastDisplayedTime = currentTime;
        }

        
    }
    
}
