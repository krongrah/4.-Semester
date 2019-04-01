/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;
import enums.PlayerStates;

/**
 *
 * @author Krongrah
 */
public class AnimationPart implements EntityPart {

    private String currentAnimation;
    private int framesInCurrentAnimation;
    private String SpriteSheetPath;
    private PlayerStates ps;
    private int currentSprite;

    public AnimationPart(String currentAnimation, int framesInCurrentAnimation, String SpriteSheetPath) {
        this.currentAnimation = currentAnimation;
        this.framesInCurrentAnimation = framesInCurrentAnimation;
        this.SpriteSheetPath = SpriteSheetPath;
    }

    public String getSpriteSheetPath() {
        return SpriteSheetPath;
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public int getFramesInCurrentAnimation() {
        return framesInCurrentAnimation;
    }

    public void setState(PlayerStates state) {
        this.ps = state;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

        
        if (ps == PlayerStates.IDLE) {
            if (framesInCurrentAnimation > 4) {
                currentSprite++;
                currentSprite = currentSprite % 5;
                currentAnimation = "Lukeidle" + currentSprite;
                framesInCurrentAnimation = framesInCurrentAnimation % 5;
            }
        } else if (ps == PlayerStates.WALKING) {
            if (framesInCurrentAnimation > 6) {
                currentSprite++;
                currentSprite = currentSprite % 7;
                currentAnimation = "Lukewalking" + currentSprite;
                framesInCurrentAnimation = framesInCurrentAnimation % 7;
            }
        }
        
    }

}