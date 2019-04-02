package assetmanagement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Krongrah
 */
public class AssetManagerClass {

    //private String string;
    private AssetManager am;
    //private String jarUrl = new File("").getAbsolutePath() + "/src/main/resources/sprites/Luke.png";

    public AssetManagerClass() {
        //am = new AssetManager();
        am = new AssetManager(new AssetJarFileResolver());
    }

    public void Load(String path) {
        if (!path.equals("")) {
            //string = path;

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
