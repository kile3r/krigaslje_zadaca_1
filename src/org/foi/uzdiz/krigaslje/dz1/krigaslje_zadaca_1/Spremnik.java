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
public class Spremnik {
    int id;
    String naziv;
    int tip;
    int nosivost;
    int naMale;
    int naSrednje;
    int naVelike;
    float kolicinaOtpada;
    public static List<Spremnik> listaSpremnika = new ArrayList<>();
    public List<Korisnik> korisniciSpremnika;
    public Spremnik(String naziv, int tip, int naMale, int naSrednje, int naVelike, int nosivost) {
        this.naziv = naziv;
        this.tip = tip;
        this.nosivost = nosivost;
        this.naMale = naMale;
        this.naSrednje = naSrednje;
        this.naVelike = naVelike;
        korisniciSpremnika = new ArrayList<>();
        kolicinaOtpada = 0;
        dodajID();
        listaSpremnika.add(this);
    }
    private void dodajID(){
        int nid = 1000;
        while(postojiID(nid)){
            nid++;
        }
        this.id = nid;
    }
    private boolean postojiID(int nid){
        for(Spremnik s: listaSpremnika) if(s.id==nid)return true;
        return false;
    }

    boolean mozePrimitKorisnika(int i) {
        if(i==1){
            if(korisniciSpremnika.size()<naMale)return true;
            else return false;
        }
        if(i==2){
            if(korisniciSpremnika.size()<naSrednje)return true;
            else return false;
        }
        if(i==3){
            if(korisniciSpremnika.size()<naVelike)return true;
            else return false;
        }
        return false;
    }

    float dodajOtpad(Korisnik k, float otpad) {
        int kid = k.id;
        if((kolicinaOtpada+otpad)<=nosivost){
            kolicinaOtpada = kolicinaOtpada+otpad;
            Ispis.getInstance().uvjetovaniIspis("Korisnik ("+kid+") je u spremnik " + naziv + " (" + id + ") bacio " + otpad + "kg otpada");
            return otpad;
        }else if(kolicinaOtpada<nosivost){
            float diff = nosivost-kolicinaOtpada;
            kolicinaOtpada = nosivost;
            float preostali = otpad-diff;
            Ispis.getInstance().uvjetovaniIspis("Korisnik ("+kid+") je u spremnik " + naziv + " (" + id + ") bacio " + diff + "kg otpada i napunio kontenjer. Ostalo mu je "+preostali+"kg. ");
            return diff;
        }else{
            Ispis.getInstance().uvjetovaniIspis("Korisnik ("+kid+") ne može baciti otpad jer je spremnik " + naziv + " (" + id + ") puni!");
            return 0;
        }
        
        //todo - provjera dal korisnik ima spremnik za određenu vrstu otpada ako treba
    }
    public Spremnik kloniraj(){
        return new Spremnik(naziv, tip, naMale, naSrednje, naVelike, nosivost);
    }
    String shortInfo() {
        return naziv+" [" + id + "]";
    }

}
