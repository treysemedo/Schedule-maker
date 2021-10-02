/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import logic.data.Data;
import logic.data.util.EnumResource;
import logic.states.util.EnumState;

/**
 *
 * @author treys
 */
public class ResourceConversion extends IStateAdapter{
     private final Data data;
     IState previousState;
     
    public ResourceConversion(Data dados,IStateAdapter previousState ) {
        super(dados);
        
        data=getData();
        this.previousState=previousState;
    }
    
     @Override
    public IState goBack(){
        return previousState;
    }
    
     @Override
    public void produceShield() {
        data.produceShield();
    }
     @Override
    public void produceAmmo() {
         data.produceAmmo();
    }
     @Override
    public void produceFuel() {
         data.produceFuel();
    }

   @Override
    public EnumState getState() {
        return EnumState.RESOURCECONVERSION;
    }
     
}
