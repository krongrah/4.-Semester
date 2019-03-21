/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 *
 * @author Krongrah
 */
public class AnimationPart implements EntityPart {

    private String currentAnimation;
    private int framesInCurrentAnimation;
    private String SpriteSheetPath;

    public AnimationPart(String currentAnimation, int framesInCurrentAnimation, String SpriteSheetPath) {
        this.currentAnimation = currentAnimation;
        this.framesInCurrentAnimation = framesInCurrentAnimation;
        this.SpriteSheetPath = SpriteSheetPath;
    }

    public String getSpriteSheetPath() {
        return SpriteSheetPath;
    }

    public void setSpriteSheetPath(String SpriteSheetPath) {
        this.SpriteSheetPath = SpriteSheetPath;
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(String currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public int getFramesInCurrentAnimation() {
        return framesInCurrentAnimation;
    }

    public void setFramesInCurrentAnimation(int framesInCurrentAnimation) {
        this.framesInCurrentAnimation = framesInCurrentAnimation;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }

}