package demolition;

import java.util.List;

import org.checkerframework.checker.units.qual.min;

import jogamp.nativewindow.NativeWindowFactoryImpl;

import java.util.ArrayList;

public class Level {
    private List<Enemy> enemies;
    private List<SolidWall> solidWalls;
    private List<BrokenWall> brokenWalls;
    private List<Bomb> bombs;
    private List<Empty> empty;
    private Goal goal;
    private Player player;
    private List<ExplosionTile> explosionTiles;
    
    //Add bombs and explosions later

    public Level(){
        this.enemies = new ArrayList<Enemy>();
        this.solidWalls = new ArrayList<SolidWall>();
        this.brokenWalls = new ArrayList<BrokenWall>();
        this.empty = new ArrayList<Empty>();
        this.bombs = new ArrayList<Bomb>();
        this.explosionTiles = new ArrayList<ExplosionTile>();
    }

    // TODO: Refactor to only add game object and make it so getters handle casting, since everytime you want to add something to the level you will have to add new logic to level.
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

    public GameObject getObjectAt(int x, int y){
        List<GameObject> objects = this.getGameObjects();
        for (GameObject object: objects){
            if (object.xPos == x && object.yPos == y){
                return object;
            }
        }
        return null;
    }

    public List<Bomb> getBombs(){
        return this.bombs;
    }

    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    public List<SolidWall> getSolidWalls(){
        return this.solidWalls;
    }

    public List <BrokenWall> getBrokenWalls(){
        return this.brokenWalls;
    }
    
    public Player getPlayer(){
        return this.player;
    }

    public List<Empty> getEmpty(){
        return this.empty;
    }
    
    public Goal getGoal(){
        return this.goal;
    }

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

    // Gets all gameobjects in the order they are to be printed
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



    //public List <Explosion> getExplosions(){}
}

// T = new type, K = old type;
class Converter<T extends K, K> {
    private List<K> oldList;
    private Class newType;

    public Converter(List<K> oldList, Class newType){
        this.oldList = oldList;
        this.newType = newType;
    }

    @SuppressWarnings("unchecked")
    public List<T> toNewType(){
        List<T> newList = new ArrayList<T>();
        for (K element: oldList){
            if (element.getClass().getName().equals(newType.getName())){
                newList.add((T) element);
            }
        }
        return newList;
    }



    


}