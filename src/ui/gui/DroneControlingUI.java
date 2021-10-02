/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import ui.gui.util.configureButtons;
import ui.gui.util.Images;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.data.util.EnumCell;
import observable.GameObservable;
import static ui.gui.util.Constantes.ALIEN;
import static ui.gui.util.Constantes.DRONE;
import static ui.gui.util.Constantes.RESOURCE;
import static ui.gui.util.Constantes.RESOURCEALIEN;
import static ui.gui.util.Constantes.SHUTLE;
import static ui.gui.util.Constantes.SHUTLEALIEN;
import static ui.gui.util.Constantes.SHUTLEDRONE;
import static ui.gui.util.Constantes.TERRENO;
import static ui.gui.util.Propriedade.ALTERA_MAPA;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class DroneControlingUI extends VBox {

    private final GameObservable jogoObs;
    private GridPane gp;

    public DroneControlingUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(1000, 650);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        this.setAlignment(Pos.CENTER);
        this.requestFocus();
        this.setFocusTraversable(true);

        Label label = new Label("SATELLITE IMAGE");
        label.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITE);
        this.getChildren().addAll(label, new HBox());

        registerPropertyListener();
        registarListenerEvent();
    }

    private void registerPropertyListener() {
        jogoObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {

            switch (jogoObs.getState()) {
                case DRONECONTROLING:
                    this.getChildren().set(1, drawTerrain());
                    this.setVisible(true);
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });

        jogoObs.addPropertyChangeListener(ALTERA_MAPA, listener -> {
            this.getChildren().set(1, drawTerrain());

        });

       
    }

    private HBox drawTerrain() {
        HBox container=new HBox();
        HBox drone=new HBox();
        HBox alien=new HBox();
        VBox info= new VBox();
        ImageView droneIV=new ImageView();
        ImageView alienIV=new ImageView();
        Label droneHP=new Label(jogoObs.getDroneHp()+" HP");
         Label alienHP=new Label();
        
        alienHP.setText(jogoObs.getTimerAlien()!=-1 ? "Respawns in " + jogoObs.getTimerAlien():jogoObs.getAlienType()+" Alien");
      
        droneHP.setTextFill(Color.WHITE);
        droneHP.setFont(Font.font("verdana", FontWeight.BOLD, 12));
         droneHP.setPadding(new Insets(7));
        alienHP.setTextFill(Color.WHITE);
        alienHP.setFont(Font.font("verdana", FontWeight.BOLD, 12));
         alienHP.setPadding(new Insets(7));
        droneIV.setFitHeight(30);
        droneIV.setFitWidth(30);
        alienIV.setFitHeight(30);
        alienIV.setFitWidth(30);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(20);
        droneIV.setImage(Images.getImage(DRONE));
        alienIV.setImage(Images.getImage(ALIEN));
        drone.getChildren().addAll(droneIV, droneHP);
        alien.getChildren().addAll(alienIV, alienHP);
        info.getChildren().addAll(drone, alien);
        
        StackPane sp1=new StackPane();
        StackPane sp2=new StackPane();
        StackPane sp3=new StackPane();
       
        
        gp = new GridPane();
        gp.setMaxSize(700, 500);
        gp.setMinSize(700, 500);
        sp1.setMaxSize(700, 500);
        sp1.setMinSize(700, 500);
        sp2.setMaxSize(700, 500);
        sp2.setMinSize(700, 500);
        
        sp1.setBackground(new Background(new BackgroundImage(Images.getImage(TERRENO), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(705, 505, false, false, false, false))));
        //gp.setBackground(new Background(new BackgroundImage(Images.getImage(TERRENO), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(705, 505, false, false, false, false))));
        gp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        EnumCell aux[][];
        aux = jogoObs.getInfoTerrain();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                gp.add(drawCell(aux[i][j]), i, j);
            }
        }
        
        switch(jogoObs.getPlanetType()){
            case BLACK:
         
                 sp2.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);");
                 break;
            case BLUE:
                 sp2.setStyle("-fx-background-color: rgba(0, 0, 255, 0.1);");
                 break;
            case GREEN:
                 sp2.setStyle("-fx-background-color: rgba(0, 255, 0, 0.1);");
                 break;
                 
           case RED:
                 sp2.setStyle("-fx-background-color: rgba(255, 0, 0, 0.1);");
                 break;
      
        }
        
        sp3.getChildren().addAll(sp1, sp2, gp);
       container.setPadding(new Insets(50));
       container.setSpacing(30);
        container.getChildren().addAll(sp3, info);
        return container;
    }

    private HBox drawCell(EnumCell enumCell) {
        HBox hb = new HBox();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        hb.setMaxSize((gp.getMaxWidth() / 6) - 1, gp.getMaxHeight() / 6 - 0.5);
        hb.setMinSize(gp.getMinWidth() / 6 - 1, gp.getMinHeight() / 6 - 0.5);
        hb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));

        switch (enumCell) {
            case SHUTLEDRONE:
                imageView.setImage(Images.getImage(SHUTLEDRONE));
                imageView.setFitHeight(80);
                imageView.setFitWidth(90);
                break;
            case SHUTLE:
                imageView.setFitHeight(75);
                imageView.setFitWidth(75);
                imageView.setImage(Images.getImage(SHUTLE));

                break;
            case ALIEN:
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                hb.setBackground(new Background(new BackgroundFill(configureButtons.getCorAlien(jogoObs.getAlienType()), new CornerRadii(60), new Insets(18, 35, 20, 36))));
                imageView.setImage(Images.getImage(ALIEN));
                break;
            case DRONE:
                imageView.setImage(Images.getImage(DRONE));
                break;
            case RESOURCE:
                imageView.setImage(Images.getImage(RESOURCE));
                imageView.setFitHeight(35);
                imageView.setFitWidth(35);

                if (jogoObs.terrainHasArtfact()) {
                    hb.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, new CornerRadii(100), new Insets(25, 40, 25, 40))));
                } else {
                    hb.setBackground(new Background(new BackgroundFill(configureButtons.getCorResource2(jogoObs.terrainResource()), new CornerRadii(100), new Insets(25, 40, 25, 40))));
                }

                break;
            case RESOURCEDRONE:
                imageView.setImage(Images.getImage(DRONE));
                 if (jogoObs.terrainHasArtfact()) {
                    hb.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, new CornerRadii(100), new Insets(25, 40, 25, 40))));
                } else {
                    hb.setBackground(new Background(new BackgroundFill(configureButtons.getCorResource2(jogoObs.terrainResource()), new CornerRadii(100), new Insets(25, 40, 25, 40))));
                }
                break;

            case SHUTLEALIEN:
                imageView.setImage(Images.getImage(SHUTLEALIEN));
                imageView.setFitHeight(80);
                imageView.setFitWidth(100);
                break;
            case RESOURCEALIEN:
                imageView.setImage(Images.getImage(RESOURCEALIEN));
                break;

        }
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(imageView);

        return hb;
    }

    private void registarListenerEvent() {

        this.setOnKeyPressed(eh -> {
            switch (eh.getCode()) {
                case UP:
                    jogoObs.DroneMove(-1, 0);
                    break;
                case DOWN:
                    jogoObs.DroneMove(1, 0);
                    break;
                case LEFT:
                    jogoObs.DroneMove(0, -1);
                    break;
                case RIGHT:
                    jogoObs.DroneMove(0, 1);
                    break;
            }
        });
    }

}
