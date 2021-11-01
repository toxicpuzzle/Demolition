package demolition;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

//NB: Spritefactory has already been tested thoroughly in other class specific tests, here just check all objects are not null
public class TestSpriteFactory extends AppTester{
    @Test
    // Check player made is not a null pointer
    public void notNullPlayer(){
        assertNotNull(SpriteFactory.makePlayer(0, 0, app));
    }
    
    @Test
    // Check enemies made are not a null pointer
    public void notNullEnemy(){
        assertNotNull(SpriteFactory.makeEnemyRed(0, 0, app));
        assertNotNull(SpriteFactory.makeEnemyYellow(0, 0, app));
    }

    @Test
    // Check explosions made are not a null pointer
    public void notNullExplosions(){
        assertNotNull(SpriteFactory.makeExplosionCentre(0, 0, app));
        assertNotNull(SpriteFactory.makeExplosionVertical(0, 0, app));
        assertNotNull(SpriteFactory.makeExplosionHorizontal(0, 0, app));
    }

    @Test
    // Check walls made are not a null pointer
    public void notNullWalls(){
        assertNotNull(SpriteFactory.makeBrokenWall(0, 0, app));
        assertNotNull(SpriteFactory.makeSolidWall(0, 0, app));
    }

    @Test
    // Check tiles made are not a null pointer
    public void notNullTile(){
        assertNotNull(SpriteFactory.makeEmpty(0, 0, app));
        assertNotNull(SpriteFactory.makeGoal(0, 0, app));
    }

    @Test
    // Check bombs made are not a null pointer
    public void notNullBomb(){
        assertNotNull(SpriteFactory.makeBomb(0, 0, app));
    }
}

