/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import entityparts.WeaponPart;
import enums.AITypes;
import enums.Behaviours;
import enums.Directions;
import enums.Environments;
import interfaces.Targetable;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

@ServiceProvider(service = IProcessor.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyProcessor implements IProcessor {

    private int range = 4 * 32; //was 12
    private Environments targetDirection;
    private Behaviours action;
    private boolean lineOfSight;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity en : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) en;
            MovingPart mp = enemy.getPart(MovingPart.class);
            AnimationPart ap = enemy.getPart(AnimationPart.class);
            PositionPart enPos = enemy.getPart(PositionPart.class);
            analyze(world, enemy);

            if (targetDirection == Environments.LEFT) {
                if (lineOfSight == true) {
                    //Attack
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        WeaponPart wp = enemy.getPart(WeaponPart.class);

                        Random rand = new Random();
                        int random = rand.nextInt(10);
                        if (random >= 7) {
                            wp.setAttacking(true);
                            enPos.setDirection(Directions.LEFT);
                        }
                    }
                    if (enemy.getAIType() == AITypes.MELEE) {
                        //Walk towards target
                        mp.setLeft(true);
                        mp.setRight(false);
                    }
                }
                if (lineOfSight != true) {
                    moveToOrigin(enemy);
                    if (enemy.hasPart(WeaponPart.class)) {
                        WeaponPart wp = enemy.getPart(WeaponPart.class);
                        wp.setAttacking(false);
                    }
                }
            }

            if (targetDirection == Environments.RIGHT) {
                if (lineOfSight == true) {
                    //Attack
                    if (enemy.getAIType() == AITypes.MELEE) {
                        mp.setLeft(false);
                        mp.setRight(true);
                    }
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        WeaponPart wp = enemy.getPart(WeaponPart.class);
                        wp.setAttacking(true);
                        enPos.setDirection(Directions.RIGHT);
                    }
                }
                if (lineOfSight != true) {
                    moveToOrigin(enemy);
                    if (enemy.hasPart(WeaponPart.class)) {
                        WeaponPart wp = enemy.getPart(WeaponPart.class);
                        wp.setAttacking(false);
                    }
                }
            }

            animate(enemy, mp, ap);
            ap.process(gameData, en);
            mp.process(gameData, enemy);
        }
    }

    /**
     * Ensures that the enemy will move back to its spawnpoint used when the
     * player no longer is in sight.
     *
     * @param enemy
     */
    private void moveToOrigin(Enemy enemy) {
        PositionPart enPos = enemy.getPart(PositionPart.class);
        MovingPart mp = enemy.getPart(MovingPart.class);
        //PropertiesPart enProp = enemy.getPart(PropertiesPart.class);

        if (enPos.getX() - enemy.getOriginX() > 2) {
            //Move left
            mp.setLeft(true);
            mp.setRight(false);
        }
        if (enPos.getX() - enemy.getOriginX() < 2) {
            //Move right
            mp.setLeft(false);
            mp.setRight(true);
        }
        if (enPos.getX() - enemy.getOriginX() <= 1) {
            mp.setLeft(false);
            mp.setRight(false);
            enPos.setX(enemy.getOriginX());
        }
    }

    /**
     * Ensures proper animation of the enemy based on its spritesheet
     *
     * @param enemy
     * @param mp
     * @param ap
     */
    private void animate(Enemy enemy, MovingPart mp, AnimationPart ap) {
        if (mp.isAccelerating()) {
            //Walk
            if (enemy.getAIType() == AITypes.MELEE) {
                ap.changeAnimation("RaiderWalking", 7);
            }
            if (enemy.getAIType() == AITypes.SHOOTER) {
                ap.changeAnimation("TrooperWalking", 4);
            }
        } else {
            //idle
            if (enemy.getAIType() == AITypes.MELEE) {
                ap.changeAnimation("RaiderIdle", 7);
            }
            if (enemy.getAIType() == AITypes.SHOOTER) {
                ap.changeAnimation("TrooperIdle", 1);
            }
        }
    }

    /**
     * Ensures that the straight line distance from the enemy to the player is
     * unobstructed by obstacles
     *
     * @param world
     * @param enemy
     * @return
     */
    private boolean unobstructed(World world, Enemy enemy, float dx) {
        PositionPart enemyPos = enemy.getPart(PositionPart.class);
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof Targetable)) {

                PositionPart entPos = entity.getPart(PositionPart.class);
                PropertiesPart entProp = entity.getPart(PropertiesPart.class);

                if (entProp.isObstacle() && enemyPos.getY() == entPos.getY()) {
                    if (Math.abs(dx) <= range) {
                        //On the same plain
                        //System.out.println("Entity x: " + entPos.getX() + " Enemy x: " + enemyPos.getX() + " dx: " + dx);
                        //System.out.println("Entity y: " + entPos.getY() + " Enemy y: " + enemyPos.getY());

                        //The first obstacle left or right will mean that the enemy does not have an unobstructed view
                        if (dx > 0) {
                            targetDirection = Environments.RIGHT;

                            //System.out.println("Player right");
                            //check for the first obstacle with a lower x value than the enemy
                            if (entPos.getX() > enemyPos.getX() && entPos.getX() <= (enemyPos.getX() + range) && enemyPos.getY() == entPos.getY()) {
                                //Entity is within the range that has to bee checked
                                return false;
                            }
                        }

                        if (dx < 0) {
                            targetDirection = Environments.LEFT;

                            //System.out.println("Player left");
                            //Same but higher x values.
                            if (entPos.getX() < enemyPos.getX()  && entPos.getX() >= (enemyPos.getX() + range) && enemyPos.getY() == entPos.getY()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Analyzes the environment around the enemy to find the location of the
     * player, and the distance from the enemy to the player. This is used to
     * determin actions, walking, attacking, idle..
     *
     * @param world
     * @param enemy
     */
    private void analyze(World world, Enemy enemy) {
        //Draw straight line between enemy and player
        //Meassure distance to ensure player is within radius

        PositionPart enPos = enemy.getPart(PositionPart.class);
        PropertiesPart enProp = enemy.getPart(PropertiesPart.class);

        for (Entity ent : world.getEntities()) {
            int targets = 0;
            if (ent instanceof Targetable) {
                targets++;
                //Found player
                PositionPart plPos = ent.getPart(PositionPart.class);

                float dx = plPos.getX() - enPos.getX();
                float dy = plPos.getY() - enPos.getY();
                float yGive = enProp.getHeight() / 2;
                if (dy < yGive) {
                    //Decide on an action:
                    if (Math.abs(dx) > range) {
                        lineOfSight = false;
                        action = Behaviours.WALK;
                    }
                    if (Math.abs(dx) < range) {
                        lineOfSight = unobstructed(world, enemy, dx);

                        if (lineOfSight) {
                            if (enemy.getAIType() == AITypes.SHOOTER) {
                                action = Behaviours.SHOOT;
                            }
                            if (enemy.getAIType() == AITypes.MELEE) {
                                action = Behaviours.MELEE;
                            }
                        }
                    } else {
                        action = Behaviours.IDLE;
                    }
                }

            }else if(targets == 0){
                action = Behaviours.IDLE;
                lineOfSight = false;
            }
        }
    }
}
