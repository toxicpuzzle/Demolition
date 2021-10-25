package demolition;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import processing.core.PImage;
import java.io.FilenameFilter;

public class SpriteFactory {
    public List<String> getSpriteImageDirectories(String filePrefix){
        List<String> images = new ArrayList<String>();

        File directory = new File("/src/main/resources");
        File[] filesFound = directory.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String name) {
                return name.startsWith(filePrefix) && name.endsWith(".png");
            }
        }); 

        for (File file: filesFound){
            images.add(file.getPath());
        }
        
        return images;
    }

    public static void main(String[] args) {
        SpriteFactory sf = new SpriteFactory();
        List<String> strings = sf.getSpriteImageDirectories("player");
        System.out.print(strings);
    }
}
