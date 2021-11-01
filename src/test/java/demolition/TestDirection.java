package demolition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

public class TestDirection {
    @Test
    public void checkCyclingDirectionClockwise(){
        Direction d = Direction.DOWN;
        d = d.clockwise();
        assertEquals(Direction.LEFT, d);
        d = d.clockwise();
        assertEquals(Direction.UP, d);
        d = d.clockwise();
        assertEquals(Direction.RIGHT, d);
        d = d.clockwise();
        assertEquals(Direction.DOWN, d);
    }

    @Test
    public void checkRandomDirection(){
        Direction d = Direction.RIGHT.random();
        assertNotNull(d);
    }

    
}
