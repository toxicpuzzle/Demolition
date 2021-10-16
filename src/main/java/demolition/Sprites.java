package demolition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

// Factory enum for all sprites
public enum Sprites {
    PLAYER {
        public Player make(int x, int y, PApplet app) {

            // TODO: Consider loading all of the images during the setup() function of hte app.java file
            HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();

            // TODO: Could use some kind of loop to make this shorter but leave for now
            List<PImage> leftFrames = new ArrayList<PImage>();
            PImage left1 = app.loadImage("src/main/resources/player/player_left1.png");
            PImage left2 = app.loadImage("src/main/resources/player/player_left2.png");
            PImage left3 = app.loadImage("src/main/resources/player/player_left3.png");
            PImage left4 = app.loadImage("src/main/resources/player/player_left4.png");
            leftFrames.add(left1);
            leftFrames.add(left2);
            leftFrames.add(left3);
            leftFrames.add(left4);
            Animation leftAnimation = new Animation(leftFrames, 4, 200);

            List<PImage> rightFrames = new ArrayList<PImage>();
            PImage right1 = app.loadImage("src/main/resources/player/player_right1.png");
            PImage right2 = app.loadImage("src/main/resources/player/player_right2.png");
            PImage right3 = app.loadImage("src/main/resources/player/player_right3.png");
            PImage right4 = app.loadImage("src/main/resources/player/player_right4.png");
            rightFrames.add(right1);
            rightFrames.add(right2);
            rightFrames.add(right3);
            rightFrames.add(right4);
            Animation rightAnimation = new Animation(rightFrames, 4, 200);

            List<PImage> upFrames = new ArrayList<PImage>();
            PImage up1 = app.loadImage("src/main/resources/player/player_up1.png");
            PImage up2 = app.loadImage("src/main/resources/player/player_up2.png");
            PImage up3 = app.loadImage("src/main/resources/player/player_up3.png");
            PImage up4 = app.loadImage("src/main/resources/player/player_up4.png");
            upFrames.add(up1);
            upFrames.add(up2);
            upFrames.add(up3);
            upFrames.add(up4);
            Animation upAnimation = new Animation(upFrames, 4, 200);

            List<PImage> downFrames = new ArrayList<PImage>();
            PImage down1 = app.loadImage("src/main/resources/player/player1.png");
            PImage down2 = app.loadImage("src/main/resources/player/player2.png");
            PImage down3 = app.loadImage("src/main/resources/player/player3.png");
            PImage down4 = app.loadImage("src/main/resources/player/player4.png");
            downFrames.add(down1);
            downFrames.add(down2);
            downFrames.add(down3);
            downFrames.add(down4);
            Animation downAnimation = new Animation(downFrames, 4, 200);

            animations.put(Direction.DOWN, downAnimation);
            animations.put(Direction.UP, upAnimation);
            animations.put(Direction.LEFT, leftAnimation);
            animations.put(Direction.RIGHT, rightAnimation);
            
            // NOTE: x, and y are adjusted to reflect player's head going above cell
            Player player = new Player(10, x, y, animations); //TODO: Change lives after making. 
            return player;
        }
    },
    WALL_SOLID{
        public SolidWall make(int x, int y, PApplet app) {
            PImage tileImage = app.loadImage("src/main/resources/wall/solid.png");
            SolidWall tile = new SolidWall(x, y, tileImage);
            return tile;
        }
    },
    WALL_BROKEN{
        public BrokenWall make(int x, int y, PApplet app) {
            PImage tileImage = app.loadImage("src/main/resources/broken/broken.png");
            BrokenWall tile = new BrokenWall(x, y, tileImage);
            return tile;
        }
    },
    TILE_GOAL{
        public Goal make(int x, int y, PApplet app) {
            PImage tileImage = app.loadImage("src/main/resources/goal/goal.png");
            Goal tile = new Goal(x, y, tileImage);
            return tile;
        }
    },
    TILE_EMPTY{
        public Empty make(int x, int y, PApplet app) {
            PImage tileImage = app.loadImage("src/main/resources/empty/empty.png");
            Empty tile = new Empty(x, y, tileImage);
            return tile;
        }
    },

    // Dummies for now
    ENEMY_YELLOW{
        public EnemyYellow make(int x, int y, PApplet app) {
            HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();

            // TODO: Could use some kind of loop to make this shorter but leave for now
            List<PImage> leftFrames = new ArrayList<PImage>();
            PImage left1 = app.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
            PImage left2 = app.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
            PImage left3 = app.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
            PImage left4 = app.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");
            leftFrames.add(left1);
            leftFrames.add(left2);
            leftFrames.add(left3);
            leftFrames.add(left4);
            Animation leftAnimation = new Animation(leftFrames, 4, 200);

            List<PImage> rightFrames = new ArrayList<PImage>();
            PImage right1 = app.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
            PImage right2 = app.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
            PImage right3 = app.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
            PImage right4 = app.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");
            rightFrames.add(right1);
            rightFrames.add(right2);
            rightFrames.add(right3);
            rightFrames.add(right4);
            Animation rightAnimation = new Animation(rightFrames, 4, 200);

            List<PImage> upFrames = new ArrayList<PImage>();
            PImage up1 = app.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
            PImage up2 = app.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
            PImage up3 = app.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
            PImage up4 = app.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");
            upFrames.add(up1);
            upFrames.add(up2);
            upFrames.add(up3);
            upFrames.add(up4);
            Animation upAnimation = new Animation(upFrames, 4, 200);

            List<PImage> downFrames = new ArrayList<PImage>();
            PImage down1 = app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
            PImage down2 = app.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
            PImage down3 = app.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
            PImage down4 = app.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
            downFrames.add(down1);
            downFrames.add(down2);
            downFrames.add(down3);
            downFrames.add(down4);
            Animation downAnimation = new Animation(downFrames, 4, 200);

            animations.put(Direction.DOWN, downAnimation);
            animations.put(Direction.UP, upAnimation);
            animations.put(Direction.LEFT, leftAnimation);
            animations.put(Direction.RIGHT, rightAnimation);
            EnemyYellow e = new EnemyYellow(x, y, animations);
            return e;
        }
    },
    ENEMY_RED{
        public Enemy make(int x, int y, PApplet app) {
            HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();

            // TODO: Could use some kind of loop to make this shorter but leave for now
            List<PImage> leftFrames = new ArrayList<PImage>();
            PImage left1 = app.loadImage("src/main/resources/red_enemy/red_left1.png");
            PImage left2 = app.loadImage("src/main/resources/red_enemy/red_left2.png");
            PImage left3 = app.loadImage("src/main/resources/red_enemy/red_left3.png");
            PImage left4 = app.loadImage("src/main/resources/red_enemy/red_left4.png");
            leftFrames.add(left1);
            leftFrames.add(left2);
            leftFrames.add(left3);
            leftFrames.add(left4);
            Animation leftAnimation = new Animation(leftFrames, 4, 200);

            List<PImage> rightFrames = new ArrayList<PImage>();
            PImage right1 = app.loadImage("src/main/resources/red_enemy/red_right1.png");
            PImage right2 = app.loadImage("src/main/resources/red_enemy/red_right2.png");
            PImage right3 = app.loadImage("src/main/resources/red_enemy/red_right3.png");
            PImage right4 = app.loadImage("src/main/resources/red_enemy/red_right4.png");
            rightFrames.add(right1);
            rightFrames.add(right2);
            rightFrames.add(right3);
            rightFrames.add(right4);
            Animation rightAnimation = new Animation(rightFrames, 4, 200);

            List<PImage> upFrames = new ArrayList<PImage>();
            PImage up1 = app.loadImage("src/main/resources/red_enemy/red_up1.png");
            PImage up2 = app.loadImage("src/main/resources/red_enemy/red_up2.png");
            PImage up3 = app.loadImage("src/main/resources/red_enemy/red_up3.png");
            PImage up4 = app.loadImage("src/main/resources/red_enemy/red_up4.png");
            upFrames.add(up1);
            upFrames.add(up2);
            upFrames.add(up3);
            upFrames.add(up4);
            Animation upAnimation = new Animation(upFrames, 4, 200);

            List<PImage> downFrames = new ArrayList<PImage>();
            PImage down1 = app.loadImage("src/main/resources/red_enemy/red_down1.png");
            PImage down2 = app.loadImage("src/main/resources/red_enemy/red_down2.png");
            PImage down3 = app.loadImage("src/main/resources/red_enemy/red_down3.png");
            PImage down4 = app.loadImage("src/main/resources/red_enemy/red_down4.png");
            downFrames.add(down1);
            downFrames.add(down2);
            downFrames.add(down3);
            downFrames.add(down4);
            Animation downAnimation = new Animation(downFrames, 4, 200);

            animations.put(Direction.DOWN, downAnimation);
            animations.put(Direction.UP, upAnimation);
            animations.put(Direction.LEFT, leftAnimation);
            animations.put(Direction.RIGHT, rightAnimation);
            EnemyRed e = new EnemyRed(x, y, animations);
            return e;
        }
    }, BOMB {
        @Override
        public Bomb make(int x, int y, PApplet app) {
            HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();

            // TODO: Could use some kind of loop to make this shorter but leave for now
            List<PImage> frames = new ArrayList<PImage>();
            PImage frame1 = app.loadImage("src/main/resources/bomb/bomb1.png");
            PImage frame2 = app.loadImage("src/main/resources/bomb/bomb2.png");
            PImage frame3 = app.loadImage("src/main/resources/bomb/bomb3.png");
            PImage frame4 = app.loadImage("src/main/resources/bomb/bomb4.png");
            PImage frame5 = app.loadImage("src/main/resources/bomb/bomb5.png");
            PImage frame6 = app.loadImage("src/main/resources/bomb/bomb6.png");
            PImage frame7 = app.loadImage("src/main/resources/bomb/bomb7.png");
            PImage frame8 = app.loadImage("src/main/resources/bomb/bomb8.png");
            frames.add(frame1);
            frames.add(frame2);
            frames.add(frame3);
            frames.add(frame4);
            frames.add(frame5);
            frames.add(frame6);
            frames.add(frame7);
            frames.add(frame8);
            Animation detonate = new Animation(frames, 4, 250);

            animations.put(Direction.DOWN, detonate);
            Bomb b = new Bomb(x, y, animations, app.millis());
            return b;
        }
    }, EXPLOSION_TOP{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/end_top.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }
        
    }, EXPLOSION_BOTTOM{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/end_bottom.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }

    }, EXPLOSION_LEFT{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/end_left.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }

    }, EXPLOSION_RIGHT{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/end_right.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }

    }, EXPLOSION_HORIZONTAL{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/horizontal.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }

    }, EXPLOSION_VERTICAL{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/vertical.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }

    }, EXPLOSION_CENTRE{
        @Override
        public ExplosionTile make(int x, int y, PApplet app) {
            // TODO Auto-generated method stub
            PImage image = app.loadImage("src/main/resources/explosion/centre.png");
            ExplosionTile tile = new ExplosionTile(x, y, image);
            return tile;
        }
    };

    public abstract GameObject make(int x, int y, PApplet app);

    public static GameObject makeByChar(char c, int x, int y, PApplet app){
        switch(c){
            case 'W' : return WALL_SOLID.make(x, y, app);
            case 'B' : return WALL_BROKEN.make(x, y, app);
            case ' ' : return TILE_EMPTY.make(x, y, app);
            case 'G' : return TILE_GOAL.make(x, y, app);
            case 'P' : return PLAYER.make(x, y, app);
            case 'Y' : return ENEMY_YELLOW.make(x, y, app);
            case 'R' : return ENEMY_RED.make(x, y, app);
        }
        return TILE_EMPTY.make(x, y, app); // return empty tile if cannot find specified char
    }
}

