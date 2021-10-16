package demolition;

import java.util.HashMap;
import java.util.List;

public class EnemyRed extends Enemy {

    public EnemyRed(int x, int y, HashMap<Direction, Animation> animations) {
        super(x, y, animations);
    }

    public void walk(){
        // keep trying random direction until clear path opens up and animation updates
        int oldX = this.xPos;
        int oldY = this.yPos;
        Direction oldDirection = this.direction;

        switch(direction){
            case DOWN: 
                this.yPos += 32;
                break;
            case UP: 
                this.yPos -= 32;
                break;
            case LEFT: 
                this.xPos -= 32;
                break;
            case RIGHT:
                this.xPos += 32;
                break; 
        }

        // TODO: Change it so that this only checks collision with tiles and not hard bodies such as the player -> temp fix
        if (collideWithSolid()){
            resetPosition(oldX, oldY, oldDirection);
            Direction randomDirection = direction.random();
            this.direction = randomDirection;
            justChangedDirection = true;
            walk();
        } else {
            updateCurrentAnimation();
            return;
        }
    }
    
}
