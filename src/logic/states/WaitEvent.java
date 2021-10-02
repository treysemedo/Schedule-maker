/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import java.util.List;
import logic.states.util.EnumState;
import logic.data.Data;
import logic.data.event.Event;
import logic.data.util.Utility;

/**
 *
 * @author treys
 */
public class WaitEvent extends IStateAdapter {
    private final Data data;
   
    

    public WaitEvent(Data dados) {
        super(dados);
      
        data=super.getData();
        
    }

    @Override
    public IState travel() {
        
        if(data.hasCrew()&& data.hasFuel()){
            data.travel();
            return new PlanetOrbiting(data);
        }else
            return new GameOver(data, false);
    }
     
    @Override
     public IState randomEvent(){
         data.eventHappen();
         if(data.hasFuel()&& data.hasCrew()){
            data.travel();
            return new PlanetOrbiting(data);
         }
         return new GameOver(data, false);
         
     }
    @Override
     public IState selectEvent(int n){
         data.eventHappen(n);
         if(data.hasFuel()&& data.hasCrew()){
            data.travel();
            return new PlanetOrbiting(data);
         }
         return new GameOver(data, false);
     }
   
     @Override
    public EnumState getState() {
        return EnumState.EVENT;
    }

}
