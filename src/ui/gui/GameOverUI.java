/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import observable.GameObservable;
import static ui.gui.util.Constantes.DEFEAT;
import static ui.gui.util.Constantes.GAMEOVER;
import static ui.gui.util.Constantes.VICTORY;
import static ui.gui.util.configureButtons.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import ui.gui.util.Images;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class GameOverUI extends VBox {

    private GameObservable jogoObs;
    private final Button newGame;
    private final Button exit;
    private final ImageView imageView1;

    public GameOverUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");

        ImageView imageView = new ImageView();
        imageView.setFitHeight(350);
        imageView.setFitWidth(350);
        imageView.setImage(Images.getImage(GAMEOVER));

        imageView1 = new ImageView();
        imageView1.setFitHeight(56);
        imageView1.setFitWidth(260);

        if (jogoObs.won()) {
            imageView1.setImage(Images.getImage(VICTORY));
        } else {
            imageView1.setImage(Images.getImage(DEFEAT));
        }

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(imageView, imageView1);

        newGame = new Button("NEW GAME");
        exit = new Button("EXIT");
        newGame.setStyle(BOTAOHOVERCSS);
        exit.setStyle(BOTAOCSS);

        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(newGame, exit));
        HBox hb = new HBox(newGame, exit);
        hb.setSpacing(30);
        hb.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().add(hb);
        this.setSpacing(50);

        configuraBotoes(botoes);
        registaListenerPropiedade();
        registaListenersEvent();
    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(CHANGE_STATE, (listener) -> {

            switch (jogoObs.getState()) {
                case GAMEOVER:
                    this.setVisible(true);
                    if (jogoObs.won()) {
                        imageView1.setImage(Images.getImage(VICTORY));
                    } else {
                        imageView1.setImage(Images.getImage(DEFEAT));
                    }
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }

    private void registaListenersEvent() {
        newGame.setOnAction(eh -> {
            jogoObs.newGame();
        });
        exit.setOnAction(eh -> {
            Platform.exit();
        });
    }

}
