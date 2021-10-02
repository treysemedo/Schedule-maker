/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import observable.GameObservable;
import static ui.gui.util.Constantes.ARROWDOWN;
import static ui.gui.util.Constantes.ARROWUP;
import static ui.gui.util.configureButtons.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.CONVERSOR;
import static ui.gui.util.Constantes.STABLE;
import ui.gui.util.Images;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class ResourceConversionUI extends HBox {

    private GameObservable jogoObs;
    private PanelCrew painelC;
    private Button fuel;
    private Button ammo;
    private Button shield;
    private Button back;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private Image up;
    private Image down;
    private Image stable;
    private HBox hb3;
    private HBox hb4;
    private HBox hb5;
    private HBox hb6;
    private HBox hb7;
    private BorderPane bp;
    private LogUI logUI;

    public ResourceConversionUI(GameObservable gameObs) {
        this.jogoObs = gameObs;
         bp=new BorderPane();
        bp.setMaxSize(800, 600);
        bp.setMinSize(800, 600);
        bp.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");

        up = Images.getImage(ARROWUP);
        down = Images.getImage(ARROWDOWN);
        stable = Images.getImage(STABLE);
        
        
       logUI=new LogUI(gameObs);
       this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(bp, logUI);
        this.setSpacing(20);
        registaListenerPropiedade();

    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {

            switch (jogoObs.getState()) {
                case RESOURCECONVERSION:
                    painelC = new PanelCrew(jogoObs);
                    bp.setRight(painelC);
                    bp.setCenter(conversorUI());
                    this.setVisible(true);
                    registarListenersEvent();
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }

    private void registarListenersEvent() {

        fuel.setOnAction(eh -> {
            jogoObs.produceFuel();
        });

        fuel.setOnMouseEntered(eh -> {
            fuel.setFocusTraversable(true);
            fuel.requestFocus();
            iv1.setImage(down);
            iv2.setImage(stable);
            iv3.setImage(down);
            iv4.setImage(down);
        });

        fuel.focusedProperty().addListener(cl -> {
            if (fuel.isFocused()) {
                fuel.setFocusTraversable(true);
                fuel.requestFocus();
                iv1.setImage(down);
                iv2.setImage(stable);
                iv3.setImage(down);
                iv4.setImage(down);
                fuel.setStyle(BOTAOHOVERCSS);
            } else {
                fuel.setStyle(BOTAOCSS);
            }
        });

        ammo.setOnMouseEntered(eh -> {
            ammo.setFocusTraversable(true);
            ammo.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(stable);
            iv4.setImage(stable);
        });

        ammo.focusedProperty().addListener(cl -> {
            if (ammo.isFocused()) {
                ammo.setFocusTraversable(true);
                ammo.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(stable);
                iv4.setImage(stable);
                ammo.setStyle(BOTAOHOVERCSS);
            } else {
                ammo.setStyle(BOTAOCSS);
            }
        });

        shield.setOnMouseEntered(eh -> {
            shield.setFocusTraversable(true);
            shield.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(down);
            iv4.setImage(stable);

        });

        shield.focusedProperty().addListener(cl -> {
            if (shield.isFocused()) {
                shield.setFocusTraversable(true);
                shield.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(down);
                iv4.setImage(stable);
                shield.setStyle(BOTAOHOVERCSS);
            } else {
                shield.setStyle(BOTAOCSS);
            }
        });

        ammo.setOnAction(eh -> {
            jogoObs.produceAmmo();
        });
        shield.setOnAction(eh -> {
            jogoObs.produceShield();
        });

        back.setOnMouseEntered(eh -> {
            back.setFocusTraversable(true);
            back.requestFocus();

        });

        back.focusedProperty().addListener(cl -> {
            if (back.isFocused()) {
                back.setFocusTraversable(true);
                back.requestFocus();
                back.setStyle(BOTAOHOVERCSS);
                hb3.setVisible(false);
            } else {
                back.setStyle(BOTAOCSS);
                hb3.setVisible(true);
            }
        });

        back.setOnAction(eh -> {
            jogoObs.goBack();
        });
    }

    private VBox conversorUI() {
        PanelShip painelN = new PanelShip(jogoObs);

        ImageView imageView = new ImageView();
        imageView.setImage(Images.getImage(CONVERSOR));
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        StackPane paneImg = new StackPane(imageView);
        paneImg.setAlignment(Pos.CENTER);

        fuel = new Button("PRODUCE FUEL");
        shield = new Button("PRODUCE SHIELD");
        ammo = new Button("PRODUCE AMMO");
        back = new Button("GO BACK");

        iv1 = new ImageView(stable);
        iv2 = new ImageView(stable);
        iv3 = new ImageView(stable);
        iv4 = new ImageView(stable);

        
        iv1.setFitHeight(20);
        iv1.setFitWidth(20);
        iv2.setFitHeight(20);
        iv2.setFitWidth(20);
        iv3.setFitHeight(20);
        iv3.setFitWidth(20);
        iv4.setFitHeight(20);
        iv4.setFitWidth(20);
        
        Label black = new Label("BLACK");
        Label blue = new Label("BLUE");
        Label green = new Label("GREEN");
        Label red = new Label("RED");
        black.setFont(Font.font("verdana", FontWeight.BOLD, 13));
        black.setTextFill(Color.WHITE);
        blue.setFont(Font.font("verdana", FontWeight.BOLD, 13));
        blue.setTextFill(Color.WHITE);
        green.setFont(Font.font("verdana", FontWeight.BOLD, 13));
        green.setTextFill(Color.WHITE);
        red.setFont(Font.font("verdana", FontWeight.BOLD, 13));
        red.setTextFill(Color.WHITE);

        

        hb4 = new HBox(black, iv1);
        hb5 = new HBox(blue, iv2);
        hb6 = new HBox(green, iv3);
        hb7 = new HBox(red, iv4);

        fuel.setStyle(BOTAOHOVERCSS);
        shield.setStyle(BOTAOCSS);
        ammo.setStyle(BOTAOCSS);
        back.setStyle(BOTAOCSS);
        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(fuel, shield, ammo, back));

        HBox hb2 = new HBox(fuel, shield, ammo, back);
        hb3 = new HBox(hb4, hb5, hb6, hb7);
        hb3.setMinHeight(32);
        hb3.setSpacing(30);
        hb3.setAlignment(Pos.BOTTOM_CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(20);

        VBox vb1 = new VBox();
        vb1.setSpacing(50);
        vb1.getChildren().addAll(painelN, paneImg, hb3, hb2);
        return vb1;
    }

}
