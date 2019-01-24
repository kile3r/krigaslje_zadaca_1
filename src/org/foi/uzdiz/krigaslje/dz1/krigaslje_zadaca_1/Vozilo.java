/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.List;
import org.foi.uzdiz.krigaslje.dz1.singleton.Ispis;
import org.foi.uzdiz.krigaslje.dz1.singleton.Parametri;

/**
 *
 * @author kile
 */
public class Vozilo {
    String naziv;
    int tip;
    int vrsta;
    int nosivost;
    String vozaci;
    int trenutnaKolicina;
    int status;
    int ciklusiOdvoza;
    String vrstaString;

    public Vozilo(String naziv, int tip, int vrsta, int nosivost, String vozaci) {
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.nosivost = nosivost;
        this.vozaci = vozaci;
        this.trenutnaKolicina = 0;
        this.status = 1;
        this.ciklusiOdvoza = 0;
        this.vrstaString = mapirajVrstu(vrsta);
    }
    public void preuzmiOtpad(List<Ulica> ulice){
        if(status==1){
            for(Ulica u : ulice){
                for(Spremnik s: u.spremnici){
                    if(s.naziv.equals(vrstaString)&&s.kolicinaOtpada>0){
                        if((trenutnaKolicina+s.kolicinaOtpada)<nosivost){
                            trenutnaKolicina+=s.kolicinaOtpada;
                            
                            Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je preuzelo " + s.kolicinaOtpada +"kg otpada iz spremnika " + s.shortInfo() +". #= " + trenutnaKolicina + "/" + nosivost);
                            s.kolicinaOtpada = 0;
                        }else{
                            status = 0;
                            Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je napunjeno i odvozi otpad na odlagalište!");
                        }
                        return;
                    }
                }
            }
        }else{
            ciklusiOdvoza++;
            if(ciklusiOdvoza>=Parametri.getBrojRadnihCiklusaZaOdvoz()){
                ciklusiOdvoza=0;
                status=1;
                trenutnaKolicina = 0;
                Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je obavilo odvoz otpada na odlagalište!");
            }else{
                Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " odvozi otpad na odlagalište!");
            }
        }
    }
    private String mapirajVrstu(int vrsta){
        if(vrsta==0) return "staklo";
        if(vrsta==1) return "papir";
        if(vrsta==2) return "metal";
        if(vrsta==3) return "bio";
        if(vrsta==4) return "mješano";
        else return "";
    }

    void iprazniKamion() {
        Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " odvozi otpad na odlagalište!");
    }
    
}
