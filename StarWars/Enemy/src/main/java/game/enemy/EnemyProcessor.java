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
import enums.AITypes;
import enums.Behaviours;
import enums.Environments;
import interfaces.Targetable;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

@ServiceProvider(service = IProcessor.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyProcessor implements IProcessor {

    private int range = 2 * 32; //was 12
    private Environments targetDirection;
    private Behaviours action;
    private boolean lineOfSight;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity en : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) en;
            MovingPart mp = enemy.getPart(MovingPart.class);
            AnimationPart ap = enemy.getPart(AnimationPart.class);
            analyze(world, (Enemy) enemy);

            if (targetDirection == Environments.LEFT) {
                if (lineOfSight == true) {
                    //Attack
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        System.out.println("Pew!");
                    }
                } else {
                    //Walk towards target
                    mp.setLeft(true);
                    mp.setRight(false);
                }
            }
            if (targetDirection == Environments.RIGHT) {
                if (lineOfSight == true) {
                    //Attack
                    if (enemy.getAIType() == AITypes.MELEE) {
                        ap.changeAnimation("RaiderAttack", 5);
                    }
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        ap.changeAnimation("TrooperShooting", 1);
                        System.out.println("Pew!");
                    }
                } else {
                    //Walk towards target
                    mp.setLeft(false);
                    mp.setRight(true);
                    if (enemy.getAIType() == AITypes.MELEE) {
                        ap.changeAnimation("RaiderWalking", 5);
                    }
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        ap.changeAnimation("TrooperWalking", 3);
                    }

                }
            }

            mp.process(gameData, enemy);
        }
    }

    private void analyze(World world, Enemy enemy) {
        //Draw straight line between enemy and player
        //Meassure distance to ensure player is within radius

        PositionPart enPos = enemy.getPart(PositionPart.class);
        PropertiesPart enProp = enemy.getPart(PropertiesPart.class);

        for (Entity ent : world.getEntities()) {
            if (ent instanceof Targetable) {
                //Found player
                PositionPart plPos = ent.getPart(PositionPart.class);

                float dx = plPos.getX() - enPos.getX();
                float dy = plPos.getY() - enPos.getY();
                float yGive = enProp.getHeight() / 2;

                if (Math.abs(dx) > range) {
                    lineOfSight = false;
                } else {
                    if (dy < yGive) {
                        if (dx < 0) {
                            //Player is left
                            targetDirection = Environments.LEFT;
                        }
                        if (dx > 0) {
                            //Player is right
                            targetDirection = Environments.RIGHT;
                        }
                    } else {
                        lineOfSight = false;
                    }
                }

                //Decide on an action:
                if (Math.abs(dx) > range * 0.75) {
                    lineOfSight = false;
                    action = Behaviours.WALK;
                } else if (Math.abs(dx) <= range * 0.75) {
                    lineOfSight = true;
                    if (enemy.getAIType() == AITypes.SHOOTER) {
                        action = Behaviours.SHOOT;
                    }
                    if (enemy.getAIType() == AITypes.MELEE) {
                        action = Behaviours.MELEE;
                    }
                } else {
                    action = Behaviours.IDLE;
                }

            }
        }
    }

}
