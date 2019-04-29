/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagement;

import com.badlogic.gdx.files.FileHandle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krongrah
 */
public class JarFileHandleStream extends FileHandle {

    private JarFile jar = null;
    private String jarDir;

    public JarFileHandleStream(String path) {
        super(path);
        try {
            String[] args = path.split("!");
            jarDir = args[1].substring(1);
            String jarFilePath = args[0];
            jar = new JarFile(jarFilePath);
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InputStream read() {
        InputStream input = null;
        try {


//            while(jar.entries().hasMoreElements()){
//                System.out.println(jar.entries().nextElement());
//            }
//            System.out.println("");
            
            input = jar.getInputStream(jar.getEntry(jarDir));
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //return input;
            return new FileInputStream(new File(jar.getEntry(jarDir).toString()));
        } catch (FileNotFoundException ex) {
        }
        return null;
    }

    
    
    @Override
    public OutputStream write(boolean overwrite) {
        return super.write(overwrite);
    }

//    public InputStream read() {
//        InputStream input = null;
//        try {
//            input = jar.getInputStream(jar.getEntry(jarDir));
//        } catch (IOException ex) {
//            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return FileInputStream(new File());
//    }

}
