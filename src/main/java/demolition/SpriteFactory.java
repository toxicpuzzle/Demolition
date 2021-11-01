package demolition;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.FilenameFilter;

/**Class that helps construct images for the constructors of GameObjects to 
 * help construct objects in one line without having to load in images repeatedly */
public class SpriteFactory {

    public static final int frameDuration = 200; //In milliseconds

    /**@param spriteName the name of the sprite used to search the directories (finding folder) 
     * @param filePrefix the prefix of the image for the sprite (finding file in folder)
     * @return a list of directories containing the images for the sprite
     */
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

    /**@param paths All image paths for the sprite, found from getSpriteImageDirectories
     * @param app The app object to load the PImages
     * @return list of images for the sprite object to create an animation
     */
    public static List<PImage> convertPathsToPImages(List<String> paths, PApplet app){
        List<PImage> images = new ArrayList<>();
        for(String path: paths) { images.add(app.loadImage(path)); }
        return images;
    }

    /**@param spriteName the name of the sprite used to search the directories (finding folder) 
     * @param animationName the name of the animation for the sprite e.g. up (file search)
     * @param app The app object used to load the PImages 
     * @return An animation object containing the images for that animation
     */
    public static Animation createAnimation(String spriteName, String animationName, PApplet app){
        List<String> directories = getSpriteImageDirectories(spriteName, animationName);
        List<PImage> frames = convertPathsToPImages(directories, app);
        Animation animation = new Animation(frames, frames.size(), frameDuration);
        return animation;
    }   

    /**@param spriteName the name of the sprite used to search the directories (finding folder) 
     * @param app The app object to load the PImages
     * @return Returns animations for movable objects like enemies and the player */
    public static HashMap<Direction, Animation> createAnimationsForMovable(String spriteName, PApplet app){
        HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();
        animations.put(Direction.DOWN, createAnimation(spriteName, spriteName + "_down", app));
        animations.get(Direction.DOWN).setDirection(Direction.DOWN);
        animations.put(Direction.UP, createAnimation(spriteName, spriteName + "_up", app));
        animations.get(Direction.UP).setDirection(Direction.UP);
        animations.put(Direction.LEFT, createAnimation(spriteName, spriteName + "_left", app));
        animations.get(Direction.LEFT).setDirection(Direction.LEFT);
        animations.put(Direction.RIGHT, createAnimation(spriteName, spriteName + "_right", app));
        animations.get(Direction.RIGHT).setDirection(Direction.RIGHT);
        return animations;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod animations for the player
     * @return a Player
     */
    public static Player makePlayer(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("player", app);
        Player player = new Player(10, x, y, animations);
        return player;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod animations for the red enemy
     * @return a red enemy
     */
    public static EnemyRed makeEnemyRed(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("red_enemy", app);
        EnemyRed enemy = new EnemyRed(x, y, animations);
        return enemy;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod animations for the yellow enemy
     * @return a yellow enemy
     */
    public static EnemyYellow makeEnemyYellow(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = createAnimationsForMovable("yellow_enemy", app);
        EnemyYellow enemy = new EnemyYellow(x, y, animations);
        return enemy;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod animations for a bomb
     * @return a bomb
     */
    public static Bomb makeBomb(int x, int y, PApplet app){
        HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();
        animations.put(Direction.DOWN,createAnimation("bomb", "bomb", app));
        Bomb bomb = new Bomb(x, y, animations, app.millis());
        return bomb;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod images for a solidwall
     * @return a solidwall
     */
    public static SolidWall makeSolidWall(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/wall/solid.png");
        SolidWall tile = new SolidWall(x, y, tileImage);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod images for a brokenwall
     * @return a brokenwall
     */
    public static BrokenWall makeBrokenWall(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/broken/broken.png");
        BrokenWall tile = new BrokenWall(x, y, tileImage);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to laod images for a goal
     * @return a goal object
     */
    public static Goal makeGoal(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/goal/goal.png");
        Goal tile = new Goal(x, y, tileImage);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to load images an empty tile
     * @return a empty tile object
     */
    public static Empty makeEmpty(int x, int y, PApplet app){
        PImage tileImage = app.loadImage("src/main/resources/empty/empty.png");
        Empty tile = new Empty(x, y, tileImage);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to load images for an explosion tile
     * @return the centre tile for an explosion
     */
    public static ExplosionTile makeExplosionCentre(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/centre.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to load images for an explosion tile
     * @return a horizontal tile for an explosion
     */
    public static ExplosionTile makeExplosionHorizontal(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/horizontal.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    /**@param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to load images for an explosion tile
     * @return a vertical tile for an explosion
     */
    public static ExplosionTile makeExplosionVertical(int x, int y, PApplet app){
        PImage image = app.loadImage("src/main/resources/explosion/vertical.png");
        ExplosionTile tile = new ExplosionTile(x, y, image);
        return tile;
    }

    /**Used by the loader method toconstruct gameobjects based off chars in level files
     * @param c the character read in by the loader method
     * @param x x coord for the object
     * @param y y coord for the object
     * @param app app instance to load images for the object
     * @return a gameobject
     */
    public static GameObject makeByChar(char c, int x, int y, PApplet app){
        switch(c){
            case 'W' : return makeSolidWall(x, y, app);
            case 'B' : return makeBrokenWall(x, y, app);
            // case ' ' : return makeEmpty(x, y, app);
            case 'G' : return makeGoal(x, y, app);
            case 'P' : return makePlayer(x, y, app);
            case 'Y' : return makeEnemyYellow(x, y, app);
            case 'R' : return makeEnemyRed(x, y, app);
        }
        return makeEmpty(x, y, app); // return empty tile if cannot find specified char
    }
}
