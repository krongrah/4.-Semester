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

    private String SpriteSheetPath;
    private String currentAnimation;
    private int framesInCurrentAnimation;
    private int currentFrame = 0;
    private float timeInAnimation = 0;
    private final float durationOfEachAnimation = 0.2f;

    public AnimationPart(String currentAnimation, int framesInCurrentAnimation, String SpriteSheetPath) {
        this.currentAnimation = currentAnimation;
        this.framesInCurrentAnimation = framesInCurrentAnimation;
        this.SpriteSheetPath = SpriteSheetPath;
        currentFrame = 0;
    }

    public void changeAnimation(String currentAnimation, int framesInCurrentAnimation) {
        if (!currentAnimation.equals(this.currentAnimation)) {
            this.currentAnimation = currentAnimation;
            this.framesInCurrentAnimation = framesInCurrentAnimation;
            timeInAnimation = 0;
            currentFrame = 0;
        }
    }

    public String getSpriteSheetPath() {
        return SpriteSheetPath;
    }

    public String getCurrentAnimation() {
        return currentAnimation + currentFrame;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        timeInAnimation += gameData.getDelta();
        currentFrame = (int) (timeInAnimation / durationOfEachAnimation % framesInCurrentAnimation);

    }

}
