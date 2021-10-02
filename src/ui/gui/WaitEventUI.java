/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import observable.GameObservable;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.infoEvents;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class WaitEventUI extends HBox {

    private GameObservable gameObs;
    private PanelCrew painelC;
    private BorderPane bp;
    private LogUI logUI;

    public WaitEventUI(GameObservable jogoObs) {
        this.gameObs = jogoObs;
        bp=new BorderPane();
        bp.setMaxSize(800, 600);
        bp.setMinSize(800, 600);
        bp.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        registaListenerPropiedade();
        
     logUI=new LogUI(gameObs);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(bp,logUI );
        
    }

    private void registaListenerPropiedade() {
        gameObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {

            switch (gameObs.getState()) {
                case EVENT:
                    painelC = new PanelCrew(gameObs);
                    bp.setRight(painelC);
                    bp.setCenter(eventsUI());
                    this.setVisible(true);
                    
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
        
        
    }

    private VBox eventsUI() {
        PanelShip painelN = new PanelShip(gameObs);

        VBox vb = new VBox(painelN, informacaoEvento());
        vb.setSpacing(100);
        return vb;
    }

    private BorderPane informacaoEvento() {
        BorderPane bp = new BorderPane();

        Label info = new Label();
        info.setTextFill(Color.WHITE);
        info.setFont(Font.font("arial", FontWeight.NORMAL, 20));
        info.setWrapText(true);

        StackPane infoBox = new StackPane(info);
        infoBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
        infoBox.setPadding(new Insets(40, 40, 40, 40));
        infoBox.setMinSize(300, 300);
        infoBox.setMaxSize(300, 300);
        infoBox.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        RadioButton[] events = new RadioButton[9];

        events[0] = new RadioButton("Asteroids");
        events[1] = new RadioButton("Crew Death");
        events[2] = new RadioButton("Salvage Ship");
        events[3] = new RadioButton("Cargo Loss");
        events[4] = new RadioButton("Fuel Loss");
        events[5] = new RadioButton("No Event");
        events[6] = new RadioButton("Crew Rescue");
        events[7] = new RadioButton("Alien Attack");
        events[8] = new RadioButton("Random Event");
        events[8].setSelected(true);
        info.setText("A random event will be trigered");
        for (int i = 0; i < events.length; i++) {
            events[i].setToggleGroup(group);
            events[i].setTextFill(Color.WHITE);
            events[i].setFont(Font.font("arial", 15));
            final int aux = i;
            if (i != 8) {
                events[i].setOnAction(eh -> {
                    info.setText(infoEvents.get(aux));
                });
            } else {
                events[i].setOnAction(eh -> {
                    info.setText("A random event will be trigered");
                });
            }

        }
        Button goAhead=new Button("Go Ahead");
        goAhead.setStyle(BOTAOCSS);
        goAhead.setOnMouseEntered(eh->{
            goAhead.setStyle(BOTAOHOVERCSS);
        });
        goAhead.setOnMouseExited(eh->{
            goAhead.setStyle(BOTAOCSS);
        });
        
        goAhead.setOnAction(eh->{
            
            for (int i = 0; i < events.length-1; i++) {
                if(events[i].isSelected()) {
                    gameObs.selectEvent(i);
                }  
            }
            if(events[8].isSelected())
                gameObs.randomEvent();
        });
        
        VBox botoesBox = new VBox();
        botoesBox.getChildren().addAll(events);
         botoesBox.getChildren().add(goAhead);
        botoesBox.setSpacing(15);
        botoesBox.setAlignment(Pos.CENTER_LEFT);
        botoesBox.setPadding(new Insets(0, 30, 0, 60));
        bp.setCenter(infoBox);
        bp.setLeft(botoesBox);

        return bp;
    }

}
