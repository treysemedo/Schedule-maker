/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.data.planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.data.util.EnumResource;
import logic.data.components.Resource;

/**
 *
 * @author treys
 */
public class GreenPlanet extends Planet{

    public GreenPlanet() {
         super(new ArrayList<EnumResource>(Arrays.asList(EnumResource.GREEN,EnumResource.RED)), false);;
    }
     @Override
    public String toString() {
        return "Green Planet->"+super.toString();
    }
    
    
    @Override
    public EnumPlanet getPlanetType(){
       return EnumPlanet.GREEN;
    }
}
