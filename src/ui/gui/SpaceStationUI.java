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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.data.util.EnumResource;
import observable.GameObservable;
import static ui.gui.util.Constantes.ARROWDOWN;
import static ui.gui.util.Constantes.ARROWUP;
import static ui.gui.util.configureButtons.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.SPACE_STATION;
import static ui.gui.util.Constantes.STABLE;
import ui.gui.util.Images;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class SpaceStationUI extends HBox {

    private GameObservable gameObs;
    private PanelCrew painelC;
    private GridPane gp;
    private VBox vb1;
    private Button cargo, weapon,resource,  crew, ship, back;
    private Button convert;
    private int n1, n2;
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

    public SpaceStationUI(GameObservable jogoObs) {
        this.gameObs = jogoObs;
        bp=new BorderPane();
        bp.setMaxSize(800, 600);
        bp.setMinSize(800, 600);
        bp.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        
        logUI=new LogUI(gameObs);
        
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(bp, logUI);
        this.setSpacing(20);
        registerPropertyListener();

    }

    private void registerPropertyListener() {
        gameObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {
           
            switch (gameObs.getState()) {
                case SPACESTATION:
                   
                    painelC = new PanelCrew(gameObs);
                    bp.setRight(painelC);
                    bp.setCenter(operacoesUI());
                    this.setVisible(true);
                    registaListenerEvent();
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }

    private void registaListenerEvent() {
        cargo.setOnAction(eh -> {
            gameObs.upgradeCargo();
        });
        cargo.setOnMouseEntered(eh -> {
            cargo.setFocusTraversable(true);
            cargo.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(down);
            iv4.setImage(down);
        });

        cargo.focusedProperty().addListener(cl -> {
            if (cargo.isFocused()) {
                cargo.setFocusTraversable(true);
                cargo.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(down);
                iv4.setImage(down);
                cargo.setStyle(BOTAOHOVERCSS);
            }else{
                cargo.setStyle(BOTAOCSS);
            }
        });
        
        weapon.setOnAction(eh -> {
            gameObs.upgradeWeapon();
        });
        
        weapon.setOnMouseEntered(eh -> {
            weapon.setFocusTraversable(true);
            weapon.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(down);
            iv4.setImage(down);
        });

        weapon.focusedProperty().addListener(cl -> {
            if (weapon.isFocused()) {
                weapon.setFocusTraversable(true);
                weapon.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(down);
                iv4.setImage(down);
                weapon.setStyle(BOTAOHOVERCSS);
            }else{
                weapon.setStyle(BOTAOCSS);
            }
        });
        resource.setOnAction(eh -> {
            gp.setVisible(false);
            hb3.setVisible(false);
            vb1.setVisible(true);
        });
        
        resource.setOnMouseEntered(eh -> {
            resource.setFocusTraversable(true);
            resource.requestFocus();
            iv1.setImage(stable);
            iv2.setImage(stable);
            iv3.setImage(stable);
            iv4.setImage(stable);
        });

        resource.focusedProperty().addListener(cl -> {
            if (resource.isFocused()) {
                resource.setFocusTraversable(true);
                resource.requestFocus();
                iv1.setImage(stable);
                iv2.setImage(stable);
                iv3.setImage(stable);
                iv4.setImage(stable);
                resource.setStyle(BOTAOHOVERCSS);
            }else{
                resource.setStyle(BOTAOCSS);
            }
        });
        convert.setOnAction(eh -> {
            gameObs.changeForResource(EnumResource.valueOf(n1), EnumResource.valueOf(n2));
            n1=n2=1;
            gp.setVisible(true);
            hb3.setVisible(true);
            vb1.setVisible(false);   
        });
       
        
        crew.setOnAction(eh->{
            gameObs.hireCrewMember();
            
        });
        
        crew.setOnMouseEntered(eh -> {
            crew.setFocusTraversable(true);
            crew.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(down);
            iv4.setImage(down);
        });

        crew.focusedProperty().addListener(cl -> {
            if (crew.isFocused()) {
                crew.setFocusTraversable(true);
                crew.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(down);
                iv4.setImage(down);
                crew.setStyle(BOTAOHOVERCSS);
            }else{
                crew.setStyle(BOTAOCSS);
            }
        });
        ship.setOnAction(eh->{
            gameObs.setFullShield();    
        });
        ship.setOnMouseEntered(eh -> {
            ship.setFocusTraversable(true);
            ship.requestFocus();
            iv1.setImage(down);
            iv2.setImage(down);
            iv3.setImage(down);
            iv4.setImage(down);
        });

        ship.focusedProperty().addListener(cl -> {
            if (ship.isFocused()) {
                ship.setFocusTraversable(true);
                ship.requestFocus();
                iv1.setImage(down);
                iv2.setImage(down);
                iv3.setImage(down);
                iv4.setImage(down);
                ship.setStyle(BOTAOHOVERCSS);
            }else{
                ship.setStyle(BOTAOCSS);
            }
        });
        
       
        
        back.setOnAction(eh->{
            gameObs.undock();
        });
        
        back.setOnMouseEntered(eh -> {
            back.setFocusTraversable(true);
            back.requestFocus();
        });

        back.focusedProperty().addListener(cl -> {
            if (back.isFocused()) {
                back.setFocusTraversable(true);
                back.requestFocus();
                hb3.setVisible(false);
                back.setStyle(BOTAOHOVERCSS);
            }else{
                back.setStyle(BOTAOCSS);
                hb3.setVisible(true);
            }
        });
        
        

    }

    private VBox operacoesUI() {
        PanelShip painelN = new PanelShip(gameObs);
        
        ImageView imageView = new ImageView();
        imageView.setImage(Images.getImage(SPACE_STATION));
        imageView.setFitHeight(200);
        imageView.setFitWidth(500);
        StackPane paneImg = new StackPane(imageView);
        paneImg.setAlignment(Pos.CENTER);

        cargo = new Button("Upgrade Cargo Holder");
        weapon = new Button("Upgrade weapon system");
        resource = new Button("Exchange  Resources");
        crew = new Button("Hire a crew Member");
        ship = new Button("Fix Ship Armor");
        back = new Button("Go Back");

        up = Images.getImage(ARROWUP);
        down = Images.getImage(ARROWDOWN);
        stable = Images.getImage(STABLE);
        
        ToggleGroup takeGroup = new ToggleGroup();
        ToggleGroup putGroup = new ToggleGroup();
        RadioButton[] botao = new RadioButton[8];
        GridPane gp1 = new GridPane();

        gp1.setVgap(40);
        gp1.setHgap(40);
        gp1.setAlignment(Pos.CENTER);
        botao[0] = new RadioButton("Red");
        botao[1] = new RadioButton("Green");
        botao[2] = new RadioButton("Black");
        botao[3] = new RadioButton("Blue");
        botao[4] = new RadioButton("Red");
        botao[5] = new RadioButton("Green");
        botao[6] = new RadioButton("Black");
        botao[7] = new RadioButton("Blue");
        Label lose = new Label("RESOURCE TO LOSE:");
        Label gain = new Label("RESOURCE TO GAIN:");
        lose.setTextFill(Color.DARKORANGE);
        gain.setTextFill(Color.DARKORANGE);
        lose.setFont(Font.font(("verdana"), 15));
        gain.setFont(Font.font(("verdana"), 15));
        gp1.addRow(0, lose);
        gp1.addRow(1, gain);
        
        for (int i = 0; i < 4; i++) {
            botao[i].setToggleGroup(takeGroup);
            botao[i].setTextFill(Color.WHITE);
            botao[i].setFont(Font.font(("verdana"), 13));
            final int aux=i;
            botao[i].setOnAction(eh->{
                n1=aux+1;
            });
            gp1.addRow(0, botao[i]);
        }
        
        for (int i = 4; i < 8; i++) {
            botao[i].setToggleGroup(putGroup);
            botao[i].setTextFill(Color.WHITE);
            botao[i].setFont(Font.font(("verdana"), 13));
             final int aux=i;
            botao[i].setOnAction(eh->{
                n2=aux-3;
            });
            gp1.addRow(1, botao[i]);
        }
        n1=1;
        n2=1;
        botao[0].setSelected(true);
        botao[4].setSelected(true);

        convert = new Button("Convert");
        convert.setStyle(BOTAOHOVERCSS);
        configuraBotoes(Arrays.asList(convert));
        vb1 = new VBox(gp1, convert);
        vb1.setVisible(false);
        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(40);

        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(cargo, weapon, resource, crew, ship, back));
        for (Button bt : botoes) {
            bt.setStyle(BOTAOCSS);
            bt.setMaxWidth(Double.MAX_VALUE);
        }
        cargo.setStyle(BOTAOHOVERCSS);

        gp = new GridPane();
        gp.addRow(0, cargo, weapon, resource);
        gp.addRow(1, crew, ship, back);
        gp.setVisible(true);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.setAlignment(Pos.CENTER);
        
        iv1 = new ImageView(stable);
        iv2 = new ImageView(stable);
        iv3 = new ImageView(stable);
        iv4 = new ImageView(stable);

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

        iv1.setFitHeight(20);
        iv1.setFitWidth(20);
        iv2.setFitHeight(20);
        iv2.setFitWidth(20);
        iv3.setFitHeight(20);
        iv3.setFitWidth(20);
        iv4.setFitHeight(20);
        iv4.setFitWidth(20);

        hb4 = new HBox(black, iv1);
        hb5 = new HBox(blue, iv2);
        hb6 = new HBox(green, iv3);
        hb7 = new HBox(red, iv4);
        
        hb3 = new HBox(hb4, hb5, hb6, hb7);
        hb3.setAlignment(Pos.CENTER);
        hb3.setSpacing(30);
       

        StackPane botoesPane = new StackPane(gp, vb1);

        VBox vbF = new VBox();
        vbF.setSpacing(40);
        vbF.getChildren().addAll(painelN, paneImg, hb3, botoesPane);
        return vbF;
    }
}
