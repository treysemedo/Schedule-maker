/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.data.Ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import logic.data.util.EnumOfficer;
import logic.data.util.EnumResource;
import logic.data.util.EnumSector;
import logic.data.util.Utility;

/**
 *
 * @author treys
 */
public abstract class Ship implements Serializable{
    private static final long serialVersionUID = 4L;

    private int fuel, maxFuel, shield, maxShield, weapon, maxWeapon;
    private int numArtifats;
    private List<EnumOfficer> crew;
    private Cargo cargoHolder;
    private EnumSector currentSector;
    private Utility util;

    public Ship(int fuel, int shield, int weapon, int cargoLvl) {
       
        numArtifats = 0;
        currentSector = EnumSector.FEDERATION;
        util = new Utility();
        initFuel(fuel);
        initShield(shield);
        initWeapon(weapon);
        initCrew();
        initCargo(cargoLvl);
    }
    
    
     // Define initial values for the class atributes
    private void initShield(int capacity) {
        shield = capacity;
        maxShield = capacity;
    }

    private void initWeapon(int firePower) {
        weapon = firePower;
        maxWeapon = firePower;

    }

    private void initFuel(int capacity) {
        fuel = capacity;
        maxFuel = capacity;
    }
    
   private void initCrew(){
        crew = new ArrayList<>();
        while(addOfficer()){}    
    }
    
   public final void initCargo(int lvl) {
        cargoHolder = new Cargo(lvl);
    }
   
   // add Officer if crew not full
    public boolean addOfficer(){
        if(crew.size()<EnumOfficer.values().length){
            crew.add(EnumOfficer.values()[crew.size()]);
            return true;
        }
        return false;
    }
    // remove Officer if crew not empty
    public void removeOfficer(){
        if(crew.size()>0){
            crew.remove(crew.size()-1);
        }
    }
 
    // verify if Officer Exists
    public boolean existsOfficer(EnumOfficer of) {
        return crew.contains(of);
    }

    //hire new crew member if all the conditions are met
    public boolean hireCrewMember() {
        List<EnumResource> aux = cargoHolder.getResources();
        if (aux.size() == 4) {
            if (addOfficer()) {
                for (EnumResource r : aux) {
                    cargoHolder.loseResource(r, 1);
                }
                return true;
            }
        }
        return false;

    }

   

    

    
    public void addResource(EnumResource r, int unit) {
        cargoHolder.saveResource(r, unit);
    }

    public void addRandomResource() {
        addResource(EnumResource.valueOf(util.randomInt(1, 4)), util.randomInt(1, 6));
    }

    public void loseRandomResource() {
        List<EnumResource> aux = getResources();
        if (!aux.isEmpty()) {
            EnumResource type = aux.get(util.randomInt(0, aux.size() - 1));
            loseResource(type, util.randomInt(1, 3));
        }

    }

    public void loseResource(EnumResource r, int unit) {
        cargoHolder.loseResource(r, unit);
    }

    
    //fuel Operations
   public void loseFuel(int num){
        if(fuel-num>0){
            fuel-=num;
        }else{
            fuel=0;
        }    
    }
    
    public void gainFuel(int num){
        if(fuel+num<maxFuel){
            fuel+=num;
        }else{
            fuel=maxFuel;
        }    
    }
    
    //shield Opertations
    public void loseShield(int num){
        if(shield-num>0){
            shield-=num;
        }else{
            shield=0;
        }    
    }
    
    public void gainShield(int num){
        if(shield+num<maxShield){
            fuel+=num;
        }else{
            shield=maxShield;
        }    
    }
    
    
    //weapon Opertations
     public void loseWeapon(int num){
        if(weapon-num>0){
            weapon-=num;
        }else{
            weapon=0;
        }    
    }
    
    public void gainWeapon(int num){
        if(weapon+num<maxWeapon){
            weapon+=num;
        }else{
            weapon=maxWeapon;
        }    
    }
  

   
    private void travelregularly() {
        loseFuel(1);
    }

    private void travelThruBlackH() {
        loseFuel(3);
        loseShield(2);
    }

    public boolean reserveFuel() {
        return fuel <= 3;
    }

    public boolean emptyFuel() {
        return fuel <= 0;
    }

    //tools for resource conversion
    public boolean produceFuel() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && fuel < maxFuel) {

            if (cargoHolder.produceFuel()) {
                fuel++;
                return true;
            }
        }

        return false;
    }

    public boolean produceShield() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && shield < maxShield) {
            if (cargoHolder.produceShield()) {
                shield++;
                return true;
            }
        }

        return false;
    }

    public boolean produceAmmo() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && weapon < maxWeapon) {
            if (cargoHolder.produceAmmo()) {
                weapon++;
                return true;
            }
        }

        return false;
    }
    
    
    
    public boolean upgradeCargo() {
        return existsOfficer(EnumOfficer.CARGOHOLD) && cargoHolder.upgrade();
    }
     public boolean changeForResource(EnumResource a, EnumResource b) {
        return existsOfficer(EnumOfficer.CARGOHOLD) && cargoHolder.changeForResource(a, b);
    }
     
     public void travelTo(EnumSector sector, int mode) {
        currentSector = sector;
        if (mode == 1) {
            travelregularly();
        } else {
            travelThruBlackH();
        }

    }
     public boolean UpgradeWeapon() {
        return false;
    }
     
     
     //setters
     public boolean setFullShield() {
        if (existsOfficer(EnumOfficer.SHIELDS) && shield < maxShield && cargoHolder.setFullShield()) {
            shield = maxShield;
            return true;
        }
        return false;
    }
    public void setMaxWeapon(int maxWeapon) {
        this.maxWeapon = maxWeapon;
    }
     
     
     
     
     //getters
    public int getMaxWeapon() {
        return maxWeapon;
    }

    public List<EnumOfficer> getCrew() {
        return crew;
    }


    public void resetUpgradeCargo() {
        cargoHolder.resetUpgrade();
    }

   
    //verify if 5 atrfacts have been found
    public boolean achievedArtifacts() {
        return numArtifats == 5;
    }

    public Cargo getCargoHolder() {
        return cargoHolder;
    }
     
    public String listCrew() {
        StringBuilder aux = new StringBuilder();
        for (EnumOfficer a : crew) {
            aux.append(a + "\n");
        }
        return aux.toString();
    }

    public boolean hasCrew() {
        return crew.size() > 0;
    }

    //simular acontecimento de eventos
    public List<EnumResource> getResources() {
        return cargoHolder.getResources();
    }

    
    public int getArtfact() {
        return numArtifats;
    }
    //sector aonde nave se encontra
    public EnumSector getSector() {
        return currentSector;
    }

    

    public String getInfoResources() {
        return cargoHolder.getInfoResources();
    }

    public void addArtfact() {
        numArtifats++;
    }

    

    

    public int[] getFuel() {
        int[] aux = new int[2];
        aux[0] = fuel;
        aux[1] = maxFuel;
        return aux;
    }

    public int[] getShield() {
        int[] aux = new int[2];
        aux[0] = shield;
        aux[1] = maxShield;
        return aux;
    }

    public int[] getWeapon() {
        int[] aux = new int[2];
        aux[0] = weapon;
        aux[1] = maxWeapon;
        return aux;
    }

    public int getResource(EnumResource r) {
        return cargoHolder.getResource(r);
    }

    @Override
    public String toString() {
        return "\n\tfuel=" + fuel + " maxFuel=" + maxFuel + " shield=" + shield 
                + " maxShield=" + maxShield + "\n\tweapon=" + weapon 
                + " maxWeapon=" + maxWeapon + " numArtifats=" + numArtifats 
                + "\n\tcrew=" + crew + "\n\t" + cargoHolder 
                + "\n\tcurrentSector=" + currentSector + '}';
    }

}
