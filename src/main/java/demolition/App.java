package demolition;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;
    public static String configFileName = "config.json";
    // public int test;
    public boolean alreadyPressed = false;

    public GameManager game;

    public App() {
        
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS); // Nullpointerexception when I try to run this in test suite, but works when I do gradle run
        // test = 2; // ERROR: You need to automati
        this.game = new GameManager(Loader.loadAllFiles(this, configFileName));
        
    }

    public void setConfig(String name){
        configFileName = name;
    }

    public void draw() {
        background(255, 200, 0);
        Level currentLevel = game.getCurrentLevel();
        int m = millis();
        
        for(GameObject object: currentLevel.getGameObjects()){
            if (!object.isRemoved){
                object.draw(this);
                object.tick(m);
            }   
        }

        game.tick();
        
        
    }   

    @Override
    public void keyReleased() {
        alreadyPressed = false;
    }

    @Override
    public void keyPressed() {
        Level currentLevel = game.getCurrentLevel();
        Player p = currentLevel.getPlayer();
    
        if (!alreadyPressed){
            switch (keyCode){
                case DOWN: 
                    p.moveDown(); 
                    break;
                case UP: 
                    p.moveUp(); 
                    break;
                case RIGHT: 
                    p.moveRight(); 
                    break;
                case LEFT: 
                    p.moveLeft();    
                    break;
                case 32:
                    p.placeBomb(this);
                    break;
                case ENTER:
                    game.toNextLevel();
                    break;
            }
        }
        alreadyPressed = true;
        
    }
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
