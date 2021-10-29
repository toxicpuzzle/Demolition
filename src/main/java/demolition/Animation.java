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

    /**Constructor for all animation objects
     * @param frames list containing all images to be used in the frames, in the order they are to be played
     * @param frameCount the number of frames in the animation (Deprecated)
     * @param frameDuration the length in milliseconds for each frame to be played
     * @param direction the direction associated with the animation.
     */
    public Animation(List<PImage> frames, int frameCount, int frameDuration, Direction direction){
        this.animationFrames = frames;
        this.frameDuration = frameDuration;
        this.direction = direction;
        frameLength = this.animationFrames.size();
        currFrameIndex = 0;
    }

    /**Constructor for gameobjects with no directional attribute */
    public Animation(List<PImage> frames, int frameCount, int frameDuration){
        this(frames, frameCount, frameDuration, Direction.NONE);
    }

    /**@return the duration for each frame to be played*/
    public int getFrameDuration(){
        return this.frameDuration;
    }

    /**@return the image object for the next frame in the current animation */
    public PImage getNextFrame(){
        return animationFrames.get(currFrameIndex++%frameLength);
    }

    /**@return the frame at a particular index in the current animationsFrame list */
    public PImage getFrameAtIndex(int index){
        if (index > frameLength){
            return null;
        } else{
            return this.animationFrames.get(index);
        }
    }

    /**@return the direction in which the current animation is to be placed */
    public Direction getDirection(){
        return this.direction;
    }

    /**@return the current frame number that is being played */
    public int getFrameNumber(){
        return this.currFrameIndex%frameLength;
    }

}
