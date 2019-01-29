/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.List;
import org.foi.uzdiz.krigaslje.dz1.singleton.Ispis;

/**
 *
 * @author kile
 */
public class Statistika {

    float ukupnoMetalaOdlagaliste = 0;
    float ukupnoStaklaOdlagaliste = 0;
    float ukupnoBioOdlagaliste = 0;
    float ukupnoPapirOdlagaliste = 0;
    float ukupnoMjesanoOdlagaliste = 0;
    List<Vozilo> listaVozila;

    public Statistika(List<Vozilo> listaVozila) {
        this.listaVozila = listaVozila;
    }

    public void dajStatistiku() {

        Ispis.getInstance().ispisiStatistiku("------------------------------STATISTIKA------------------------------");
        Ispis.getInstance().ispisiStatistiku("\n");

        for (Vozilo v : listaVozila) {

            Ispis.getInstance().ispisiStatistiku(
                    "Vozilo " + v.naziv
                    + " \nPosjetilo je  " + v.brojMjesta + " mjesta.\n"
                    + " Pokupilo je smece iz " + v.brojKontejnera + " kontejnera\n"
                    + " Odvezlo je ukupno " + v.ukupnaKolicinaOtpada + " smeca na otpad\n"
                    + " Odlazilo je na mjesto za zbrinjavanje " + v.brojOdlazakaNaZbrinjavanje + " puta."
            );
            Ispis.getInstance().ispisiDetalje(
                    "Vozilo " + v.naziv
                    + " \nPosjetilo je  " + v.brojMjesta + " mjesta.\n"
                    + " Pokupilo je smece iz " + v.brojKontejnera + " kontejnera\n"
                    + " Odvezlo je ukupno " + v.ukupnaKolicinaOtpada + " smeca na otpad\n"
                    + " Odlazilo je na mjesto za zbrinjavanje " + v.brojOdlazakaNaZbrinjavanje + " puta."
            );

            switch (v.vrsta) {
                case 0:
                    ukupnoStaklaOdlagaliste += v.ukupnaKolicinaOtpada;
                    break;
                case 1:
                    ukupnoPapirOdlagaliste += v.ukupnaKolicinaOtpada;
                    break;
                case 2:
                    ukupnoMetalaOdlagaliste += v.ukupnaKolicinaOtpada;
                    break;
                case 3:
                    ukupnoBioOdlagaliste += v.ukupnaKolicinaOtpada;
                    break;
                case 4:
                    ukupnoMjesanoOdlagaliste += v.ukupnaKolicinaOtpada;
                    break;
                default:
                    break;
            }

        }//zatvara for

        Ispis.getInstance().ispisiStatistiku("\n");
        Ispis.getInstance().ispisiStatistiku("Na odlagaliste je ukupno odvezeno\n"
                + ukupnoStaklaOdlagaliste + "kg stakla \n"
                + ukupnoPapirOdlagaliste + "kg papira \n"
                + ukupnoMetalaOdlagaliste + "kg metala \n"
                + ukupnoBioOdlagaliste + "kg bio-otpada \n"
                + ukupnoMjesanoOdlagaliste + "kg mjesanog otpada"
        );

        Ispis.getInstance().ispisiDetalje("\n");
        Ispis.getInstance().ispisiDetalje("Na odlagaliste je ukupno odvezeno\n"
                + ukupnoStaklaOdlagaliste + "kg stakla \n"
                + ukupnoPapirOdlagaliste + "kg papira \n"
                + ukupnoMetalaOdlagaliste + "kg metala \n"
                + ukupnoBioOdlagaliste + "kg bio-otpada \n"
                + ukupnoMjesanoOdlagaliste + "kg mjesanog otpada"
        );

        Ispis.getInstance().uvjetovaniIspis();
    }

}
