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
public class KategorijaSpremnika {
    String naziv;
    int tip;
    int nosivost;
    int naMale;
    int naSrednje;
    int naVelike;
    public static List<KategorijaSpremnika> listaKategorijaSpremnika = new ArrayList<>();

    public KategorijaSpremnika(String naziv, int tip, int naMale, int naSrednje, int naVelike,int nosivost) {
        this.naziv = naziv;
        this.tip = tip;
        this.nosivost = nosivost;
        this.naMale = naMale;
        this.naSrednje = naSrednje;
        this.naVelike = naVelike;
        listaKategorijaSpremnika.add(this);
    }
}
