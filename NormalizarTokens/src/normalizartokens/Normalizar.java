/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normalizartokens;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author JN1
 */
public class Normalizar {
    private String texto="";
    private String[] stopwords;
    
    public Normalizar(String ruta){
        File archivo = new File(ruta+"stopwords.txt");
        String linea = "";
        String temp = "";
        try{
          Scanner entrada = new Scanner(archivo);
          do{
             linea = entrada.nextLine();
             temp += linea + " ";
          }while(entrada.hasNextLine());
          entrada.close();
        }catch(Exception e){}
        stopwords = temp.split(" ");
    }
    
    public void modificarArchivo(File archivo){
       texto="";
       String linea = "";
       String[] temp;
       try{
          Scanner entrada = new Scanner(archivo);
          do{
             linea = entrada.nextLine();
             quitarToken(linea);
          }while(entrada.hasNextLine());
          entrada.close();
       }catch(Exception e){}
    }
    
    private void quitarToken(String palabra){
        String temp = palabra+"\n";
        //quitar stopwords
        for(int i = 0; i < stopwords.length; ++i){
            if(palabra.contentEquals(stopwords[i])){
                temp = "";
            }
        }
        //quitar tokens con caracteres que no se van a indexar 
        for(int i = 0; i < temp.length()-1; ++i){
            if(!(temp.charAt(i)-'a' >= 0 && temp.charAt(i)-'a' < 26)){
                temp = "";
            }
        }
        texto += temp;
    }
    
    public void escribirArchivo(File archivo){
       try{
          PrintWriter escritura = new PrintWriter(archivo);
          escritura.print(texto);
          escritura.close();
       }catch(Exception e){}
    }
}
