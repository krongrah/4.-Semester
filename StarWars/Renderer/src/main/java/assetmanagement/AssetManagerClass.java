package assetmanagement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Krongrah
 */
public class AssetManagerClass {

    private AssetManager am;

    public AssetManagerClass() {
        am = new AssetManager(new AssetJarFileResolver());
    }

    public void Load(String path) {
        if (!path.equals("")) {
            am.load(path, TextureAtlas.class);
            while (am.update()) {
                System.out.println("Asset loaded...");
            }
            am.finishLoadingAsset(path);
        }
    }

    public Sprite getSprite(String animation, String filePath) {
         return new Sprite(((TextureAtlas) (am.get(filePath, TextureAtlas.class))).findRegion(animation));
    }

}
