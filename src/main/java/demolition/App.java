package demolition;

import processing.core.PApplet;
import processing.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import processing.core.PFont;
import processing.core.PImage;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;
    public static String configFileName = "config.json";
    public boolean alreadyPressed = false;
    public GameManager game;
    public PImage player;
    public PImage timer;


    public App() {
        
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS); 
        List<Level> levels = Loader.loadAllFiles(this, configFileName);
        this.game = new GameManager(levels);
        this.player = loadImage("src/main/resources/icons/player.png");
        this.timer = loadImage("src/main/resources/icons/clock.png");
    }

    public void setConfig(String name){
        configFileName = name;
    }

    public void drawText(String word, int x, int y, int size){
        PFont text = createFont("src/main/resources/PressStart2P-Regular.ttf", 16);
        textFont(text, size);
        textAlign(CENTER);
        fill(0,0,0);
        text(word, x, y);
    }

    public void draw() {
        background(255, 128, 0);
        Level currentLevel = game.getCurrentLevel();
        int m = millis();
        
        if (!game.hasWonAll() && !game.hasGameOver()){
            for(GameObject object: currentLevel.getGameObjects()){
                if (!object.isRemoved){
                    object.draw(this);
                    object.tick(m);
                }   
            }

            image(timer, 260, 18);
            image(player, 100, 18);
            drawText(String.valueOf(currentLevel.getTimeLeft()), 330, 45, 20);
            drawText(String.valueOf(currentLevel.getPlayer().getLives()), 165, 45, 20);
            currentLevel.tick(m);
            game.tick();
        }
    
        if (game.hasWonAll()){
            drawText("YOU WON", width/2, height/2, 30);
        } else if (game.hasGameOver()){
            drawText("GAME OVER", width/2, height/2, 30);
        }
        
        
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
            }
        }
        alreadyPressed = true;
        
    }
    public static void main(String[] args) {
        PApplet.main("demolition.App");
        SpriteFactory sf = new SpriteFactory();
        List<String> strings = sf.getSpriteImageDirectories("player", "player");
        System.out.print(strings);
    }
}
