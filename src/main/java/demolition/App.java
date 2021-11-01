package demolition;

import processing.core.PApplet;
import java.util.List;
import processing.core.PFont;
import processing.core.PImage;

/**Runs the game*/
public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;
    public static String configFileName = "config.json";
    public boolean alreadyPressed = false;
    public GameManager game;
    public PImage player;
    public PImage timer;

    public App() {}

    /**Sets up the width and height of the window to play the game in */
    public void settings() {size(WIDTH, HEIGHT);}

    /**Loads in the levels from the config file and the UI icons */
    public void setup() {
        frameRate(FPS); 
        List<Level> levels = Loader.loadAllFiles(this, configFileName);
        this.game = new GameManager(levels);
        this.player = loadImage("src/main/resources/icons/player.png");
        this.timer = loadImage("src/main/resources/icons/clock.png");
    }

    /**Changes the config file, which is used during testing 
     * @param name The name/directory of the config file*/
    public void setConfig(String name){configFileName = name;}

    /**Writes a text on screen 
     * @param word the string/word to be written
     * @param x the x coord of the centre of the word
     * @param y the y coord of the centre of the word
     * @param size font size*/
    public void drawText(String word, int x, int y, int size){
        PFont text = createFont("src/main/resources/PressStart2P-Regular.ttf", 16);
        textFont(text, size);
        textAlign(CENTER);
        fill(0,0,0);
        text(word, x, y);
    }

    /**Draws all elements onto the screen */
    public void draw(){
        background(255, 128, 0);
        Level currentLevel = game.getCurrentLevel();

        if (!game.hasWonAll() && !game.hasGameOver()){
            for(GameObject object: currentLevel.getGameObjects()){
                if (!object.isRemoved){
                    object.draw(this);
                    object.tick();
                }   
            }

            image(timer, 260, 18);
            image(player, 100, 18);
            drawText(String.valueOf(currentLevel.getTimeLeft()), 330, 45, 20);
            drawText(String.valueOf(currentLevel.getPlayer().getLives()), 165, 45, 20);
            currentLevel.tick();
            game.tick();
        }
    
        if (game.hasWonAll()){ drawText("YOU WON", width/2, height/2, 30);} 
        else if (game.hasGameOver()){ drawText("GAME OVER", width/2, height/2, 30);}
    }   

    /**Sets the alreadyPressed attribute to false on key release */
    @Override
    public void keyReleased() {alreadyPressed = false;}

    /**Moves the player depending which keys has bene pressed, will not move the player until the key is released again. */
    @Override
    public void keyPressed() {
        Level currentLevel = game.getCurrentLevel();
        Player p = currentLevel.getPlayer();
    
        if (!alreadyPressed){
            if (keyCode == DOWN) {p.moveDown(); }
            else if (keyCode == UP) {p.moveUp(); }
            else if (keyCode == RIGHT) {p.moveRight(); }
            else if (keyCode == LEFT) {p.moveLeft(); }
            else if (keyCode == 32) {p.placeBomb(this);}
        }
        alreadyPressed = true;   
    }

    /** @param args the command line arguments for the app*/
    public static void main(String[] args) { PApplet.main("demolition.App");}
}