/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.data.event;

import logic.data.Ship.Ship;
import logic.data.util.EnumEvent;

/**
 *
 * @author treys
 */
public class NoEvent extends Event{

    public NoEvent(Ship ship) {
        super(ship);
    }

    @Override
    public void act() {
       }

    @Override
    public EnumEvent getEnumEvent() {
       return EnumEvent.NOEVENT;
    }
    
}
