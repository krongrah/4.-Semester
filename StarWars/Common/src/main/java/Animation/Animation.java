/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

/**
 *
 * @author Krongrah
 */
public class Animation {

    private String path;
    private String name;
    private int numberOfFrames;

    public Animation(String path, String name, int numberOfFrames) {
        this.path = path;
        this.name = name;
        this.numberOfFrames = numberOfFrames;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

}
