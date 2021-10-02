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
public class GameOver extends IStateAdapter{
    Data data;
    public GameOver(Data dados, boolean win) {
        //data.setWin devia estar nos dados
        
        super(dados);
        data=super.getData();
        data.setWin(win);
    }

    

    @Override
    public IState newGame(){
        data.resetDados();
        return new ShipSelection(data);
    }
    @Override
    public EnumState getState() {
        return EnumState.GAMEOVER;
    }
    
}
