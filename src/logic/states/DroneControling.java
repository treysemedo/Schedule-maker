/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import logic.states.util.EnumState;
import logic.data.Data;
import logic.data.util.EnumExplore;

/**
 *
 * @author treys
 */
public class DroneControling extends IStateAdapter{
    Data data;
    public DroneControling(Data data) {
        super(data);
        this.data=data;
        data.explorePlanet();
        data.spendFuel(); 
    }
  

    @Override
    public IState moveDrone(int i, int j) {
        EnumExplore aux=data.DroneMove(i, j);
        switch(aux){
        
            case ATACK:
                data.atack();
                   if(!data.DroneIsAlive()){
                       data.resetDrone();
                     return new PlanetOrbiting(data);
                   }
                else 
                    return this;
                   
                  
               
            case BACKTOSHIP:
               if (data.explorationEnd())
                   return new GameOver(data, true);
               
               else{
                   data.resetDrone();
                    return new PlanetOrbiting(data);
               }
              
            default:
                return this;
                     
        }
    }
    @Override
    public EnumState getState() {
        return EnumState.DRONECONTROLING;
    }
    
    
}
