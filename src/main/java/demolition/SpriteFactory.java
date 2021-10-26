package demolition;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.FilenameFilter;

public class SpriteFactory {

    public static final int frameDuration = 200; //In milliseconds

    public static List<String> getSpriteImageDirectories(String spriteName, String filePrefix){
        List<String> images = new ArrayList<String>();

        File directory = new File("src/main/resources/" + spriteName);
        File[] filesFound = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.startsWith(filePrefix) && name.endsWith(".png");
            }
        }); 

        for (File file: filesFound){
            images.add(file.getPath());
        }
        
        return images;
    }

    public static List<PImage> convertPathsToPImages(List<String> paths, PApplet app){
        List<PImage> images = new ArrayList<>();
        for(String path: paths) { images.add(app.loadImage(path)); }
        return images;
    }

    public static Animation createAnimation(String spriteName, String animationName, PApplet app){
        List<String> directories = getSpriteImageDirectories(spriteName, animationName);
        List<PImage> frames = convertPathsToPImages(directories, app);
        Animation animation = new Animation(frames, frames.size(), frameDuration);
        return animation;
    }   

    /** @return Returns animations for movable objects like enemies and the player */
    public static HashMap<Direction, Animation> createAnimationsForMovable(String spriteName, PApplet app){
        HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();
        animations.put(Direction.DOWN, createAnimation(spriteName, spriteName + "_down", app));
        animations.put(Direction.UP, createAnimation(spriteName, spriteName + "_up", app));
        animations.put(Direction.LEFT, createAnimation(spriteName, spriteName + "_left", app));
        animations.put(Direction.RIGHT, createAnimation(spriteName, spriteName + "_right", app));
        return animations;
    }

    public static Player makePlayer(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("player", app);
        Player player = new Player(10, x, y, animations);
        return player;
    }

    public static EnemyRed makeEnemyRed(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("red_enemy", app);
        EnemyRed enemy = new EnemyRed(x, y, animations);
        return enemy;
    }

    public static EnemyYellow makeEnemyYellow(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("yellow_enemy", app);
        EnemyYellow enemy = new EnemyYellow(x, y, animations);
        return enemy;
    }

    public static Bomb makeBomb(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();
        animations.put(Direction.DOWN,createAnimation("bomb", "bomb", app));
        Bomb bomb = new Bomb(x, y, animations, app.millis());
        return bomb;
    }

    public static SolidWall makeSolidWall(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/wall/solid.png");
        SolidWall tile = new SolidWall(x, y, tileImage);
        return tile;
    }

    public static BrokenWall makeBrokenWall(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/broken/broken.png");
        BrokenWall tile = new BrokenWall(x, y, tileImage);
        return tile;
    }

    public static Goal makeGoal(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/goal/goal.png");
        Goal tile = new Goal(x, y, tileImage);
        return tile;
    }

    public static Empty makeEmpty(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/empty/empty.png");
        Empty tile = new Empty(x, y, tileImage);
        return tile;
    }

    public static ExplosionTile makeExplosionCentre(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/centre.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    public static ExplosionTile makeExplosionHorizontal(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/horizontal.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    public static ExplosionTile makeExplosionVertical(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/vertical.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    public static GameObject makeByChar(char c, int x, int y, PApplet app){
        switch(c){
            case 'W' : return makeSolidWall(x, y, app);
            case 'B' : return makeBrokenWall(x, y, app);
            case ' ' : return makeEmpty(x, y, app);
            case 'G' : return makeGoal(x, y, app);
            case 'P' : return makePlayer(x, y, app);
            case 'Y' : return makeEnemyYellow(x, y, app);
            case 'R' : return makeEnemyRed(x, y, app);
        }
        return makeEmpty(x, y, app); // return empty tile if cannot find specified char
    }
}
