/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import enums.AITypes;
import enums.Behaviours;
import enums.Directions;
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

    private int range = 5 * 32; //was 12
    private Environments targetDirection;
    private Behaviours action;
    private boolean lineOfSight;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (enemy instanceof Enemy) {
                MovingPart mp = enemy.getPart(MovingPart.class);
                analyze(world, (Enemy) enemy);

                if (targetDirection == Environments.LEFT) {
                    if (lineOfSight == true) {
                        //Attack
                        System.out.println("Pew!");
                    } else {
                        //Walk towards target
                        mp.setLeft(true);
                        mp.setRight(false);
                    }
                }
                if (targetDirection == Environments.RIGHT) {
                    if (lineOfSight == true) {
                        //Attack
                        System.out.println("Pew!");
                    } else {
                        //Walk towards target
                        mp.setLeft(false);
                        mp.setRight(true);
                    }
                }

                mp.process(gameData, enemy);
            }

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
                    lineOfSight = true;
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
