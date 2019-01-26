/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kile
 */
public class InicijalizatorSustava {
    
    DataImporter di;

    public InicijalizatorSustava(DataImporter di) {
        this.di = di;
    }
    
    
    public void inicijalizcijaKorisnikaSpremnikaUlicama(List<Ulica> listaUlica) {

        for (Ulica ulica : listaUlica) {
            System.out.println("Ulica " + ulica.naziv);
            ulica.odrediKorisnike();
            ulica.inicijalizirajKorisnike();
            ulica.dodajKorisnikuSpremnike(di.getSpremnici());
            
            for (Korisnik k : ulica.stanovnici) {
                
                k.generirajOtpad();
                System.out.println("Korisnik [" + k.id + "] tip [" + k.tip + "] \n" +
                        "\tbio " + k.otpadBio+
                        "\tmetala " + k.otpadMetal+
                        "\tmjesanog " + k.otpadMjesano+
                        "\tpapira " + k.otpadPapir+
                        "\tstaklo " + k.otpadStaklo
                        );
                izracunajUkupniOtpadUulici(ulica, k.otpadMetal, k.otpadPapir , k.otpadStaklo, k.otpadBio, k.otpadMjesano);
                k.baciOtpad();
            }
            
            System.out.println("Ukupno u ulici ima " + ulica.otpadMetal + " metala");
            //todo - ostali otpad zbrojit
        }
        
        ispisiSpremnike(listaUlica);
    }

    public void inicijalizacijaVozaca(List<Vozilo> listaVozila) {
        List<Vozac> listaVozaca = new ArrayList<>();
        Vozac vozac;
        for (Vozilo vozilo : listaVozila) {
            System.out.println("\nVozilo [" + vozilo.naziv + "]-["+ vozilo.vrsta+ "]");
            if (vozilo.listaVozaca != null && !vozilo.listaVozaca.isEmpty()) {
                for (Vozac v : vozilo.getListaVozaca()) {
                    System.out.println("Vozac " + v.ime.trim());
                    vozac = new Vozac(v.getIme(), 0);
                    listaVozaca.add(vozac);
                }

            }

        }
        //return listaVozaca;
    }

    private void izracunajUkupniOtpadUulici(Ulica ulica, float otpadMetal, float otpadPapir, float otpadStaklo, float otpadBio, float otpadMjesano) {
                ulica.otpadMetal += otpadMetal;
                ulica.otpadPapir += otpadPapir;
                ulica.otpadStaklo += otpadStaklo;
                ulica.otpadBio += otpadBio;
                ulica.otpadMjesano += otpadMjesano;
    }

    private void ispisiSpremnike(List<Ulica> listaUlica) {
        for(Ulica u :listaUlica ){
            for(Spremnik s : u.spremnici)
            {
                System.out.println("Spremnik ["+s.id+"] ima sad: " + s.kolicinaOtpada);
            }
        
        }
    }
    
    
}
