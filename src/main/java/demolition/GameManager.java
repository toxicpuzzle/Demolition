package demolition;

import java.util.List;
import processing.core.PApplet;
import processing.core.PFont;
public class GameManager extends App{
    private int currentLevelIndex;
    private List<Level> levels;
    private boolean won;
    private boolean gameOver;
    private Level currentLevel;

    public GameManager(List<Level> levels){
        this.levels = levels;
        this.currentLevelIndex = 0;
        this.currentLevel = levels.get(currentLevelIndex);

        setup();
    }

    // TODO: Add icons along with Papplet the ability to display the time and also lives for current player.
    public boolean addIcons(Icon lives, Icon timer){
        return true;
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
            if (currentLevelIndex == this.levels.size()-1){
                goToWin();
                return;
            }
            this.toNextLevel();
        }

        Player player = this.currentLevel.getPlayer();
        if (player.collideWithEnemy() || player.collideWithExplosion()){
            currentLevel.reset();
            player.setLives(player.getLives()-1);
            if (player.getLives() <= 0){
                goToGameOver();
                return;
            }
        }

        if (currentLevel.getTimeLeft() <= 0){
            goToGameOver();
        }

        removeBrokenWalls();
    }

    public void goToGameOver(){
        this.currentLevel.removeAllObjects();
        this.gameOver = true;
    }

    public void goToWin(){
        this.currentLevel.removeAllObjects();
        this.won = true;
    }

    public boolean hasWon(){
        Player p = currentLevel.getPlayer();
        if (p.collisionWith(currentLevel.getGoal())){
            return true;
        }
        return false;
    }

    public boolean hasWonAll(){
        return this.won;
    }

    public boolean hasGameOver(){
        return this.gameOver;
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
        int lives = currentLevel.getPlayer().getLives();
        this.currentLevel = levels.get(currentLevelIndex);
        currentLevel.getPlayer().setLives(lives);
        setup();
    }


}
