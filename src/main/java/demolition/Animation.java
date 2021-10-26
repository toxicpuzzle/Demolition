package demolition;

import processing.core.PImage;

import java.util.HashMap;
import java.util.List;

public class Animation {
    private int currFrameIndex;
    private int frameLength;
    private int frameDuration;
    private List<PImage> animationFrames; //Could change this to url of animation frames
    private Direction direction;

    public Animation(List<PImage> frames, int frameCount, int frameDuration, Direction direction){
        this.animationFrames = frames;
        this.frameDuration = frameDuration;
        this.direction = direction;
        frameLength = this.animationFrames.size();
        currFrameIndex = 0;
    }

    public Animation(List<PImage> frames, int frameCount, int frameDuration){
        this(frames, frameCount, frameDuration, Direction.NONE);
    }

    public int getFrameDuration(){
        return this.frameDuration;
    }

    public PImage getNextFrame(){
        return animationFrames.get(currFrameIndex++%frameLength);
    }

    public Direction getDirection(){
        return this.direction;
    }

    public int getFrameNumber(){
        return this.currFrameIndex;
    }

}
