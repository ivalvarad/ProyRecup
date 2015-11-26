
import java.io.File;
import java.io.PrintWriter;

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
        Arreglo arreglo = new Arreglo();
        File temp = new File("");
        String texto = "";
        String ruta = temp.getAbsolutePath().replace("\\", "/");
        String[] temp1 = ruta.split("/");
        ruta = "";
        for(int i = 0; i < temp1.length-1; ++i){
            ruta += temp1[i] + "/";
        }
        File[] archivos = new File(ruta+"paginas").listFiles();
        for(int i = 0; i < archivos.length; ++i){
            arreglo.modificarArchivo(archivos[i]);
            arreglo.escribirArchivo(archivos[i]);
            texto += "paginas/" + archivos[i].getName() + " dirTokens/" + archivos[i].getName() + "\n";
        }
        try{
          PrintWriter escritura = new PrintWriter(ruta+"lista.txt");
          escritura.print(texto);
          escritura.close();
       }catch(Exception e){}
    }
    
}
