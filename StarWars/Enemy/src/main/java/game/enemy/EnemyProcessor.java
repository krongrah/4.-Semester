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

    private int range = 5 * 32; //was 12
    private Environments targetDirection;
    private Behaviours action;
    private boolean lineOfSight;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            MovingPart mp = enemy.getPart(MovingPart.class);

            //Analyze environment
            switch (analyze(world, enemy)) {
                case LEFT:
                    //React
                    mp.setLeft(true);
                    mp.setRight(false);
                    break;
                case RIGHT:
                    //React
                    mp.setRight(true);
                    mp.setLeft(false);
                    break;
                case LINEOFSIGHT:
                    //React
                    break;
                case OUTOFSIGHT:
                    //React
                    break;
            }

            //PositionPart pos=enemy.getPart(PositionPart.class);
            mp.process(gameData, enemy);
        }

    }

    private Environments analyze(World world, Entity enemy) {
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
                    action = Behaviours.SHOOT;
                } else {
                    action = Behaviours.IDLE;
                }

            }
        }

        return null;
    }

}
