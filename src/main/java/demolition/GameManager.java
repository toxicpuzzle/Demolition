package demolition;

import java.util.List;
import processing.core.PApplet;
import processing.core.PFont;
public class GameManager {
    private int currentLevelIndex;
    private List<Level> levels;
    private boolean won;
    private boolean gameOver;
    private Level currentLevel;

    /**@param levels all levels the gamemanager needs to handle */
    public GameManager(List<Level> levels){
        this.levels = levels;
        this.currentLevelIndex = 0;
        this.currentLevel = levels.get(currentLevelIndex);
        setup();
    }

    /**Removes all broken walls that are colliding with an explosion in a level
     * @return the number of brokenwalls removed
    */
    public int removeBrokenWalls(){
        int counter = 0;
        for (GameObject object: currentLevel.getBrokenWalls()){
            for (GameObject explosion: currentLevel.getExplosionTiles()){
                if (object.collisionWith(explosion)){
                    object.remove();
                    counter++;
                }
            }
        }
        return counter;
    }

    /**Implements gamelogic to check if the player will lose a life, has won, run out of time, andgo to gameover or win screen or continue the game */
    public void tick() {

        Player player = this.currentLevel.getPlayer();
        if (player.collideWithEnemy() || player.collideWithExplosion()){
            currentLevel.reset();
            player.setLives(player.getLives()-1);
            if (player.getLives() <= 0){
                goToGameOver();
                return;
            }
        }

        if (this.hasWon()){
            if (currentLevelIndex == this.levels.size()-1){
                goToWin();
                return;
            }
            this.toNextLevel();
        }

        
        if (currentLevel.getTimeLeft() <= 0){
            goToGameOver();
        }

        removeBrokenWalls();
    }

    /**Removes all objects from level and triggers gameover conditions */
    public void goToGameOver(){
        this.currentLevel.removeAllObjects();
        this.gameOver = true;
    }

    /**Removes all objects from level and triggers gamewin conditions */
    public void goToWin(){
        this.currentLevel.removeAllObjects();
        this.won = true;
    }

    /**@return true if the player has won by walking over the gaol tile */
    public boolean hasWon(){
        Player p = currentLevel.getPlayer();
        if (p.collisionWith(currentLevel.getGoal())){
            return true;
        }
        return false;
    }

    /**@return true if the player has won all levels in the game */
    public boolean hasWonAll(){
        return this.won;
    }

    /**@return true if the level's gameover attribute is true */
    public boolean hasGameOver(){
        return this.gameOver;
    }

    //Setup current/futurelevel level (Fully intialise enemies and players)
    /**Sets up all moving objects in the level by allowing the gamemanager to access all enemies and players from the current level */
    public void setup(){
        List<Enemy> enemies = currentLevel.getEnemies();
        Player p = currentLevel.getPlayer();
        
        for (Enemy e: enemies){
            e.setCurrentLevel(currentLevel);
        }
        p.setCurrentLevel(currentLevel);
    }

    /**@return the level object the gamemanager is currently handling */
    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    /**@return the index (starts at 0) for the current level in the list of levels */
    public int getCurrentLevelIndex(){
        return this.currentLevelIndex;
    }

    /**Goes to the next level and sets it up to be handled by the present gamemanager instance */
    public void toNextLevel(){
        this.currentLevelIndex++;
        int lives = currentLevel.getPlayer().getLives();
        this.currentLevel = levels.get(currentLevelIndex);
        currentLevel.getPlayer().setLives(lives);
        setup();
    }


}
