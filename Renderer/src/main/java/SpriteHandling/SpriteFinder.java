/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteHandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import data.Entity;
import entityparts.PositionPart;

/**
 *
 * @author Krongrah
 */
public class SpriteFinder {
    
    private TextureAtlas atlas;
    private Sprite sprite;
    private AnimationTracker animationTracker;

    //tmx map loader
    
    
    public SpriteFinder(AnimationTracker animationTracker) {
       atlas=new TextureAtlas(Gdx.files.internal("resources/Elm.txt"));
       sprite = new Sprite(atlas.findRegion("ElmNorthStand"));
       this.animationTracker=animationTracker;
       
    }
    
    
    
    
            
    
    
   public Sprite getSprite(Entity entity, float cameraAngle){
       PositionPart pos = entity.getPart(PositionPart.class);
       
       String region="";
       region+=((PositionPart)entity.getPart(PositionPart.class)).getModel().getName();
       region+=Directions.getDirection(pos.getRadians(), cameraAngle).getName();
       region+=animationTracker.getPos(entity);
       
       sprite.setRegion(atlas.findRegion(region));
       
       sprite.setPosition(pos.getX()-sprite.getWidth()/2, pos.getY()-sprite.getHeight()/2);
            sprite.setRotation((cameraAngle)/3.1415f*180);
    
       
   return sprite;
   }
}
