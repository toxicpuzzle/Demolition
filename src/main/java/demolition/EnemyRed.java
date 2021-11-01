package demolition;

import java.util.HashMap;
import java.util.List;


/**Concrete implementation of enemy that moves in a random direction upon hitting a wall*/
public class EnemyRed extends Enemy {
    
    /**Construct red enemies
     * @param x x coord 
     * @param y y coord
     * @param animations animation objects for the enemy
     */
    public EnemyRed(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    /**@return direction for the enemy to move in if the enemy collides with a solid object */
    public Direction getDirectionStrategy(){
        return this.direction.random();
    }

}
