/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import logic.Game;
import logic.data.planet.EnumPlanet;
import logic.data.util.EnumAlien;
import logic.data.util.EnumCell;
import logic.data.util.EnumEvent;
import logic.data.util.EnumMsg;
import logic.data.util.EnumOfficer;
import logic.data.util.EnumResource;
import logic.states.util.EnumState;
import static ui.gui.util.Propriedade.ALIEN_ATACK;
import static ui.gui.util.Propriedade.ALTERA_AMMO;
import static ui.gui.util.Propriedade.ALTERA_CREW;
import static ui.gui.util.Propriedade.ALTERA_FUEL;
import static ui.gui.util.Propriedade.ALTERA_MAPA;
import static ui.gui.util.Propriedade.ALTERA_RESOURCE;
import static ui.gui.util.Propriedade.ALTERA_SHIELD;
import static ui.gui.util.Propriedade.CHANGE_LOG;
import static ui.gui.util.Propriedade.EVENTO_ALEATORIO;
import static ui.gui.util.Propriedade.CHANGE_STATE;

/**
 *
 * @author treys
 */
public class GameObservable {

    private Game game;
    private final PropertyChangeSupport propertyChangeSuport;

    public GameObservable(Game jogo) {
        this.game = jogo;
        propertyChangeSuport = new PropertyChangeSupport(jogo);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSuport.addPropertyChangeListener(propertyName, listener);
    }

    //state changes
    public void SelectShip(int n) {
        game.SelectShip(n);
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
    }

    public void spaceTravel() {
        game.spaceTravel();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
    }

    public void convertResources() {
        game.convertResources();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    public void goBack() {
        game.goBack();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    public void visitStation() {
        game.visitStation();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    public void undock() {
        game.undock();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    public void landOnPlanet() {
        game.landOnPlanet();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    public void DroneMove(int i, int j) {
        game.DroneMove(i, j);
        propertyChangeSuport.firePropertyChange(ALTERA_MAPA, null, null);

        if (game.getMsg() == EnumMsg.ATACK) {
            propertyChangeSuport.firePropertyChange(ALIEN_ATACK, null, null);
            propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
            propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
            
        } else if (game.getMsg() == EnumMsg.BACKTOSHIP) {
            propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
            propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        }

    }

    public void randomEvent() {
        game.randomEvent();
        propertyChangeSuport.firePropertyChange(EVENTO_ALEATORIO, null, null);
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
    }

    public void selectEvent(int n) {
        game.selectEvent(n);
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
    }

    public void newGame() {
        game.newGame();
        propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
    }

    //operations
    public void hireCrewMember() {
        game.hireCrewMember();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_CREW, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
        
    }

    public void produceShield() {
        game.produceShield();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_SHIELD, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void produceAmmo() {
        game.produceAmmo();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_AMMO, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void produceFuel() {
        game.produceFuel();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_FUEL, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void upgradeCargo() {
        game.upgradeCargo();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void changeForResource(EnumResource a, EnumResource b) {
        game.changeForResource(a, b);
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }

    }

    public void upgradeWeapon() {
        game.upgradeWeapon();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_AMMO, null, null);
        }
    }

    public void setFullShield() {
        game.setFullShield();
        propertyChangeSuport.firePropertyChange(CHANGE_LOG, null, null);
        if (game.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_SHIELD, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    //string with infos
    public String getInfoShip() {
        return game.getInfoShip();
    }

    public String getInfoPlanet() {
        return game.getInfoPlanet();
    }

    public String getInfoResources() {
        return game.getInfoResources();
    }

    public EnumCell[][] getInfoTerrain() {
        return game.getInfoTerrain();
    }

    public List<EnumOfficer> getCrew() {
        return game.getCrew();
    }

    //interface options
    public boolean planetHasStation() {
        return game.planetHasStation();
    }

    public boolean terrainHasArtfact() {
        return game.terrainHasArtfact();
    }

    public EnumResource terrainResource() {
        return game.terrainResource();
    }

    public String listCrew() {
        return game.listCrew();

    }

    //get infoEnums
    public EnumState getState() {
        return game.getState();
    }

    public EnumEvent getEvent() {
        return game.getEvent();
    }

    public int[] getFuel() {
        return game.getFuel();
    }

    public int[] geShield() {
        return game.geShield();
    }

    public int[] getWeapon() {
        return game.getWeapon();
    }

    public int getResource(EnumResource r) {
        return game.getResource(r);
    }

    public int getArtfact() {
        return game.getArtfact();
    }

    public EnumPlanet getPlanetType() {
        return game.getPlanetType();
    }

    public List<EnumResource> getResources() {
        return game.getResources();
    }

    public boolean planetHasArtfact() {
        return game.planetHasArtfact();
    }

    public int getDroneHp() {
        return game.getDroneHp();
    }
    public int getTimerAlien() {
        return game.getTimerAlien();
    }

    public EnumAlien getAlienType() {
        return game.getAlienType();
    }

    public EnumMsg getMsg() {
        return game.getMsg();
    }

    public boolean lostCrew() {
        return game.lostCrew();
    }

    public boolean won() {
        return game.won();
    }
    
    public String getLog() {
        return game.getLog();
    }

    public boolean saveGame(String nomeFich) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(nomeFich));
            out.writeObject(game);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    return false;
                }
            }

        }
    }

    public boolean loadGame(File inF) {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream(inF));
            game = (Game) in.readObject();
            propertyChangeSuport.firePropertyChange(CHANGE_STATE, null, null);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
    }
    
    

}
