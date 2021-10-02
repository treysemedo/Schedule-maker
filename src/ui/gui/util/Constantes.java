/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author treys
 */
public class Constantes {

    
    public static final String CURSOR = "cursor";
    public static final String PATH_CURSOR= "images/cursor1.png";
    public static final String STABLE = "stable";
    public static final String PATH_STABLE= "images/stable.png";
    public static final String ARROWUP = "arrowup";
    public static final String PATH_ARROWUP= "images/arrow_up.png";
    public static final String ARROWDOWN = "arrowdown";
    public static final String PATH_ARROWDOWN= "images/arrow_down.png";
    public static final String TERRENO = "terreno";
    public static final String PATH_TERRENO = "images/terrain.png";
    public static final String DRONE = "drone";
    public static final String PATH_DRONE = "images/drone.png";
    public static final String ALIEN = "alien";
    public static final String PATH_ALIEN = "images/alien.png";
    public static final String SHUTLEDRONE = "shutledrone";
    public static final String PATH_SHUTEDRONE = "images/shutledrone.png";
    public static final String SHUTLE = "shutle";
    public static final String PATH_SHUTLE = "images/shutle.png";
    public static final String RESOURCE = "resource";
    public static final String PATH_RESOURCE = "images/resource.png";
    public static final String SHUTLEALIEN = "shutleAlien";
    public static final String PATH_SHUTLEALIEN = "images/shutleAlien.png";
    public static final String RESOURCEALIEN = "resourceAlien";
    public static final String PATH_RESOURCEALIEN = "images/resourceAlien.png";
    public static final String GAMEOVER = "gameOver";
    public static final String PATH_GAMEOVER = "images/gameOver.png";
    public static final String VICTORY = "victory";
    public static final String PATH_VICTORY = "images/victory.png";
    public static final String DEFEAT = "defeat";
    public static final String PATH_DEFEAT = "images/defeat.png";
    public static final String PLANET_B = "planetB";
    public static final String PATH_PLANET_B = "images/planetB.png";
    public static final String PLANET_R = "planetR";
    public static final String PATH_PLANET_R = "images/planetR.png";
    public static final String PLANET_BL = "planetBL";
    public static final String PATH_PLANET_BL = "images/planetBL.png";
    public static final String PLANET_G = "planetG";
    public static final String PATH_PLANET_G = "images/planetG.png";
    public static final String CONVERSOR = "conversor";
    public static final String PATH_CONVERSOR= "images/conversion.png";
    public static final String LOGO = "logo";
    public static final String PATH_LOGO= "images/logo.png";
    public static final String MILITARY = "military";
    public static final String PATH_MILITARY= "images/military.png";
    public static final String MINING = "mining";
    public static final String PATH_MINING= "images/mining.png";
     public static final String SPACE_STATION = "spacestation";
    public static final String PATH_SPACE_STATION= "images/station.png";
    public static final String BACKGROUND = "background";
    public static final String PATH_BACKGROUND= "images/espaco.jpg";
     public static final String OPTION = "options";
    public static final String PATH_OPTION= "images/options.png";
    public final static String INFO_ASTEROIDS="Your ship travels through an asteroid field and causes you to lose 2 shield cells";
    public final static String INFO_CREWDEATH="One of your crew members was irresponsible and played with a space creature, he died imidiatly after touching it ";
    public final static String INFO_SALVAGESHIP="You and your crew have encountered a ghost spaceship, after safely explore it you´ve found some resources that can still be used ";
    public final static String INFO_CARGOLOSS="An asteroid field, luckly your captain has guided your crew out of there, some damage were made to the casquet and you lost some resource";
    public final static String INFO_FUELLOSS="A dangerous camp force is ahead, to escape it your ship needs max potency,extra fuel will needed";
    public final static String INFO_NOEVENT="Just a regular exploration day thru space, nothing new today";
    public final static String INFO_CREWRESCUE="You find a ship in destress with a lone crew member. your crew has new member, make her feel welcome";
    public final static String INFO_ALIENATTACK="A random alien attack causes damage to the ship which causes you to lose 1 shield\n" +
"cell, 1 weapon cell and 1 crew member";
    public static ArrayList<String> infoEvents=new ArrayList<>(Arrays.asList(INFO_ASTEROIDS,INFO_CREWDEATH ,INFO_SALVAGESHIP, INFO_CARGOLOSS,INFO_FUELLOSS, INFO_NOEVENT, INFO_CREWRESCUE, INFO_ALIENATTACK));
    public static String BOTAOCSS="-fx-background-color: " +
"        #090a0c" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
"        linear-gradient(#20262b, #191d22)," +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
"    -fx-background-radius: 5,4,3,5;" +
"    -fx-background-insets: 0,1,2,0;" +
"    -fx-text-fill: white;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
"    -fx-font-family: \"Arial\";" +
"    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
"    -fx-font-size: 12px;" +
"    -fx-padding: 10 20 10 20;";
 
            
      public static String BOTAOHOVERCSS="-fx-background-color: " +
"        #090a0c" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
"        linear-gradient(#20262b, #191d22)," +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
"    -fx-background-radius: 5,4,3,5;" +
"    -fx-background-insets: 0,1,2,0;" +
"    -fx-text-fill: white;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,100,10) , 5, 0.0 , 2 , 3 );" +
"    -fx-font-family: \"Arial\";" +
"    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
"    -fx-font-size: 12px;" +
"    -fx-padding: 10 20 10 20;";
    

}
