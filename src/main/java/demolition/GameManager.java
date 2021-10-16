package demolition;

import java.util.List;;


public class GameManager extends App{
    private int currentLevelIndex;
    private List<Level> levels;
    // private Level currentLevelCopy;
    private Level currentLevel;
    private Level template;

    public GameManager(List<Level> levels){
        this.levels = levels;
        this.currentLevelIndex = 0;
        this.currentLevel = levels.get(currentLevelIndex);
        this.template = currentLevel;
         //TODO: Implement copying mecahnism
        setup();
    }

    public Level copyOf(Level level){
        return null;
    }

    public void removeBrokenWalls(){
        for (GameObject object: currentLevel.getBrokenWalls()){
            for (GameObject explosion: currentLevel.getExplosionTiles()){
                if (object.collisionWith(explosion)){
                    // System.out.println("hit!");
                    object.remove();
                }
            }
        }
    }

    public void tick() {
        if (this.hasWon()){
            System.out.println("WON!");
            this.toNextLevel();
        }
        removeBrokenWalls();
    }

    public boolean hasWon(){
        Player p = currentLevel.getPlayer();
        if (p.collisionWith(currentLevel.getGoal())){
            return true;
        }
        return false;
    }

    public boolean hasDied(){
        return false;
    }

    //Setup current/futurelevel level (Fully intialise enemies and players)
    public void setup(){
        List<Enemy> enemies = currentLevel.getEnemies();
        Player p = currentLevel.getPlayer();
        
        for (Enemy e: enemies){
            e.setCurrentLevel(currentLevel);
        }
        p.setCurrentLevel(currentLevel);
    }

    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    public void toNextLevel(){
        this.currentLevelIndex++;
        this.currentLevel = levels.get(currentLevelIndex);
        setup();
    }

    public void restartLevel(){
        // TODO: Find way to copy the start level
    }

}
