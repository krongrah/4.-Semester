/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Krongrah
 */
public class Animation {

    private String path;
    private String name;
    private int numberOfFrames;
    
    private String cannonicalPath;

    public Animation(String path, String name, int numberOfFrames) throws IOException {
        this.path = path;
        
        path.substring(5);
        this.name = name;
        this.numberOfFrames = numberOfFrames;
        
        
        this.cannonicalPath = new File(path).getCanonicalPath().replace("\\", "/");
        
    }

    public String getPath() {
//        return path;
        return path;
    }
    
    

    public String getName() {
        return name;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

}
