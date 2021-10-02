/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.data.Ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.data.util.EnumResource;

/**
 *
 * @author treys
 */
public class Cargo implements Serializable {

    private static final long serialVersionUID = 4L;

    private final int maxLevel;
    private final Map<Integer, Integer> mapLvlMaxCapacity;
    private int level;
    private int blackResource, redResource, greenResource, blueResource;
    private boolean upgraded;

    public Cargo(int maxLvl) {
        maxLevel = maxLvl;
        level = 0;
        blackResource = redResource = greenResource = blueResource = 6;
        upgraded = false;

        //create a hashmap with the max capacity for each level
        mapLvlMaxCapacity = new HashMap<>();
        for (int i = 0; i < maxLevel; i++) {
            mapLvlMaxCapacity.put(i, (i * 6 + 6));
        }
    }
    
    private int getMax() {
        return mapLvlMaxCapacity.get(level);
    }

    
    
    public void saveResource(EnumResource r, int unit) {

        switch (r) {
            case BLACK:
                if (blackResource + unit >= getMax()) {
                    blackResource = getMax();
                } else {
                    blackResource += unit;
                }
                break;
            case RED:
                if (redResource + unit >= getMax()) {
                    redResource = getMax();
                } else {
                    redResource += unit;
                }
                break;
            case GREEN:
                if (greenResource + unit >= getMax()) {
                    greenResource = getMax();
                } else {
                    greenResource += unit;
                }
                break;
            case BLUE:
                if (blueResource + unit >= getMax()) {
                    blueResource = getMax();
                } else {
                    blueResource += unit;
                }
                break;

        }

    }

    public void loseResource(EnumResource r, int unit) {

        switch (r) {
            case BLACK:
                if (blackResource - unit < 0) {
                    blackResource = 0;
                } else {
                    blackResource -= unit;
                }
                break;
            case RED:
                if (redResource - unit < 0) {
                    redResource = 0;
                } else {
                    redResource -= unit;
                }
                break;
            case GREEN:
                if (greenResource - unit < 0) {
                    greenResource = 0;
                } else {
                    greenResource -= unit;
                }
                break;
            case BLUE:
                if (blueResource - unit < 0) {
                    blueResource = 0;
                } else {
                    blueResource -= unit;
                }
                break;
            default:
                break;
        }

    }

    private boolean hasResource(EnumResource r) {
        switch (r) {
            case BLACK:
                return blackResource > 0;
            case RED:
                return redResource > 0;
            case GREEN:
                return greenResource > 0;
            case BLUE:
                return blueResource > 0;
            default:
                break;
        }
        return false;
    }

    private boolean hasSpace(EnumResource r) {
        switch (r) {
            case BLACK:
                return blackResource < mapLvlMaxCapacity.get(level);
            case RED:
                return redResource < mapLvlMaxCapacity.get(level);
            case GREEN:
                return greenResource < mapLvlMaxCapacity.get(level);
            case BLUE:
                return blueResource < mapLvlMaxCapacity.get(level);
            default:
                break;
        }
        return false;
    }

    public boolean changeForResource(EnumResource a, EnumResource b) {
        if (hasResource(a) && hasSpace(b)) {
            loseResource(a, 1);
            saveResource(b, 1);
            return true;
        }
        return false;

    }

    public boolean produceShield() {
        if (blackResource <= 0 || greenResource <= 0 || blueResource <= 0) {
            return false;
        }
        blackResource--;
        greenResource--;
        blueResource--;
        return true;
    }

    public boolean produceAmmo() {
        if (blackResource <= 0 || blueResource <= 0) {
            return false;
        }
        blackResource--;
        blueResource--;
        return true;
    }

    public boolean produceFuel() {
        if (blackResource <= 0 || greenResource <= 0 || redResource <= 0) {
            return false;
        }
        blackResource--;
        greenResource--;
        redResource--;
        return true;
    }
    
    public void resetUpgrade() {
        upgraded = false;
    }

    public boolean upgrade() {

        if (!upgraded && level < maxLevel && blackResource >= 3 
                && redResource >= 3 && greenResource >= 3 && blueResource >= 3) {
            level++;
            upgraded = true;
            blackResource -= 3;
            redResource -= 3;
            greenResource -= 3;
            blueResource -= 3;
            return true;
        }
        return false;
    }

    public boolean canUpgradeWeapon() {
        return (blackResource >= 2 && redResource >= 2 && greenResource >= 2 && blueResource >= 2);
    }


    

   public void upgradeWeapon() {
        blackResource -= 2;
        redResource -= 2;
        greenResource -= 2;
        blueResource -= 2;
    }

    boolean setFullShield() {
        if (blackResource <= 0 || greenResource <= 0 || redResource <= 0 || blueResource <= 0) {
            return false;
        }

        blackResource--;
        redResource--;
        greenResource--;
        blueResource--;
        return true;
    }
    
//returns number of resource
    public int getResource(EnumResource r) {
        switch (r) {
            case RED:
                return redResource;
            case BLUE:
                return blueResource;
            case BLACK:
                return blackResource;
            case GREEN:
                return greenResource;
        }
        return 0;
    }
    
    //get existing resources
    public List<EnumResource> getResources() {
        List<EnumResource> aux = new ArrayList<>();

        if (blackResource > 0) {
            aux.add(EnumResource.BLACK);
        }
        if (greenResource > 0) {
            aux.add(EnumResource.GREEN);
        }
        if (redResource > 0) {
            aux.add(EnumResource.RED);
        }
        if (blueResource > 0) {
            aux.add(EnumResource.BLUE);
        }
        return aux;

    }
    
    public String getInfoResources() {
        StringBuilder aux = new StringBuilder();
        aux.append(" red=").append(redResource).append(" black=").append(blackResource).append(" green=").append(greenResource).append(" blue=").append(blueResource);
        return aux.toString();
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        aux.append("Cargo Level=").append(level).append(" Max capacity for level=").append(getMax()).append(" red=").append(redResource).append(" black=").append(blackResource).append(" green=").append(greenResource).append(" blue=").append(blueResource);
        return aux.toString();
    }

    
}
