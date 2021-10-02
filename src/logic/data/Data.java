/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//under 10
package logic.data;

import java.io.Serializable;
import java.util.ArrayList;
import logic.data.event.Event;
import java.util.List;
import logic.data.event.CargoLoss;
import logic.data.event.CrewDeath;
import logic.data.event.CrewRescue;
import logic.data.event.FuelLoss;
import logic.data.event.NoEvent;
import logic.data.event.SalvageShip;
import logic.data.Ship.Ship;
import logic.data.Ship.Military;
import logic.data.Ship.Mining;
import logic.data.event.AlienAttack;
import logic.data.event.Asteroids;
import logic.data.planet.BlackPlanet;
import logic.data.planet.BluePlanet;
import logic.data.planet.EnumPlanet;
import logic.data.planet.GreenPlanet;
import logic.data.planet.Planet;
import logic.data.planet.RedPlanet;
import logic.data.util.EnumAlien;
import logic.data.util.EnumCell;
import logic.data.util.EnumEvent;
import logic.data.util.EnumExplore;
import logic.data.util.EnumMsg;
import logic.data.util.EnumOfficer;
import logic.data.util.EnumResource;
import logic.data.util.EnumSector;
import logic.data.util.Utility;

/**
 *
 * @author treys
 */
public class Data implements Serializable {

    private static final long serialVersionUID = 4L;

    private Ship ship;
    private Planet planet;
    private Utility util;
    private Event event;
    private EnumMsg msg;
    private boolean win;
    private List<String> log;

    public Data() {
        planet = null;
        util = new Utility();
        win = false;
        log = new ArrayList<>();

    }

    public void addLog(String msg) {
        if (log.size() >= 10) {
            log.remove(9);
        }
        log.add(0, msg);
    }

    public String getLog() {
        StringBuilder aux = new StringBuilder();
        for (String msg : log) {
            aux.append(msg).append("\n");
        }
        return aux.toString();
    }

    public boolean setShip(int n) {

        switch (n) {
            case 1:
                ship = new Military();
                break;
            case 2:
                ship = new Mining();
                break;
            default:
                return false;

        }
        return true;
    }

    public void removeOfficer() {
        ship.removeOfficer();
        addLog("SHIP - Officer Removed");
    }

    public EnumSector travel() {
        int mode = 1;
        if (util.randomInt(1, 8) == 1) {
            mode = 2;
            addLog("TRAVEL - Black Hole");
        } else {
            addLog("TRAVEL - Regular");
        }

        switch (ship.getSector()) {
            case FEDERATION:
                ship.travelTo(EnumSector.PLANET, mode);
                planet = generatePlanet();
                break;
            case EVENT:
                ship.travelTo(EnumSector.PLANET, mode);
                planet = generatePlanet();
                break;
            case PLANET:
                ship.travelTo(EnumSector.EVENT, mode);

        }

        return ship.getSector();

    }

    //gerar planeta e evento
    public Planet generatePlanet() {
        int aux = util.randomInt(1, 4);
        Planet newP = null;
        switch (aux) {
            case 1:
                newP = new BlackPlanet();
                break;
            case 2:
                newP = new RedPlanet();
                break;
            case 3:
                newP = new GreenPlanet();
                break;
            case 4:
                newP = new BluePlanet();
                break;

        }
        return newP;
    }

    public void generateEvent(int aux) {

        Event newE = null;

        switch (aux) {
            case 0:
                newE = new Asteroids(ship);
                addLog("EVENT - Asteroids");
                break;
            case 1:
                newE = new CrewDeath(ship);
                addLog("EVENT - Crew Death");
                break;
            case 2:
                newE = new SalvageShip(ship);
                addLog("EVENT - Salvage Ship");
                break;
            case 3:
                newE = new CargoLoss(ship);
                addLog("EVENT - Cargo Loss");
                break;
            case 4:
                newE = new FuelLoss(ship);
                addLog("EVENT - Fuel Loss");
                break;
            case 5:
                newE = new NoEvent(ship);
                addLog("EVENT - No Event");
                break;
            case 6:
                newE = new CrewRescue(ship);
                addLog("EVENT - Crew Rescue");
                break;
            case 7:
                newE = new AlienAttack(ship);
                addLog("EVENT - Alien Atack");
                break;
        }

        event = newE;
    }

    public final void explorePlanet() {
        planet.explorePlanet(ship.existsOfficer(EnumOfficer.WEAPONS));
    }

    public void hireCrewMember() {
        if (hasStation() && ship.hireCrewMember()) {
            msg = EnumMsg.SUCCESS;
            addLog("SPACE STATION - New Crew Member");
        } else {
            msg = EnumMsg.FAIL;
            addLog("SPACE STATION - Operation FAIL");
        }

    }

    public void changeForResource(EnumResource type1, EnumResource type2) {
        if (hasStation() && ship.changeForResource(type1, type2)) {
            addLog("SPACE STATION - Resources Exchanged");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("SPACE STATION - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    //operacoes cargohold
    public void produceShield() {
        if (ship.produceShield()) {
            addLog("LABORATORY - Shield Produced");
            msg = EnumMsg.SUCCESS;
        } else {
            msg = EnumMsg.FAIL;
            addLog("LABORATORY - Operation FAIL");
        }
    }

    public void produceAmmo() {
        if (ship.produceAmmo()) {
            addLog("LABORATORY - Ammo Produced");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("LABORATORY - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    public void produceFuel() {
        if (ship.produceFuel()) {
            addLog("LABORATORY - Fuel Produced");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("LABORATORY - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    public void upgradeCargo() {
        if (hasStation() && ship.upgradeCargo()) {
            addLog("SPACE STATION - Cargo Upgraded");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("SPACE STATION - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    public void resetUpgradeCargo() {
        ship.resetUpgradeCargo();
    }

    public void upgradeWeapon() {
        if (ship.UpgradeWeapon()) {
            addLog("SPACE STATION - Weapons Upgraded");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("SPACE STATION - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    public void setFullShield() {
        if (ship.setFullShield()) {
            addLog("SPACE STATION - Shields Fixed");
            msg = EnumMsg.SUCCESS;
        } else {
            addLog("SPACE STATION - Operation FAIL");
            msg = EnumMsg.FAIL;
        }
    }

    public EnumExplore DroneMove(int i, int j) {

        EnumExplore aux = planet.DroneMove(i, j);
        switch (aux) {
            case BACKTOSHIP:
                msg = EnumMsg.BACKTOSHIP;
                break;
            case ATACK:
                msg = EnumMsg.ATACK;
                break;
            default:
                msg = EnumMsg.SUCCESS;
                break;
        }
        return aux;

    }

    public void eventHappen(int n) {
        generateEvent(n);
        event.act();
    }

    public void eventHappen() {
        generateEvent(util.randomInt(0, 7));
        event.act();
    }

    public void atack() {
        planet.atack();
    }

    public void resetDrone() {
        planet.resetDrone();
    }

    public boolean explorationEnd() {
        if (planet.loseResource()) {
            int aux=util.randomInt(1, 6);
            addLog("EXPLORATION END - got "+ aux +" "+planet.getExploredResource());
            ship.addResource(planet.getExploredResource(), aux);
        } else {
            addLog("EXPLORATION END - got Artifact");
            ship.addArtfact();
        }

        return ship.achievedArtifacts();
    }

    public void spendFuel() {
        ship.loseFuel(1);
    }

    public boolean hasFuel() {
        return !ship.emptyFuel();
    }

    public void resetDados() {
        planet = null;
        util = new Utility();
        win = false;
        log.clear();
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    //get Info
    public boolean hasLandingParty() {
        return ship.existsOfficer(EnumOfficer.LANDINGPARTY);
    }

    public boolean canExplore() {
        return planet.canExplore();
    }

    public boolean hasCrew() {
        return ship.hasCrew();
    }

    public String getInfoShip() {
        return ship.toString();
    }

    public String getInfoPlanet() {
        if (planet != null) {
            return planet.toString();
        }
        return "Sem planeta";
    }

    public EnumCell[][] getInfoTerrain() {
        return planet.getInfoTerrain();
    }

    public String getInfoResources() {

        return ship.getInfoResources();
    }

    public int getDroneHp() {
        return planet.getDroneHp();
    }

    public EnumAlien getAlienType() {
        return planet.getAlienType();
    }

    public EnumEvent getEvent() {
        return event.getEnumEvent();
    }

    public List<EnumOfficer> getCrew() {
        return ship.getCrew();
    }

    public boolean hasStation() {
        return planet.getSpaceStation();
    }

    public String listCrew() {
        return ship.listCrew();
    }

    public boolean won() {
        return win;
    }

    public int[] getFuel() {
        return ship.getFuel();
    }

    public int[] geShield() {
        return ship.getShield();
    }

    public int[] getWeapon() {
        return ship.getWeapon();
    }

    public int getResource(EnumResource r) {
        return ship.getResource(r);
    }

    public EnumPlanet getPlanetType() {
        return planet.getPlanetType();
    }

    public int getTimerAlien() {
        return planet.getTimerAlien();
    }
    public boolean DroneIsAlive() {
        if(!planet.DroneIsAlive())
            addLog("EXPLORATION END - Drone lost");
        return planet.DroneIsAlive();
    }

    public EnumResource terrainResource() {
        return planet.terrainResource();
    }

    public boolean terrainHasArtfact() {
        return planet.terrainHasArtfact();
    }

    public List<EnumResource> getResources() {
        return planet.getResources();
    }

    public int getArtfact() {
        return ship.getArtfact();
    }

    public boolean planetHasArtfact() {
        return planet.hasArtfact();
    }

    public EnumMsg getMsg() {
        return msg;
    }

}
