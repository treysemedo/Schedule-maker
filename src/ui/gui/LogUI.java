/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import observable.GameObservable;
import static ui.gui.util.Propriedade.CHANGE_LOG;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class LogUI extends VBox {
    private final GameObservable gameObs;
    private Label title;
    private Label log;

    public LogUI(GameObservable gameObs) {
        this.gameObs = gameObs;
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: rgba(20,24,30,0.95); -fx-background-radius: 10;");
        this.setMaxSize(300, 600);
        this.setMinSize(300, 600);
        this.setSpacing(50);
        title= new Label("GAME LOG");
        log= new Label();
        
        title.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);
        title.setAlignment(Pos.TOP_CENTER);
        log.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
        log.setTextFill(Color.WHITE);
        
        log.setMaxWidth(this.getMaxWidth()-20);
        registerPropertyListener();
        this.getChildren().addAll(title, log);
    }
    
    
     private void registerPropertyListener() {
        gameObs.addPropertyChangeListener(CHANGE_LOG, (listener) -> {
             log.setText(gameObs.getLog());
        });
     }
}
