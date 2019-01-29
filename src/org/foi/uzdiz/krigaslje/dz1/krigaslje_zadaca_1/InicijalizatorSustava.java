/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz1.singleton.Ispis;

/**
 *
 * @author kile
 */
public class InicijalizatorSustava {

    DataImporter di;

    public InicijalizatorSustava(DataImporter di) {
        this.di = di;
    }

    public List<Ulica> inicijalizcijaKorisnikaSpremnikaUlicama(List<Ulica> listaUlica) {
        for (Ulica ulica : listaUlica) {
            Ispis.getInstance().ispisiDetalje("Ulica " + ulica.naziv);
            ulica.odrediKorisnike();
            ulica.inicijalizirajKorisnike();
            ulica.dodajKorisnikuSpremnike(di.getSpremnici());

            for (Korisnik k : ulica.stanovnici) {

                k.generirajOtpad();
                Ispis.getInstance().ispisiDetalje("Korisnik [" + k.id + "] tip [" + k.tip + "]"
                        + "\nbio " + k.otpadBio
                        + " metala " + k.otpadMetal
                        + " mjesanog " + k.otpadMjesano
                        + " papira " + k.otpadPapir
                        + " staklo " + k.otpadStaklo
                );
                izracunajUkupniOtpadUulici(ulica, k.otpadMetal, k.otpadPapir, k.otpadStaklo, k.otpadBio, k.otpadMjesano);
                k.baciOtpad();
            }

            Ispis.getInstance().ispisiDetalje("Ukupno u ulici " + ulica.naziv + " ima " + ulica.otpadMetal + " metala "
                    + ulica.otpadStaklo + " stakla"
                    + ulica.otpadPapir + " papira"
                    + ulica.otpadBio + " bio otpada"
                    + ulica.otpadMjesano + "mjesanog otpada"
            );

        }

        ispisiSpremnike(listaUlica);

        return listaUlica;

    }

    public List<Vozilo> inicijalizacijaVozaca(List<Vozilo> listaVozila) {
        List<Vozac> listaVozaca = new ArrayList<>();
        Vozac vozac;
        for (Vozilo vozilo : listaVozila) {
            Ispis.getInstance().ispisiDetalje("\nVozilo [" + vozilo.naziv + "]-[" + vozilo.vrsta + "]");
            if (vozilo.listaVozaca != null && !vozilo.listaVozaca.isEmpty()) {
                for (Vozac v : vozilo.getListaVozaca()) {
                    Ispis.getInstance().ispisiDetalje("Vozac " + v.ime.trim());
                    vozac = new Vozac(v.getIme(), 0);
                    listaVozaca.add(vozac);
                }

            }

        }

        return listaVozila;
    }

    private void izracunajUkupniOtpadUulici(Ulica ulica, float otpadMetal, float otpadPapir, float otpadStaklo, float otpadBio, float otpadMjesano) {
        ulica.otpadMetal += otpadMetal;
        ulica.otpadPapir += otpadPapir;
        ulica.otpadStaklo += otpadStaklo;
        ulica.otpadBio += otpadBio;
        ulica.otpadMjesano += otpadMjesano;
    }

    private void ispisiSpremnike(List<Ulica> listaUlica) {
        for (Ulica u : listaUlica) {
            for (Spremnik s : u.spremnici) {
                Ispis.getInstance().ispisiDetalje("Spremnik [" + s.id + "] ima : " + s.kolicinaOtpada);
            }

        }
    }

}
