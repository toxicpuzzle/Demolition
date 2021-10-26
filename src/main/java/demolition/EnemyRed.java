package demolition;

import java.util.HashMap;
import java.util.List;

public class EnemyRed extends Enemy {

    public EnemyRed(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    public Direction getDirectionStrategy(){
        return this.direction.random();
    }

}
