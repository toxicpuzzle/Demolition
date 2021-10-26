package demolition;

import java.util.HashMap;

public class EnemyYellow extends Enemy {


    public EnemyYellow(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    public Direction getDirectionStrategy(){
        return this.direction.clockwise();
    }
    
}
