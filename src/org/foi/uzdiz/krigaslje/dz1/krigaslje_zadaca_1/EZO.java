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

                List<Ulica> listaUlica = is.inicijalizcijaKorisnikaSpremnikaUlicama(di.getListaUlica());

                List<Vozilo> listaVozila = is.inicijalizacijaVozaca(di.getVozila());

                Obrada obrada = new Obrada(listaVozila, listaUlica);
                obrada.obradi();

                Statistika s = new Statistika(listaVozila);
                s.dajStatistiku();

            }

        } catch (Exception e) {
            System.err.println("Došlo je do pogreške u pokretanju programa...");
            return;

        }
    }

}
