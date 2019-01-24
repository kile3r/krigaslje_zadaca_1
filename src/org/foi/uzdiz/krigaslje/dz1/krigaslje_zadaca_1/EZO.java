/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz1.factoryMethod.FileReader;
import org.foi.uzdiz.krigaslje.dz1.factoryMethod.ReaderFactory;
import org.foi.uzdiz.krigaslje.dz1.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dz1.singleton.RndGenerator;

/**
 *
 * @author kile
 */
public class EZO {

    /**
     * @param args the command line arguments
     */
    public static String parameterFileName;
    
    public static void main(String[] args) {
        
        // TODO - dodat statistiku, onaj algoritam prolaska kroz ulice s rndGeneratorom i buildera
        if(args.length==1){
            //read parameter file
            Parametri.getInstance().readParameterFile(args[0]);
            //read other files, check records
            ReaderFactory rf = new ReaderFactory("ulice");
            FileReader fr = rf.fileReader();
            List<String[]> listaUlica = fr.getRecords();
            
            
            rf = new ReaderFactory("spremnici");
            fr = rf.fileReader();
            List<String[]> listaSpremnika = fr.getRecords();
            for(String[] zapisSpremnik:listaSpremnika){
                KategorijaSpremnika ks = new KategorijaSpremnika(zapisSpremnik[0],Integer.parseInt(zapisSpremnik[1]),Integer.parseInt(zapisSpremnik[2]),Integer.parseInt(zapisSpremnik[3]),Integer.parseInt(zapisSpremnik[4]),Integer.parseInt(zapisSpremnik[5]));
            }
            
            Parametri.getInstance().readParameterFile(args[0]);
            rf = new ReaderFactory("vozila");
            fr = rf.fileReader();
            List<String[]> listaVozila = fr.getRecords();
            List<Vozilo> vozila = new ArrayList<>();
            for(String[] zapisVozila : listaVozila){
                vozila.add(new Vozilo(zapisVozila[0],Integer.parseInt(zapisVozila[1]),Integer.parseInt(zapisVozila[2]),Integer.parseInt(zapisVozila[3]),zapisVozila[4]));
            }
            //kreiraj ulice
            List<Ulica> ulice = new ArrayList<>();
            for (String[] zapisUlica : listaUlica) {
                ulice.add(new Ulica(zapisUlica[0],Integer.parseInt(zapisUlica[1]),Integer.parseInt(zapisUlica[2]),Integer.parseInt(zapisUlica[3]),Integer.parseInt(zapisUlica[4])));
            }
            //dodaj korisnike
            for (Ulica ulica : ulice) {
                ulica.odrediKorisnike();
                ulica.dodijeliSpremnike();
                //System.out.println(ulica.info());
            }
            for (Ulica ulica : ulice) ulica.generirajOtpadKorisnika();
            for (Ulica ulica : ulice) ulica.odloziOtpad();
            //vozila
            
            List<Vozilo> listaAktivnihVozila = new ArrayList<>();
            for (int i = 0; i < vozila.size(); i++) {
                Vozilo v = vozila.get(RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, vozila.size()));
                while(listaAktivnihVozila.contains(v)) v = vozila.get(RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, vozila.size()));
                listaAktivnihVozila.add(v);
            }
            while(imaOtpada(ulice)){
                //preuzmi otpad
                for(Vozilo v: listaAktivnihVozila){
                    v.preuzmiOtpad(ulice);
                }
            }
            for(Vozilo v: listaAktivnihVozila){
                if(v.trenutnaKolicina>0) v.iprazniKamion();
            }
        }else{
            System.out.println("Krivi broj parametara. Potreno je navesti datoteku parametri.txt");
        }
    }

    private static boolean imaOtpada(List<Ulica> ulice) {
        for(Ulica u:ulice){
            for (Spremnik s: u.spremnici) 
                if(s.kolicinaOtpada>0) return true;
        }
        return false;
    }

}
