package demolition;

import java.util.List;
import java.util.Random;

/**Directional enum that helps keep track the direction an animation is played for
 * and the direction that enemies and players are currently facing
*/
public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE;

    /**Gets the direction clockwise to the current direction 
     * @return the direction clockwise to the current direction
    */
    public Direction clockwise() {
        switch(this){
            case LEFT: return UP;
            case RIGHT: return DOWN;
            case UP: return RIGHT;
            // case DOWN: return LEFT;
        }
        // return NONE;
        return LEFT;
    }

    /**Gets a random direction from the current direction 
     * @return a random direction 
    */
    public Direction random(){
        int rand = (int) (Math.random()*4 + 1);
        switch(rand){
            case 1: return UP;
            case 2: return DOWN;
            case 3: return RIGHT;
            // case 4: return LEFT;
        }
        // return NONE;
        return LEFT;
    }   
    
}
