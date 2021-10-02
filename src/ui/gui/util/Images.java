/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import static ui.gui.util.Constantes.ALIEN;
import static ui.gui.util.Constantes.DEFEAT;
import static ui.gui.util.Constantes.DRONE;
import static ui.gui.util.Constantes.GAMEOVER;
import static ui.gui.util.Constantes.PATH_ALIEN;
import ui.gui.resource.ResourceLoader;
import static ui.gui.util.Constantes.ARROWDOWN;
import static ui.gui.util.Constantes.ARROWUP;
import static ui.gui.util.Constantes.BACKGROUND;
import static ui.gui.util.Constantes.CONVERSOR;
import static ui.gui.util.Constantes.CURSOR;
import static ui.gui.util.Constantes.LOGO;
import static ui.gui.util.Constantes.MILITARY;
import static ui.gui.util.Constantes.MINING;
import static ui.gui.util.Constantes.OPTION;
import static ui.gui.util.Constantes.PATH_ARROWDOWN;
import static ui.gui.util.Constantes.PATH_ARROWUP;
import static ui.gui.util.Constantes.PATH_BACKGROUND;
import static ui.gui.util.Constantes.PATH_CONVERSOR;
import static ui.gui.util.Constantes.PATH_CURSOR;
import static ui.gui.util.Constantes.PATH_DEFEAT;
import static ui.gui.util.Constantes.PATH_DRONE;
import static ui.gui.util.Constantes.PATH_GAMEOVER;
import static ui.gui.util.Constantes.PATH_LOGO;
import static ui.gui.util.Constantes.PATH_MILITARY;
import static ui.gui.util.Constantes.PATH_MINING;
import static ui.gui.util.Constantes.PATH_OPTION;
import static ui.gui.util.Constantes.PATH_PLANET_B;
import static ui.gui.util.Constantes.PATH_PLANET_BL;
import static ui.gui.util.Constantes.PATH_PLANET_G;
import static ui.gui.util.Constantes.PATH_PLANET_R;
import static ui.gui.util.Constantes.PATH_RESOURCE;
import static ui.gui.util.Constantes.PATH_RESOURCEALIEN;
import static ui.gui.util.Constantes.PATH_SHUTEDRONE;
import static ui.gui.util.Constantes.PATH_SHUTLE;
import static ui.gui.util.Constantes.PATH_SHUTLEALIEN;
import static ui.gui.util.Constantes.PATH_SPACE_STATION;
import static ui.gui.util.Constantes.PATH_STABLE;
import static ui.gui.util.Constantes.PATH_TERRENO;
import static ui.gui.util.Constantes.PATH_VICTORY;
import static ui.gui.util.Constantes.PLANET_B;
import static ui.gui.util.Constantes.PLANET_BL;
import static ui.gui.util.Constantes.PLANET_G;
import static ui.gui.util.Constantes.PLANET_R;
import static ui.gui.util.Constantes.RESOURCE;
import static ui.gui.util.Constantes.RESOURCEALIEN;
import static ui.gui.util.Constantes.SHUTLE;
import static ui.gui.util.Constantes.SHUTLEALIEN;
import static ui.gui.util.Constantes.SHUTLEDRONE;
import static ui.gui.util.Constantes.SPACE_STATION;
import static ui.gui.util.Constantes.STABLE;
import static ui.gui.util.Constantes.TERRENO;
import static ui.gui.util.Constantes.VICTORY;

/**
 *
 * @author treys
 */
public abstract class Images {

    private static final Map<String, Image> IMAGES = new HashMap<>();

    static {
        IMAGES.put(TERRENO, new Image(ResourceLoader.getMyResourceFile(PATH_TERRENO)));
        IMAGES.put(DRONE, new Image(ResourceLoader.getMyResourceFile(PATH_DRONE)));
        IMAGES.put(ALIEN, new Image(ResourceLoader.getMyResourceFile(PATH_ALIEN)));
        IMAGES.put(SHUTLEDRONE, new Image(ResourceLoader.getMyResourceFile(PATH_SHUTEDRONE)));
        IMAGES.put(SHUTLE, new Image(ResourceLoader.getMyResourceFile(PATH_SHUTLE)));
        IMAGES.put(RESOURCE, new Image(ResourceLoader.getMyResourceFile(PATH_RESOURCE)));
        IMAGES.put(SHUTLEALIEN, new Image(ResourceLoader.getMyResourceFile(PATH_SHUTLEALIEN)));
        IMAGES.put(RESOURCEALIEN, new Image(ResourceLoader.getMyResourceFile(PATH_RESOURCEALIEN)));
        IMAGES.put(GAMEOVER, new Image(ResourceLoader.getMyResourceFile(PATH_GAMEOVER)));
        IMAGES.put(VICTORY, new Image(ResourceLoader.getMyResourceFile(PATH_VICTORY)));
        IMAGES.put(DEFEAT, new Image(ResourceLoader.getMyResourceFile(PATH_DEFEAT)));
        IMAGES.put(PLANET_B, new Image(ResourceLoader.getMyResourceFile(PATH_PLANET_B)));
        IMAGES.put(PLANET_R, new Image(ResourceLoader.getMyResourceFile(PATH_PLANET_R)));
        IMAGES.put(PLANET_BL, new Image(ResourceLoader.getMyResourceFile(PATH_PLANET_BL)));
        IMAGES.put(PLANET_G, new Image(ResourceLoader.getMyResourceFile(PATH_PLANET_G)));
        IMAGES.put(CONVERSOR, new Image(ResourceLoader.getMyResourceFile(PATH_CONVERSOR)));
        IMAGES.put(LOGO, new Image(ResourceLoader.getMyResourceFile(PATH_LOGO)));
        IMAGES.put(MILITARY, new Image(ResourceLoader.getMyResourceFile(PATH_MILITARY)));
        IMAGES.put(MINING, new Image(ResourceLoader.getMyResourceFile(PATH_MINING)));
        IMAGES.put(SPACE_STATION, new Image(ResourceLoader.getMyResourceFile(PATH_SPACE_STATION)));
        IMAGES.put(BACKGROUND, new Image(ResourceLoader.getMyResourceFile(PATH_BACKGROUND)));
        IMAGES.put(OPTION, new Image(ResourceLoader.getMyResourceFile(PATH_OPTION)));
        IMAGES.put(CURSOR, new Image(ResourceLoader.getMyResourceFile(PATH_CURSOR)));
         IMAGES.put(ARROWUP, new Image(ResourceLoader.getMyResourceFile(PATH_ARROWUP)));
        IMAGES.put(ARROWDOWN, new Image(ResourceLoader.getMyResourceFile(PATH_ARROWDOWN)));
        IMAGES.put(STABLE, new Image(ResourceLoader.getMyResourceFile(PATH_STABLE)));
       }    

    public static Image getImage(String nome) {
        return IMAGES.get(nome);
    }

}
