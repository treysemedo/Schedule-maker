/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import logic.states.util.EnumState;
import logic.data.Data;

/**
 *
 * @author treys
 */
public class ShipSelection extends IStateAdapter {
    Data data;
    public ShipSelection(Data dados) {
        super(dados);
       
        data=getData();
    }
    
    @Override
    public IState selectShip(int n) {

       if(data.setShip(n)){
           data.travel();
         
           return new PlanetOrbiting(data);
       }
       return this;
    }
    
    @Override
    public EnumState getState() {
        return EnumState.SHIPSELECTION;
    }
    
}
