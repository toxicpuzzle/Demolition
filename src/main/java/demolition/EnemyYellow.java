package demolition;

import java.util.HashMap;

public class EnemyYellow extends Enemy {


    public EnemyYellow(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    /**@return direction for the enemy to move in if the enemy collides with a solid object */
    @Override
    public Direction getDirectionStrategy(){
        return this.direction.clockwise();
    }
    
}
