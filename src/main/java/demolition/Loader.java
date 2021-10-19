package demolition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) {
        // App app = new App();
        // List<Level> allLevels = loadAllFiles(app);
        // System.out.println(allLevels.size());
    }

    public static List<Level> loadAllFiles(PApplet app, String configFileName){
        List<Level> allLevels = new ArrayList<Level>();

        // Get the config file
        File f = new File(configFileName); //Issue with directories files -> Needs to refer to config file in the same folder
        Scanner scan;
        try{
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return allLevels;
        }
        
        // Read the contents of file into a string
        StringBuilder JSONScript = new StringBuilder();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            JSONScript.append(line);
        }
        scan.close();

        String JSONString = JSONScript.toString();

        // Parse the string into JSON object
        JSONObject config = JSONObject.parse(JSONString);

        JSONArray levels = config.getJSONArray("levels");
        int lives = config.getInt("lives");

        // Load every level
        for (int i = 0; i < levels.size(); i++){
            JSONObject level = levels.getJSONObject(i);
            String path = level.getString("path");
            int time = level.getInt("time");

            Level newLevel = loadFromFile(path, time, lives, app);

            // Set the player and enemies to know what level they are in
          

            allLevels.add(newLevel);
        }

        

        
        return allLevels;

    }
    
    public static Level loadFromFile(String filename, int time, int lives, PApplet app){
        Level newLevel = new Level();

        // Check if the file is valid
        File f = new File(filename);
        Scanner scan;
        
        try{
            scan = new Scanner(f);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        // Check if file characters are valid
        char[] validChars = new char[] {'W', 'B', 'G', ' ', 'P', 'Y', 'R'};
        List<String> lines = new ArrayList<String>();
        boolean containsGoal = false;
        boolean containsStart = false;
        while (scan.hasNextLine()){
            
            String line = scan.nextLine();
            char[] charArray = line.toCharArray();

            for (char c: charArray){
                boolean isValidChar = false;
                for (char v: validChars){
                    if (c == v){
                        isValidChar = true;
                    
                        if (c == 'G'){
                            if (!containsGoal) {containsGoal = true;}
                            else {return null;}
                        }

                        if (c == 'P'){
                            if (!containsStart) {containsStart = true;}
                            else {return null;}
                        }
                    }
                }

                if (!isValidChar) {return null;}
            }

            // Check for width dimension of line 
            if (line.length() != 15){return null;}

            lines.add(line);
        }

        scan.close();

        // Check for starting location and goal tile
        if (!containsGoal || !containsStart) {return null;}
        
        // Check for height of map
        if (lines.size() != 13){return null;}
        
        // Removed check for bounds as it is optional

        // Load the file
        int x = 0;
        int y = 64;
        for (String line: lines){
            char[] charArray = line.toCharArray();
            for (char c: charArray){
                GameObject objectAdded, backGround; 
                objectAdded = Sprites.makeByChar(c, x, y, app);
                newLevel.addObject(objectAdded);

                // Deal with special case of breakable wall -> add background behind it

                // Deal with special case of player or enemy or breakable tile
                if (c == 'P' || c == 'R' || c == 'Y' || c == 'B'){
                    backGround = Sprites.TILE_EMPTY.make(x, y, app);
                    newLevel.addObject(backGround);
                    // System.out.println("ENEMY ADDED");
                }
                
                x += 32;
            }
            y += 32;
            x = 0;
        }
        return newLevel;
    }
}
