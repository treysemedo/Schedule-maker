/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.resource;

import java.io.InputStream;

/**
 *
 * @author treys
 */
public class ResourceLoader {
    public static InputStream getMyResourceFile(String nomeF){
        InputStream in=ResourceLoader.class.getResourceAsStream(nomeF);
        return in;
    }
}
