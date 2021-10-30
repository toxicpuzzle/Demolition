package demolition;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.zip.CheckedInputStream;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.BeforeEach;

public class TestExplosion extends AppTester {

    private static double explosionDuration = 0.5;
    private Explosion explosion; 
    private Level level;
    private static int posX = 3*32;
    private static int posY = 5*32;

    @BeforeEach
    public void getExplosion(){
        this.level = Loader.loadFromFile("src/test/resources/empty.txt", 100, 100, app);
        this.explosion = new Explosion(posX, posY, this.level, app);
        explosion.addAllExpTiles();    
    }

    private void createExplosionAt(int x, int y, String fileUsed){
        this.level = Loader.loadFromFile(fileUsed, 100, 100, app);
        this.explosion = new Explosion(x, y, this.level, app);
        explosion.addAllExpTiles();    
    }

    private boolean checkExplosionTileCoords(Explosion explosion, List<List<Integer>> coords){
        List<GameObject> explosionTiles = explosion.getExplosionTiles();
        for(GameObject explosionTile: explosionTiles){
            boolean tileMatchesExpected = false;
            // System.out.println("Checking the " + counter + "th tile.");
            // System.out.printf("Current explosion tile has coords: %d, %d\n", explosionTile.xPos, explosionTile.yPos);
            for (List<Integer> coord: coords){
                // System.out.printf("Coords: %d, %d\n", coord.get(0),  coord.get(1));
                if (explosionTile.xPos == coord.get(0) && explosionTile.yPos == coord.get(1)){
                    tileMatchesExpected = true;
                    break;
                }
            }
            if (!tileMatchesExpected) {return false;}
        }
        return true;
    }

    //Test breakable walls break (use gameamanger) when they hit the explosion
    @Test
    public void notNull(){
        assertNotNull(this.explosion);
    } 

    //Testing solid wall blockages

    @Test
    // Check explosions are created in all directions (2 tile distance when there is nothing blocking it in any direction)
    public void correctTilesNoBlockage(){
        // Check size is correct
        List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
        assertEquals(9, explosionTiles.size());

        // Check that the tile coords are correct
        List<List<Integer>> coords = Lists.newArrayList();
        //Horizontal tiles
        coords.add(Lists.newArrayList(32*1, 32*5));
        coords.add(Lists.newArrayList(32*2, 32*5));
        coords.add(Lists.newArrayList(32*3, 32*5));
        coords.add(Lists.newArrayList(32*4, 32*5));
        coords.add(Lists.newArrayList(32*5, 32*5));

        //Vertical tiles
        coords.add(Lists.newArrayList(32*3, 32*3));
        coords.add(Lists.newArrayList(32*3, 32*4));
        // coords.add(Lists.newArrayList(32*3, 32*5));
        coords.add(Lists.newArrayList(32*3, 32*6));
        coords.add(Lists.newArrayList(32*3, 32*7));
        assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    @Test
    public void correctTilesTopBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosiontopblockage.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(8, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
         coords.add(Lists.newArrayList(32*1, 32*5));
         coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*4, 32*5));
         coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
         coords.add(Lists.newArrayList(32*3, 32*4));
         // coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*6));
         coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    @Test
    public void correctTilesLeftBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosionleftblockage.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(7, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*1, 32*5));
         coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*4, 32*5));
         coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
         coords.add(Lists.newArrayList(32*3, 32*4));
         // coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*6));
         coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    @Test
    public void correctTilesRightBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosionrightblockage.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(6, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*1, 32*5));
         coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*4, 32*5));
        //  -> Removed tile coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
         coords.add(Lists.newArrayList(32*3, 32*4));
         // coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*6));
         coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    @Test
    public void correctTilesBottomBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosionbottomblockage.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(5, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*1, 32*5));
         coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*4, 32*5));
        //  -> Removed tile coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
         coords.add(Lists.newArrayList(32*3, 32*4));
         // coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*6));
        // -> Removed tile  coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    @Test
    public void correctTilesFullBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosionfullblockage.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(1, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*1, 32*5));
        //  coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
        //  coords.add(Lists.newArrayList(32*4, 32*5));
        //  -> Removed tile coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
        //  coords.add(Lists.newArrayList(32*3, 32*4));
         // coords.add(Lists.newArrayList(32*3, 32*5));
        //  coords.add(Lists.newArrayList(32*3, 32*6));
        // -> Removed tile  coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));
    }

    //Testing broken wall blockages (no need in all direction as we have already done that for the solidblocks) -> Move the testing walls not removed part into gamemanager

    @Test
    // Check that the explosion tiles only expand as far as 1 tile if they hit a broken wall in all directions
    public void correctTilesBrokenFullBlockage(){
         // Check size is correct
         createExplosionAt(posX, posY, "src/test/resources/explosionbrokenwall.txt");
         List<GameObject> explosionTiles = this.explosion.getExplosionTiles();
         assertEquals(5, explosionTiles.size());
 
         // Check that the tile coords are correct
         List<List<Integer>> coords = Lists.newArrayList();
         //Horizontal tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*1, 32*5));
         coords.add(Lists.newArrayList(32*2, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*4, 32*5));
        //  -> Removed tile coords.add(Lists.newArrayList(32*5, 32*5));
 
         //Vertical tiles
        //  -> Removed tile coords.add(Lists.newArrayList(32*3, 32*3));
         coords.add(Lists.newArrayList(32*3, 32*4));
         coords.add(Lists.newArrayList(32*3, 32*5));
         coords.add(Lists.newArrayList(32*3, 32*6));
        // -> Removed tile  coords.add(Lists.newArrayList(32*3, 32*7));
         assertTrue(checkExplosionTileCoords(this.explosion, coords));

        //  //* Check broken tiles are still there by checking size of broken tiles
        //  int unBrokenWalls = 0;
        //  List<BrokenWall> walls = this.level.getBrokenWalls();
        //  for (int i = 0; i < walls.size(); i++){
        //      if (!walls.get(i).isRemoved) 
        //         unBrokenWalls++;
        //  }
        // //  assertEquals(4,unBrokenWalls);
    }

    @Test
    //Test explosion tiles dissappear after specified time
    public void testExplosionDuration(){
        for (GameObject et: this.explosion.getExplosionTiles()){
            for (int i = 0; i < App.FPS*explosionDuration; i++){
                et.tick();
            }
        }

        for (GameObject et: this.explosion.getExplosionTiles()){
            assertTrue(et.isRemoved);
        }
    }

}
