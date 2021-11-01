package demolition;

import java.util.HashMap;

public class EnemyYellow extends Enemy {

    /**Construct yellow enemies
     * @param x x coord 
     * @param y y coord
     * @param animations animation objects for the yellow enemy
     */
    public EnemyYellow(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    /**@return direction for the enemy to move in if the enemy collides with a solid object */
    @Override
    public Direction getDirectionStrategy(){
        return this.direction.clockwise();
    }
    
}
