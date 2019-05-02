/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import common.Entity;

/**
 *
 * @author Sebas
 */
public interface Reaction {
    public void execute(Entity entity, Targetable target);
}
