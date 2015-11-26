package normalizartokens;


import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JN1
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File temp = new File("");
        String ruta = temp.getAbsolutePath().replace("\\", "/");
        String[] temp1 = ruta.split("/");
        ruta = "";
        for(int i = 0; i < temp1.length-1; ++i){
            ruta += temp1[i] + "/";
        }
        Normalizar normalizar = new Normalizar(ruta);
        File[] archivos = new File(ruta+"dirTokens").listFiles();
        for(int i = 0; i < archivos.length; ++i){
            normalizar.modificarArchivo(archivos[i]);
            normalizar.escribirArchivo(archivos[i]);
        }
    }
    
}
