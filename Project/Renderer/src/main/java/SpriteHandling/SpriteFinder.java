/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteHandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import common.Entity;
import entityparts.PositionPart;
import entityparts.PropertiesPart;

/**
 *
 * @author Krongrah
 */
public class SpriteFinder {

    private TextureAtlas atlas;
    private Sprite sprite;

    //tmx map loader
    public SpriteFinder() {
        atlas = new TextureAtlas(Gdx.files.internal("resources/TatooineSprites.txt"));
        sprite = new Sprite(atlas.findRegion("Luke"));

    }

    public Sprite getSprite(Entity entity) {
        PropertiesPart prop = entity.getPart(PropertiesPart.class);
        PositionPart pos = entity.getPart(PositionPart.class);

        sprite.setRegion(atlas.findRegion(prop.getSprite().name()));

        sprite.setPosition(pos.getX() - sprite.getWidth() / 2, pos.getY() - sprite.getHeight() / 2);

        return sprite;
    }
}
