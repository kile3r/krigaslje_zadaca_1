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

/**
 *
 * @author kile
 */
public class DataImporter {

    Parametri param = Parametri.getInstance();

    public DataImporter() {
        param.readParameterFile(EZO.parameterFileName);
    }

    public List<Vozilo> getVozila() {
        ReaderFactory rf = new ReaderFactory("vozila");
        FileReader fr = rf.fileReader();

        List<String[]> zapisVozila = fr.getRecords();
        List<Vozilo> listaVozila = new ArrayList<>();

        Vozilo vozilo;

        for (String[] zapis : zapisVozila) {

            vozilo = new Vozilo.VoziloBuilder(zapis[0], Integer.valueOf(zapis[1]), Integer.valueOf(zapis[2]), Integer.valueOf(zapis[3]), 0, 0, 0)
                    .setListaVozaca(dodajVozace(zapis[4]))
                    .build();
            //listaVozila.add(new Vozilo(zapis)); ovo otkomentirat ako builder nebu dobar HA! dobar je
            listaVozila.add(vozilo);
        }

        return listaVozila;

    }

    public List<Spremnik> getSpremnici() {

        ReaderFactory rf = new ReaderFactory("spremnici");
        FileReader fr = rf.fileReader();

        List<String[]> zapisSpremnika = fr.getRecords();
        List<Spremnik> listaSpremnika = new ArrayList<>();

        for (String[] zapis : zapisSpremnika) {
            listaSpremnika.add(new Spremnik(zapis));
        }
        return listaSpremnika;
    }

    public List<Ulica> getListaUlica() {

        ReaderFactory rfu = new ReaderFactory("ulice");
        FileReader fru = rfu.fileReader();
        List<String[]> zapisiUlica = fru.getRecords();

        List<Ulica> listaUlica = new ArrayList<>();

        for (String[] zapis : zapisiUlica) {
            listaUlica.add(new Ulica(zapis));
        }

        return listaUlica;
    }

    private List<Vozac> dodajVozace(String zapis) {

        List<Vozac> listaVozaca = new ArrayList<>();
        String[] vozaci = null;
        vozaci = zapis.split(",");
        for (String s : vozaci) {
            Vozac v = new Vozac(s);
            listaVozaca.add(v);
        }
        return listaVozaca;
    }

}
