/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.singleton;
import java.util.Random;

/**
 *
 * @author kile
 */
public class RndGenerator {
    private static RndGenerator instance;
    static Random rand;
    
    private RndGenerator(){
    }
    public static RndGenerator getRndGeneratorInstance(){
        if(instance == null){
            instance = new RndGenerator();
            int sg = Parametri.getSjemeGeneratora();
            rand = new Random(sg);
        }
        return instance;
    }
    public int dajSlucajniBroj(int odBroja, int doBroja){
        int randomNum = rand.nextInt(doBroja - odBroja) + odBroja;
        return randomNum;            
    }
    public long dajSlucajniBroj(long odBroja, long doBroja){
        long randomNum = odBroja+((long)(rand.nextDouble()*(doBroja-odBroja)));
        return randomNum;
    }
    public float dajSlucajniBrojsDecimalama(float odBroja, float doBroja){
        int brojDecimala = Parametri.getBrojDecimala();
        float randomNum = rand.nextFloat() * (doBroja - odBroja) + odBroja;
        int scale = (int) Math.pow(10, brojDecimala);
        return (float) (Math.round(randomNum*scale)/scale);
        
        //(double)Math.round(value * 100000d) / 100000d
        //int num = (int)(randomNum*100);
        //return (float)(num/100.0);
    }
    
}
//public static double roundAvoid(double value, int places) {
//    double scale = Math.pow(10, places);
//    return Math.round(value * scale) / scale;
//}