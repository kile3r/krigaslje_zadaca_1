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
public class Vozilo {

    //String id;
    String naziv;
    int tip;
    int vrsta;
    float nosivost;
    int status;  // 0-pripremi 1-isprazni
    float trenutnaKolicina;
    int ciklusiOdvoza;
    public List<Vozac> listaVozaca;

    String vrstaString;

    public int brojPosjecenihUlica;
    public int brojKontejnera;
    public float ukupnaKolicinaOtpada;
    public int brojMjesta;
    public int brojOdlazakaNaZbrinjavanje;

    List<Ulica> listaUlicaVozila;

    public Vozilo(String[] zapisi) {
        //id = zapisi[0];
        naziv = zapisi[0].trim();
        tip = Integer.parseInt(zapisi[1]);
        vrsta = Integer.parseInt(zapisi[2]);
        nosivost = Float.parseFloat(zapisi[3]);
        listaVozaca = dodajVozace(zapisi[4]);
        status = 0;
        trenutnaKolicina = 0;
        ciklusiOdvoza = 0;
        vrstaString = mapirajVrstu(vrsta);

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

    private static String mapirajVrstu(int vrsta) {
        if (vrsta == 0) {
            return "staklo";
        }
        if (vrsta == 1) {
            return "papir";
        }
        if (vrsta == 2) {
            return "metal";
        }
        if (vrsta == 3) {
            return "bio";
        }
        if (vrsta == 4) {
            return "mješano";
        } else {
            return "";
        }
    }

    void iprazniKamion() {
        Ispis.getInstance().ispisiDetalje("Vozilo " + naziv + " odvozi otpad na odlagalište!");
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }

    public float getNosivost() {
        return nosivost;
    }

    public void setNosivost(float nosivost) {
        this.nosivost = nosivost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Vozac> getListaVozaca() {
        return listaVozaca;
    }

    public void setListaVozaca(List<Vozac> listaVozaca) {
        this.listaVozaca = listaVozaca;
    }

    public float getTrenutnaKolicina() {
        return trenutnaKolicina;
    }

    public void setTrenutnaKolicina(float trenutnaKolicina) {
        this.trenutnaKolicina = trenutnaKolicina;
    }

    public int getCiklusiOdvoza() {
        return ciklusiOdvoza;
    }

    public void setCiklusiOdvoza(int ciklusiOdvoza) {
        this.ciklusiOdvoza = ciklusiOdvoza;
    }

    public String getVrstaString() {
        return vrstaString;
    }

    public void setVrstaString(String vrstaString) {
        this.vrstaString = vrstaString;
    }

    public List<Ulica> getListaUlicaVozila() {
        return listaUlicaVozila;
    }

    public void setListaUlicaVozila(List<Ulica> listaUlicaVozila) {
        this.listaUlicaVozila = listaUlicaVozila;
    }

    public Vozilo(VoziloBuilder builder) {
        //this.id = builder.id;
        this.naziv = builder.naziv;
        this.tip = builder.tip;
        this.vrsta = builder.vrsta;
        this.nosivost = builder.nosivost;
        this.status = builder.status;//0
        this.trenutnaKolicina = builder.trenutnaKolicina;//0
        this.ciklusiOdvoza = builder.ciklusiOdvoza;//0
        this.listaVozaca = builder.listaVozaca;
        this.vrstaString = builder.vrstaString;
    }

    public static class VoziloBuilder {

        //private String id;
        private String naziv;
        private int tip;
        private int vrsta;
        private float nosivost;
        private int status;  // 0-pripremi 1-isprazni
        private float trenutnaKolicina;
        private int ciklusiOdvoza;
        private String vrstaString = Vozilo.mapirajVrstu(vrsta);

        private List<Vozac> listaVozaca;

        public VoziloBuilder(String naziv, int tip, int vrsta, int nosivost, int status, float trenutnaKolicina, int ciklusiOdvoza) {
            //this.id = id;
            this.naziv = naziv;
            this.tip = tip;
            this.vrsta = vrsta;
            this.nosivost = nosivost;
            this.status = status;
            this.trenutnaKolicina = trenutnaKolicina;
            this.ciklusiOdvoza = ciklusiOdvoza;
        }

        public VoziloBuilder setListaVozaca(List<Vozac> listaVozaca) {
            this.listaVozaca = listaVozaca;
            return this;
        }

        public Vozilo build() {
            return new Vozilo(this);
        }

    }

}
