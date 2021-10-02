/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbonundv1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Game;
import observable.GameObservable;
import ui.gui.MainView;

/**
 *
 * @author treys
 */
public class PlanetBonundv1 extends Application {

   
    public static void main(String[] args) {
      
        launch(args);
       
    }

    @Override
    public void start(Stage stage) throws Exception {
     Game myGame=new Game();
     GameObservable gameObs= new GameObservable(myGame);
     
     Scene cena=new Scene(new MainView(gameObs), 1300, 700);
     //Scene cena1=new Scene(new MainView(gameObs), 1000, 700);

     stage.setScene(cena);
     //Stage stage2=new Stage();
     stage.setScene(cena);
     //stage2.setScene(cena1);
     stage.setTitle("PLANET BOUND");
     stage.show();
    // stage2.show();
    }
    
}
