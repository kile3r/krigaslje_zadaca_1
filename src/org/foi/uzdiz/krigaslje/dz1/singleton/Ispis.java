/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author kile
 */
public class Ispis {
    static Ispis instance;
    
    String ispisKratki = "";
    String ispisOpsirni = "";
    
    private Ispis(){
        
    }
    public static Ispis getInstance(){
        if(instance == null) instance = new Ispis();
        return instance;
    }
    
     public void ispisiDetalje(String str){
        ispisOpsirni +=  str + "\n";  
        
    }
    
   public void ispisiStatistiku(String str){
       ispisKratki += str + "\n";
   }
   
    public void uvjetovaniIspis(){
        //0 -detaljni ispis
        if(Parametri.getIspis()==0){
            System.out.println(ispisOpsirni);
            writeToFile(ispisOpsirni);
        }else if (Parametri.getIspis()==1){
            System.out.println(ispisKratki);
            writeToFile(ispisKratki);
        }
    }
    private void writeToFile(String str){
        String datotekaIzlaza = Parametri.getDatotekaIzlaz();
        try {
            File file = new File(datotekaIzlaza);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream writer = new FileOutputStream(datotekaIzlaza,false);
            
            writer.write(str.getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
