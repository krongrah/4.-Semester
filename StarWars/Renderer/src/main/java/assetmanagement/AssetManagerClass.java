package assetmanagement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.List;
import sprites.Animation;

/**
 *
 * @author Krongrah
 */
public class AssetManagerClass {

    private AssetManager am;

    public AssetManagerClass() {
        am = new AssetManager(new AssetJarFileResolver());
    }

//    public void Load(String path) {
//        if (!path.equals("")) {
//            am.load(path, TextureAtlas.class);
//            while (am.update()) {
//                System.out.println("Asset loaded...");
//            }
//            am.finishLoadingAsset(path);
//        }
//    }
    public Sprite getSprite(String animation, String filePath) {
        am.finishLoadingAsset(filePath + animation + ".png");
        return new Sprite(am.get(filePath + animation + ".png", Texture.class));
    }

    public void Load(List<Animation> animationList) {

        for (Animation animation : animationList) {

            for (int i = 0; i < animation.getNumberOfFrames(); i++) {
                am.load(animation.getPath() + animation.getName() + i + ".png", Texture.class);
                while (am.update()) {
                    System.out.println("Asset loaded...");
                }
            }

        }

        //am.finishLoadingAsset(path);
    }

}
