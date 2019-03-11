/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteHandling;

/**
 *
 * @author Krongrah
 */
class Animation {

    private float delta = 0;
    private static float timeBetweenFrames = 0.2f;
    private static int animationSteps = 4;
    private boolean expired;
    private boolean moving = false;
    private float x=0;
    private float y=0;

    String getCurrentPose() {
        expired = false;
        if (moving) {
            float pose = (delta / timeBetweenFrames) % animationSteps;
            if (pose > 3) {
                return "Stand";
            }
            if (pose > 2) {
                return "Right";
            }
            if (pose > 1) {
                return "Stand";
            }
            if (pose > 0) {
                return "Left";
            }
            
        }

        return "Stand";
    }

    void updateDelta(float delta) {
        expired = true;
        if (moving) {
            this.delta += delta;
        }
    }

    boolean isExpired() {
        return expired;
    }

    void setMoving(float x, float y) {
        boolean lastMoving=moving;
        moving=false;
       if(this.x!=x){
           this.x=x;
       moving=true;
       }
       if(this.y!=y){
           this.y=y;
       moving=true;
       }
    }

}
