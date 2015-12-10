import java.io.PrintWriter;
import java.util.Scanner;
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
public class Arreglo {
    
    private String texto="";
    private boolean ignorarHead = false;
    private boolean ignorarScript = false;
    private boolean ignorar = false;
    
    public Arreglo(){
        
    }
    
    public void modificarArchivo(File archivo){
       texto="";
       String linea = "";
       String[] temp;
       ignorarHead = false;
       ignorarScript = false;
       ignorar = false;
       try{
          Scanner entrada = new Scanner(archivo);
          do{
             linea = entrada.nextLine();
             temp = linea.split(" ");
             for(int i = 0; i < temp.length; ++i){
                 reglas(temp[i]);
             }
          }while(entrada.hasNextLine());
          entrada.close();
          
       }catch(Exception e){}
    }
    
    private void reglas(String palabra){
        String temp = palabra + " ";
        //ignorar
        if(!ignorarHead && !ignorarScript && !ignorar && palabra.contains("<head")){
            ignorarHead = true;
        }
        if(!ignorarHead && !ignorarScript && !ignorar && palabra.contains("<script")){
            ignorarScript = true;
        }
        if(!ignorarHead && !ignorarScript && !ignorar && palabra.contains("<")){
            ignorar = true;
        }
        if(ignorarHead || ignorarScript || ignorar){
            temp = "";
        }
        texto += temp;
        if(texto.contains("&")){
            String[] temp1 = texto.split("&",2);
            if(temp1[1].contains(";")){
                String[] temp2 = temp1[1].split(";", 2);
                texto = texto.replaceAll("&"+temp2[0]+";", "");
            }
        }
        //termina de ignorar
        if(ignorarHead && palabra.contains("</head")){
            ignorarHead = false;
        }
        if(ignorarScript && palabra.contains("</script")){
            ignorarScript = false;
        }
        if(ignorar && palabra.contains(">")){
            ignorar = false;
        }
        String[] rev = palabra.split("</script",2);
        if(rev.length > 1 && rev[1].contains("<script")){
            reglas(rev[1]);
        }
        rev = palabra.split(">",2);
        if(!ignorarHead && !ignorarScript && !ignorar && rev.length > 1 && rev[1].contains("<")){
            String[] temp1 = rev[1].split("<",2);
            texto += temp1[0] + " ";
            reglas(rev[1]);
        }
    }
    
    public void escribirArchivo(File archivo){
       try{
          PrintWriter escritura = new PrintWriter(archivo);
          texto = texto.replace(". ", ".\n ");
          escritura.println(texto);
          escritura.close();
       }catch(Exception e){}
    }
}
