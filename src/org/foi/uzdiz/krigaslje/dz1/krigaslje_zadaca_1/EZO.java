/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.List;

/**
 *
 * @author kile
 */
public class EZO {

    public static String parameterFileName;

    public static void main(String[] args) {

        try {

            if (args.length != 1) {
                System.out.println("NeDobar broj argumenata..pokušajte ponovo");
                return;
            } else {

                parameterFileName = args[0];

                DataImporter di = new DataImporter();
                InicijalizatorSustava is = new InicijalizatorSustava(di);

                is.inicijalizcijaKorisnikaSpremnikaUlicama(di.getListaUlica());
                
                for(Vozilo v : di.getVozila()){
                    System.out.println("Vozilo " + v.naziv + " " + v.vrsta);
                }
                
                is.inicijalizacijaVozaca(di.getVozila());
                


                //tudu - ovdje sad sve složit da se inicijalizira
                // TODO - dodat statistiku (pozbrajat sve), onaj algoritam prolaska kroz ulice(da ih bude 2) s rndGeneratorom i buildera
            }

        } catch (Exception e) {
            System.err.println("Došlo je do pogreške u pokretanju programa...");
            return;

        }

        
        //
        //            List<Vozilo> listaAktivnihVozila = new ArrayList<>();
        //            for (int i = 0; i < vozila.size(); i++) {
        //                Vozilo v = vozila.get(RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, vozila.size()));
        //                while (listaAktivnihVozila.contains(v)) {
        //                    v = vozila.get(RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, vozila.size()));
        //                }
        //                listaAktivnihVozila.add(v);
        //            }
        //            while (imaOtpada(ulice)) {
        //                //preuzmi otpad
        //                for (Vozilo v : listaAktivnihVozila) {
        //                    v.preuzmiOtpad(ulice);
        //                }
        //            }
        //            for (Vozilo v : listaAktivnihVozila) {
        //                if (v.trenutnaKolicina > 0) {
        //                    v.iprazniKamion();
        //                }
        //            }
        //        } else {
        //            System.out.println("Krivi broj parametara. Potreno je navesti datoteku parametri.txt");
        //        }
    }

    private static boolean imaOtpada(List<Ulica> ulice) {
        for (Ulica u : ulice) {
            for (Spremnik s : u.spremnici) {
                if (s.kolicinaOtpada > 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
