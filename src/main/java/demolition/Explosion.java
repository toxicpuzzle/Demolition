package demolition;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Explosion{
    private List<ExplosionTile> left;
    private List<ExplosionTile> right;
    private List<ExplosionTile> up;
    private List<ExplosionTile> down;
    private Level currentLevel;
    private ExplosionTile centre;
    private int explosionStart;

    // New explosion will be made by the bomb class.
    // NOTE: Explosion is a collection of explosiontiles
    // NB: Pimage is dummy var to avoid nullpointer right now
    public Explosion(int x, int y, Level level, PApplet app) {
        this.centre = (ExplosionTile) SpriteFactory.makeExplosionCentre(x, y, app);
        this.left = new ArrayList<ExplosionTile>();
        this.right = new ArrayList<ExplosionTile>();
        this.up = new ArrayList<ExplosionTile>();
        this.down = new ArrayList<ExplosionTile>();
        this.currentLevel = level;
        centre.setCurrentLevel(this.currentLevel);
        
        
        //TODO: COde repetition here - Could make separate collission functions for different types of walls.
        //Check if new tile collides, and check if broken tile. 

        // Add the current level to all explosion tiles that were made
        makeInDirection(Direction.LEFT, app);
        makeInDirection(Direction.RIGHT, app);
        makeInDirection(Direction.UP, app);
        makeInDirection(Direction.DOWN, app);

        this.explosionStart = app.millis(); 
        this.setAllExplosionTimes();
        // System.out.println(this.getExplosionTiles().size());
    }

    private void setAllExplosionTimes(){
        List<ExplosionTile> tiles = new ArrayList<ExplosionTile>();
        
        for (GameObject tile: this.getExplosionTiles()){
            tiles.add((ExplosionTile) tile);
        }
        for (ExplosionTile tile: tiles){
            tile.setExplosionTime(this.explosionStart);
        }
    }

    // Helper function for constructor
    private void makeInDirection(Direction direction, PApplet app){
        int xCurrent = this.centre.getX();
        int yCurrent = this.centre.getY();

        for (int i = 0; i < 2; i++) {
            int xPrevious = xCurrent;
            int yPrevious = yCurrent;
            
            ExplosionTile currentTile = SpriteFactory.makeExplosionHorizontal(xCurrent, yCurrent, app);
            
            //Add tile depending on direction
            switch(direction){
                case UP: 
                    yCurrent -= 32;
                    currentTile = SpriteFactory.makeExplosionVertical(xCurrent, yCurrent, app);
                    this.up.add(currentTile);
                    break;
                case DOWN: 
                    yCurrent += 32;
                    currentTile = SpriteFactory.makeExplosionVertical(xCurrent, yCurrent, app);
                    this.down.add(currentTile);
                    break;
                case LEFT: 
                    xCurrent -= 32;
                    currentTile = SpriteFactory.makeExplosionHorizontal(xCurrent, yCurrent, app);
                    this.left.add(currentTile);
                    break;
                case RIGHT: 
                    xCurrent += 32;
                    currentTile = SpriteFactory.makeExplosionHorizontal(xCurrent, yCurrent, app);
                    this.right.add(currentTile);
                    break;
            }

            currentTile.setCurrentLevel(currentLevel);

            // Remove the last two if this tile collides with any solid object, and add a endie tile (replaces previous tile)
            if (currentTile.collideWithSolid()){
                currentTile.remove();
                break;
            } else if (currentTile.collideWithBroken()) {
                break;
            }
        } 
    }

    public List<GameObject> getExplosionTiles(){
        List<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.addAll(this.left);
        gameObjects.addAll(this.right);
        gameObjects.addAll(this.up);
        gameObjects.addAll(this.down);
        gameObjects.add(this.centre);
        return gameObjects;
    }

    public void addAllExpTiles(){
        List<GameObject> tiles = this.getExplosionTiles();
        for (GameObject object: tiles){
            currentLevel.addObject(object);
        }
    }
    
}
