/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.states.util.EnumState;
import observable.GameObservable;
import static ui.gui.PanelShip.drawGradient;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.LOGO;
import static ui.gui.util.Constantes.MILITARY;
import static ui.gui.util.Constantes.MINING;
import ui.gui.util.Images;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class ShipSelectionUI extends StackPane {

    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private StackPane shipPreview;
    private StackPane shipInfo;
    private VBox vb1;
    private VBox vb2;
    private VBox vb3;
    private VBox vb4;
    private HBox hb1;
    private HBox hb2;
    private HBox fuel1;
    private HBox shield1;
    private HBox ammo1;
    private HBox cargo1;
    private HBox fuel2;
    private HBox shield2;
    private HBox ammo2;
    private HBox cargo2;
    private Label lb1;
    private Button continue_bt;
    private Button mining_bt;
    private Button military_bt;
    private GameObservable jogoObs;

    public ShipSelectionUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;

        createComponents();
        configureComponents();

        registerControlListeners();
        registerPropertiesListeners();
    }

    private void configureComponents() {
        imageView.setImage(Images.getImage(LOGO));

        vb1.setVisible(true);
        hb2.setVisible(false);
        vb2.setMinSize(700, 500);
        vb2.setPrefSize(700, 500);
        vb2.setMaxSize(700, 500);
        vb2.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        shipInfo.setMinSize(220, 120);
        shipInfo.setPrefSize(220, 120);
        shipInfo.setMaxSize(220, 120);
        shipInfo.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");

        continue_bt.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        continue_bt.setFocusTraversable(true);
        continue_bt.requestFocus();
        continue_bt.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        continue_bt.setTextFill(Color.DARKORANGE);
        continue_bt.setBorder(new Border(new BorderStroke(Color.DARKORANGE, Color.TRANSPARENT, Color.DARKORANGE, Color.TRANSPARENT, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, null, null, new Insets(10, 0, 0, 0))));

        lb1.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        lb1.setTextFill(Color.DARKORANGE);

        imageView1.setImage(Images.getImage(MINING));
        imageView1.setFitHeight(350);
        imageView1.setFitWidth(350);
        imageView1.setVisible(true);

        imageView2.setImage(Images.getImage(MILITARY));
        imageView2.setFitHeight(250);
        imageView2.setFitWidth(528);
        imageView2.setVisible(false);

        mining_bt.setStyle(BOTAOHOVERCSS);
        military_bt.setStyle(BOTAOCSS);

        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(20);
        hb1.getChildren().addAll(mining_bt, military_bt);

        vb1.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER);
        shipInfo.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        shipInfo.setPadding(new Insets(20));
        hb2.setSpacing(40);
        vb1.setSpacing(40);
        vb2.setSpacing(40);
        vb4.setVisible(false);

        vb1.getChildren().addAll(imageView, continue_bt);
        vb2.getChildren().addAll(lb1, shipPreview, hb1);
        vb3.getChildren().addAll(fuel1, shield1, ammo1, cargo1);
        vb4.getChildren().addAll(fuel2, shield2, ammo2, cargo2);
        shipInfo.getChildren().addAll(vb3, vb4);
        hb2.getChildren().addAll(vb2, shipInfo);
        this.getChildren().addAll(vb1, hb2);
    }

    private void createComponents() {
        imageView = new ImageView();
        shipInfo=new StackPane();
        vb1 = new VBox();
        vb2 = new VBox();
        vb3 = new VBox();
        vb4 = new VBox();
        continue_bt = new Button("PRESS TO CONTINUE");
        lb1 = new Label("SELECT YOUR SHIP");
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        shipPreview = new StackPane(imageView1, imageView2);
        mining_bt = new Button("MINING");
        military_bt = new Button("MILITARY");
        hb1 = new HBox();
        hb2 = new HBox();
        fuel1 = drawGradient("FUEL  ", 30, 30, Color.WHITE, Color.RED, Color.YELLOW);
        shield1 = drawGradient("SHIELD",30, 30, Color.WHITE, Color.DARKGREY, Color.WHITE);
        ammo1 = drawGradient("WEAPON", 10, 30, Color.WHITE, Color.DARKGREEN, Color.GREEN);
        cargo1 = drawGradient("CARGO ",30, 30, Color.WHITE, Color.BLUE, Color.DARKBLUE);
        fuel2 = drawGradient("FUEL  ", 20, 30, Color.WHITE, Color.RED, Color.YELLOW);
        shield2 = drawGradient("SHIELD",30, 30, Color.WHITE, Color.DARKGREY, Color.WHITE);
        ammo2 = drawGradient("WEAPON", 30, 30, Color.WHITE, Color.DARKGREEN, Color.GREEN);
        cargo2 = drawGradient("CARGO ",15, 30, Color.WHITE, Color.BLUE, Color.DARKBLUE);
    }

    private void registerPropertiesListeners() {
        jogoObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {
            if (jogoObs.getState() == EnumState.SHIPSELECTION) {
                this.setVisible(true);
            } else {
                this.setVisible(false);
            }
        });
    }

    private void registerControlListeners() {
        continue_bt.setOnAction(eh -> {
            vb1.setVisible(false);
            hb2.setVisible(true);
        });

        mining_bt.setOnAction(eh -> {
            jogoObs.SelectShip(2);
        });
        military_bt.setOnAction(eh -> {
            jogoObs.SelectShip(1);
        });

        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(mining_bt, military_bt));
        configureButtons(botoes);

    }

    private void configureButtons(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setOnMouseEntered(eh -> {
                if (bt.equals(mining_bt)) {
                    imageView1.setVisible(true);
                    vb3.setVisible(true);
                    imageView2.setVisible(false);
                    vb4.setVisible(false);
                } else if (bt.equals(military_bt)) {
                    imageView1.setVisible(false);
                    vb4.setVisible(true);
                    imageView2.setVisible(true);
                    vb3.setVisible(false);
                }
                buttonsOutStyle(botoes);
                bt.setStyle(BOTAOHOVERCSS);

            });
            bt.setOnMouseExited(eh -> {
                buttonsOutStyle(botoes);
                showFocused(botoes);
            });

            bt.setOnKeyPressed(eh -> {
                buttonsOutStyle(botoes);
                showFocused(botoes);
            });

        }
    }

    private void buttonsOutStyle(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setStyle(BOTAOCSS);
        }
    }

    private void showFocused(List<Button> botoes) {
        for (Button bt : botoes) {
            if (bt.isFocused()) {
                if (bt.equals(mining_bt)) {
                    imageView1.setVisible(true);
                    vb3.setVisible(true);
                    imageView2.setVisible(false);
                    vb4.setVisible(false);
                } else if (bt.equals(military_bt)) {
                    imageView1.setVisible(false);
                    vb4.setVisible(true);
                    imageView2.setVisible(true);
                    vb3.setVisible(false);
                }
                bt.setStyle(BOTAOHOVERCSS);
            } else {
                bt.setStyle(BOTAOCSS);
            }
        }
    }

}
