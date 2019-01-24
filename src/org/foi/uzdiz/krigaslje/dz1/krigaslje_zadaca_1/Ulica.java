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
public class Ulica {
    String naziv;
    int brojMjesta;
    int udioMali;
    int udioSrednji;
    int udioVeliki;
    int brojMalih;
    int brojSrednjih;
    int brojVelikih;
    
    float otpadStaklo;
    float otpadPapir;
    float otpadMetal;
    float otpadBio;
    float otpadMjesano;
    
    public List<Korisnik> stanovnici;
    public List<Spremnik> spremnici;
    public Ulica(String naziv, int brojMjesta, int udioMali, int udioSrednji, int udioVeliki) {
        this.naziv = naziv;
        this.brojMjesta = brojMjesta;
        this.udioMali = udioMali;
        this.udioSrednji = udioSrednji;
        this.udioVeliki = udioVeliki;
        stanovnici = new ArrayList<>();
        spremnici = new ArrayList<>();
    }

    void odrediKorisnike() {
        brojMalih = (int)(brojMjesta*(udioMali/100.0));
        brojSrednjih = (int)(brojMjesta*(udioSrednji/100.0));
        brojVelikih = (int)(brojMjesta*(udioVeliki/100.0));
        int ukupno = brojMalih+brojVelikih+brojSrednjih;
        //System.out.println(brojMalih + " " + brojVelikih + " " + brojSrednjih + "\n");
        if(ukupno!=brojMjesta){
            //ak zbog postotaka brojke krivo otideju i ne pašu točno
            if(ukupno<brojMjesta){
                while (ukupno<brojMjesta){
                    brojMalih++;
                    ukupno++;
                }
            }else{
                while (ukupno>brojMjesta){
                    brojMalih--;
                    ukupno--;
                }
            }
        }
        for (int i = 0; i < brojMalih; i++) {
            stanovnici.add(new Korisnik(1));
        }
        for (int i = 0; i < brojSrednjih; i++) {
            stanovnici.add(new Korisnik(2));
        }
        
        for (int i = 0; i < brojVelikih; i++) {
            stanovnici.add(new Korisnik(3));
        }
    }
    
    
    public void dodijeliSpremnike(){
        for(KategorijaSpremnika ks : KategorijaSpremnika.listaKategorijaSpremnika){
            if(ks.naMale>0&&brojMalih>0){
                //dodamo malim korisnicima spremnike
                Spremnik noviSpremnik = new Spremnik(ks.naziv, ks.tip, ks.naMale, ks.naSrednje, ks.naVelike, ks.nosivost);
                spremnici.add(noviSpremnik);
                for(Korisnik k: stanovnici){
                    if(k.tip==1){
                        if(noviSpremnik.mozePrimitKorisnika(k.tip)){
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }else{
                            noviSpremnik = noviSpremnik.kloniraj();
                            spremnici.add(noviSpremnik);
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }
                    }
                }
            }
            if(ks.naSrednje>0&&brojSrednjih>0){
                //dodamo malim korisnicima spremnike
                Spremnik noviSpremnik = new Spremnik(ks.naziv, ks.tip, ks.naMale, ks.naSrednje, ks.naVelike, ks.nosivost);
                spremnici.add(noviSpremnik);
                for(Korisnik k: stanovnici){
                    if(k.tip==2){
                        if(noviSpremnik.mozePrimitKorisnika(k.tip)){
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }else{
                            noviSpremnik = noviSpremnik.kloniraj();
                            spremnici.add(noviSpremnik);
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }
                    }
                }
            }
            if(ks.naVelike>0&&brojVelikih>0){
                //dodamo malim korisnicima spremnike
                Spremnik noviSpremnik = new Spremnik(ks.naziv, ks.tip, ks.naMale, ks.naSrednje, ks.naVelike, ks.nosivost);
                spremnici.add(noviSpremnik);
                for(Korisnik k: stanovnici){
                    if(k.tip==3){
                        if(noviSpremnik.mozePrimitKorisnika(k.tip)){
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }else{
                            noviSpremnik = new Spremnik(ks.naziv, ks.tip, ks.naMale, ks.naSrednje, ks.naVelike, ks.nosivost);
                            spremnici.add(noviSpremnik);
                            k.dodijeljeniSpremnici.add(noviSpremnik);
                            noviSpremnik.korisniciSpremnika.add(k);
                        }
                    }
                }
            }
        }
    }
    public void generirajOtpadKorisnika(){
        Ispis.getInstance().uvjetovaniIspis("***generiranje otpada***"+naziv + "***");
        for(Korisnik k: stanovnici){
            k.generirajOtpad();
        }
        
    }
    public void odloziOtpad(){
        Ispis.getInstance().uvjetovaniIspis("***odlaganje otpada***"+naziv+"***");
        for(Korisnik k: stanovnici){
            k.baciOtpad();
        }
        for(Spremnik s: spremnici){
            if(s.naziv.equals("staklo")) otpadStaklo+=s.kolicinaOtpada;
            if(s.naziv.equals("papir")) otpadPapir+=s.kolicinaOtpada;
            if(s.naziv.equals("metal")) otpadMetal+=s.kolicinaOtpada;
            if(s.naziv.equals("bio")) otpadBio+=s.kolicinaOtpada;
            if(s.naziv.equals("mješano")) otpadMjesano+=s.kolicinaOtpada;
        }
        Ispis.getInstance().uvjetovaniIspis("\nOtpad u ulici " + naziv + "\n");
        Ispis.getInstance().uvjetovaniIspis("Staklo: " + otpadStaklo);
        Ispis.getInstance().uvjetovaniIspis("Papir: " + otpadPapir);
        Ispis.getInstance().uvjetovaniIspis("Metal: " + otpadMetal);
        Ispis.getInstance().uvjetovaniIspis("Bio: " + otpadBio);
        Ispis.getInstance().uvjetovaniIspis("Mješano: " + otpadMjesano);
        Ispis.getInstance().uvjetovaniIspis("\n\n");
    }
    public String info(){
        String info = naziv + "\nStanovnici: \n";
        for(Korisnik k:stanovnici) info = info + k.id + ":  "+ k.tip + "\n";
        return info+"\n";
    }
}
