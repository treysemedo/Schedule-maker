/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import ui.gui.util.configureButtons;
import ui.gui.util.Images;
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
import javafx.scene.layout.BorderPane;
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
import logic.data.util.EnumResource;
import observable.GameObservable;
import static ui.gui.util.Constantes.PLANET_B;
import static ui.gui.util.Constantes.PLANET_BL;
import static ui.gui.util.Constantes.PLANET_G;
import static ui.gui.util.Constantes.PLANET_R;
import static ui.gui.util.configureButtons.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class PlanetOrbitingUI extends HBox {

    private final GameObservable gameObs;
    
    private final Button travel;
    private final Button land;
    private final Button convertR;
    private final LogUI logUI;
    private BorderPane bp;
    

    PlanetOrbitingUI(GameObservable jogoObs) {
        this.gameObs = jogoObs;
        this.setAlignment(Pos.CENTER);
        bp=new BorderPane();
        bp.setMaxSize(800, 600);
        bp.setMinSize(800, 600);
        
        bp.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        

        
        land = new Button("LAND ON PLANET");
        convertR = new Button("CONVERT RESOURSE");
        travel = new Button("TRAVEL");
        
        logUI=new LogUI(jogoObs);
        
        this.getChildren().addAll(bp, logUI);
        this.setSpacing(20);
        registaListenerPropiedade();
        registarListenersEvent();
    }
    
    private void registaListenerPropiedade() {
        gameObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {

            switch (gameObs.getState()) {
                case PLANETORBITING:
                    PanelCrew painelC = new PanelCrew(gameObs);
                    bp.setCenter(desenhaPlanetBox());
                    bp.setRight(painelC);
                    this.setVisible(true);
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }
    
    private void registarListenersEvent() {
        land.setOnAction(eh -> {
            gameObs.landOnPlanet();
        });
        convertR.setOnAction(eh -> {
            gameObs.convertResources();
        });
        travel.setOnAction(eh -> {
            gameObs.spaceTravel();
        });
    }

   

    private VBox desenhaPlanetBox() {
        PanelShip painelN = new PanelShip(gameObs);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(500);

        switch (gameObs.getPlanetType()) {
            case RED:
                imageView.setImage(Images.getImage(PLANET_R));
                break;
            case GREEN:
                imageView.setImage(Images.getImage(PLANET_G));
                break;
            case BLUE:
                imageView.setImage(Images.getImage(PLANET_B));
                break;
            case BLACK:
               imageView.setImage(Images.getImage(PLANET_BL));
                break;
        }

        StackPane paneImg = new StackPane();
        paneImg.getChildren().add(imageView);
        paneImg.setAlignment(Pos.CENTER_RIGHT);

        

        land.setStyle(BOTAOHOVERCSS);
        travel.setStyle(BOTAOCSS);
        convertR.setStyle(BOTAOCSS);
        land.setMaxWidth(Double.MAX_VALUE);
        travel.setMaxWidth(Double.MAX_VALUE);;
        convertR.setMaxWidth(Double.MAX_VALUE);
        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(land, travel, convertR));

        GridPane gp = new GridPane();
        gp.setVgap(20);
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(20);
        gp.addRow(0, land, convertR, travel);

        if (gameObs.planetHasStation()) {

            Button visitaS = new Button("VISIT SPACE STATION");
            visitaS.setStyle(BOTAOCSS);
            visitaS.setMaxWidth(Double.MAX_VALUE);
            visitaS.setOnAction(eh -> {
                gameObs.visitStation();
            });
            botoes.add(visitaS);
            gp.add(visitaS, 1, 1);

        }

        configuraBotoes(botoes);
        
        
        VBox res = new VBox(painelN, paneImg, infoPlanet(), gp);
        res.setSpacing(40);
        
        return res;

    }

    private HBox infoPlanet() {

        List<EnumResource> aux = gameObs.getResources();
        HBox hb = new HBox();
        Label lb = new Label("RESOURCES AVALIABLE");
        lb.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        lb.setTextFill(Color.WHITE);
        hb.getChildren().add(lb);
        hb.setAlignment(Pos.CENTER);

        for (EnumResource a : aux) {
            hb.getChildren().add(desenho(configureButtons.getCorResource1(a)));
        }
        if (gameObs.planetHasArtfact()) {
            hb.getChildren().add(desenho(Color.FUCHSIA));
        }
        hb.setSpacing(30);
        return hb;
    }

    private StackPane desenho(Color cor) {

        StackPane grafico = new StackPane();
        grafico.setPadding(new Insets(10, 10, 10, 10));
        grafico.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2, 2, 2, 2))));
        grafico.setAlignment(Pos.CENTER_RIGHT);
        grafico.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(15), null)));

        return grafico;

    }

    
    
     
}
