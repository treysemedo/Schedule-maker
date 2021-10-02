/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.data.event;


import java.io.Serializable;
import logic.data.Ship.Ship;
import logic.data.util.EnumEvent;


/**
 *
 * @author treys
 */
public abstract class Event implements Serializable{
    private static final long serialVersionUID = 4L;
    
    
    Ship ship;
    
    public Event(Ship ship){
        this.ship=ship;
    }
    
     public abstract void act();

     public abstract EnumEvent getEnumEvent();
}
