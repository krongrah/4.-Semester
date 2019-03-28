/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagement;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/**
 *
 * @author Krongrah
 */
public class AssetJarFileResolver implements FileHandleResolver {

    @Override
    public FileHandle resolve(String string) {
        if (string.contains("!")) {
            return new JarFileHandleStream(string);
        }
        return new FileHandle(string);
    }

}
