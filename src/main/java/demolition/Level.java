package demolition;

import java.util.List;
import java.util.ArrayList;

/**The Level object holds the time limit for the level, and all objects in the level, 
 * and can reset all objects to their starting position/status and return objects in the level*/
public class Level {
    private List<Enemy> enemies;
    private List<SolidWall> solidWalls;
    private List<BrokenWall> brokenWalls;
    private List<Bomb> bombs;
    private List<Empty> empty;
    private Goal goal;
    private Player player;
    private List<ExplosionTile> explosionTiles;
    private int timeLeft;
    private int timer;
    
    /**Constructor for level */
    public Level(){
        this.enemies = new ArrayList<Enemy>();
        this.solidWalls = new ArrayList<SolidWall>();
        this.brokenWalls = new ArrayList<BrokenWall>();
        this.empty = new ArrayList<Empty>();
        this.bombs = new ArrayList<Bomb>();
        this.explosionTiles = new ArrayList<ExplosionTile>();
    }

    /**Adds a gameobject to the level
     * @param object the game object to be added
    */
    public void addObject(GameObject object){
        if (object instanceof Enemy){
            this.enemies.add((Enemy) object);
        } else if (object instanceof SolidWall){
            this.solidWalls.add((SolidWall) object);
        } else if (object instanceof BrokenWall){
            this.brokenWalls.add((BrokenWall) object);
        } else if (object instanceof Empty){
            this.empty.add((Empty) object);
        } else if (object instanceof Goal){
            this.goal = (Goal) object;
        } else if (object instanceof Player){
            this.player = (Player) object;
        } else if (object instanceof Bomb){
            this.bombs.add((Bomb) object);
        } else if (object instanceof ExplosionTile){
            this.explosionTiles.add((ExplosionTile) object);
        }
    }

    /**Resets the time left for the level, used when the level is restarted/initiated
     * @param seconds seconds left for the level
     */
    public void setTimeLeft(int seconds){
        this.timeLeft = seconds;
    }

    /**@return the seconds left for the level */
    public int getTimeLeft(){
        return this.timeLeft;
    }

    /**@return the time left in the current level in seconds */
    public int tick(){
        timer++;
        if (this.timer >= App.FPS*1){
            this.timeLeft--;
            timer = 0;
        }
        return this.timeLeft;
    }

    /**@return all objects with the isSolid attribute true in the level */
    public List<GameObject> getSolids(){
        List<GameObject> objects = this.getGameObjects();
        List<GameObject> returned = new ArrayList<GameObject>();
        for (GameObject object: objects){
            if (object.isSolid){
                returned.add(object);
            }
        }
        return returned;
    }
    

    /**@return all bombs that have been placed either removed or not removed in this level */
    public List<Bomb> getBombs(){
        return this.bombs;
    }

    /**@return all enemies in the level that have either been removed or not removed in the level */
    public List<Enemy> getEnemies(){
        return this.enemies;
    }   

    /**@return all solid walls that cannot be broken */
    public List<SolidWall> getSolidWalls(){
        return this.solidWalls;
    }

    /**@return all solid walls that can be broken, including ones that have already been removed */
    public List<BrokenWall> getBrokenWalls(){
        return this.brokenWalls;
    }
    
    /**@return the player in the level */
    public Player getPlayer(){
        return this.player;
    }

    /**@return returns all background/empty tiles in the level */
    public List<Empty> getEmpty(){
        return this.empty;
    }
    
    /**@return returns the goal tile in the current level */
    public Goal getGoal(){
        return this.goal;
    }

    /**@return all explosion tiles in current level assuming they have not been removed */
    public List<ExplosionTile> getExplosionTiles(){
        List<ExplosionTile> explosionTiles = this.explosionTiles;
        List<ExplosionTile> newList = new ArrayList<ExplosionTile>();
        for (ExplosionTile tile: explosionTiles){
            if (!tile.isRemoved){
                newList.add(tile);
            }
        }
        this.explosionTiles = newList;
        return this.explosionTiles;
    }

    /**@return all game objects in the current level in the order they are to be displayed */
    public List<GameObject> getGameObjects(){
        List<GameObject> GameObjects = new ArrayList<GameObject>();
        GameObjects.addAll(empty);
        GameObjects.addAll(solidWalls);
        GameObjects.addAll(brokenWalls);
        GameObjects.add(goal);
        GameObjects.addAll(explosionTiles);
        GameObjects.addAll(bombs);
        
        List<GameObject> sorted = new ArrayList<GameObject>();
        sorted.addAll(enemies);
        sorted.add(player);
        
        int minIndex = 0;
        int minY = 1000; 
        while (sorted.size() > 0) {
            // Find the object with the smallest y;
            for (int i = 0; i < sorted.size(); i++){
                GameObject current = sorted.get(i);
                if (current.yPos < minY){
                    minY = current.yPos;
                    minIndex = i;
                }
            }
            GameObjects.add(sorted.get(minIndex));
            sorted.remove(minIndex);
            minY = 1000;
        }
        

        return GameObjects;
    }

    /**Sets all gameobjects in current level to be isRemoved = true*/
    public void removeAllObjects(){
        List<GameObject> gameObjects = getGameObjects();
        for (GameObject object: gameObjects){
            object.isRemoved = true;
        }
    }

    /**Resets the current level by restoring all enemies, broken walls, and removing all explosion tiles and bombs, and also resetting the player's position/animation
     * @return true if the reset method is executed successfully.
    */
    public boolean reset(){

        // Restore all gameobjects
        for (GameObject object: this.getGameObjects()){
            object.isRemoved = false;
        }

        List<Enemy> enemies = this.enemies;
        for(Enemy e: enemies){
            e.isRemoved = false;
            e.xPos = e.xStarting;
            e.yPos = e.yStarting;
            e.direction = Direction.DOWN;
            e.currentAnimation = e.animations.get(Direction.DOWN);
        }

        List<BrokenWall> brokenWalls = this.brokenWalls;
        for(BrokenWall w: brokenWalls){
            w.isRemoved = false;
        }

        this.explosionTiles.clear();
        this.bombs.clear();

        player.xPos = player.getXStarting();
        player.yPos = player.getYStarting();
        player.currentAnimation = player.animations.get(Direction.DOWN);
        return true;
    }

}

