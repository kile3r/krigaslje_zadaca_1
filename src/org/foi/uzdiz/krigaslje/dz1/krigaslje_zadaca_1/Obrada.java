/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz1.krigaslje_zadaca_1;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz1.singleton.Ispis;
import org.foi.uzdiz.krigaslje.dz1.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dz1.singleton.RndGenerator;

/**
 *
 * @author kile
 */
public class Obrada {

    private List<Vozilo> listaVozila;
    private List<Ulica> listaUlica;
    private boolean radnoVrijeme;
    private float ukupnoStaklo, ukupnoPapir, ukupnoMetal, ukupnoBio, ukupnoMjesano;

    public Obrada(List<Vozilo> listaVozila, List<Ulica> listaUlica) {
        this.listaVozila = listaVozila;
        this.listaUlica = listaUlica;
        this.radnoVrijeme = true;
        this.ukupnoStaklo = 0;
        this.ukupnoPapir = 0;
        this.ukupnoMetal = 0;
        this.ukupnoBio = 0;
        this.ukupnoMjesano = 0;
    }

    private List<Vozilo> inicijalizirajRandomRedoslijedVozila() {
        izmijeniRedoslijedUListiVozila(listaVozila);
        return listaVozila;
    }

    private List<Ulica> inicijalizirajRandomRedoslijedUlica() {
        izmijeniRedoslijedUListiUlica(listaUlica);
        return listaUlica;
    }

    private List<Ulica> inicijalizirajRandomRedoslijedUlicaVozila() {
        List<Ulica> listaUlicaVozila = new ArrayList<>();
        listaUlicaVozila = listaUlica;
        izmijeniRedoslijedUListiUlica(listaUlicaVozila);
        return listaUlicaVozila;
    }

    public void obradi() {
        zbrojiUkupniOtpadUSpremnicima();
        listaVozila = inicijalizirajRandomRedoslijedVozila();

        if (Parametri.getPreuzimanja() == 0) {
            // random inicijalizacija svih ulica
            listaUlica = inicijalizirajRandomRedoslijedUlica();
        } else {
            // radnom inicijalizacija svih ulica za svako vozilo
            for (Vozilo vozilo : listaVozila) {
                vozilo.setListaUlicaVozila(inicijalizirajRandomRedoslijedUlicaVozila());
            }
        }

        // obrada ovisno o parametru
        if (Parametri.getPreuzimanja() == 0) {
            preuzmiOtpadPremaUlicama();
        } else {
            preuzmiOtpadPremaVozilima();
        }

    }

    private void preuzmiOtpadPremaUlicama() {
        int brojCiklusa = 0;
        while (radnoVrijeme) {
            Ispis.getInstance().ispisiDetalje((brojCiklusa + 1) + ". ciklus odvoza...\n");
            for (Vozilo vozilo : listaVozila) {
                if (vozilo.status == 0) {
                    preuzmiOtpad(vozilo);
                } else {
                    if (brojCiklusa >= (vozilo.ciklusiOdvoza + Parametri.getBrojRadnihCiklusaZaOdvoz())) {
                        vozilo.status = 0;
                        vozilo.ciklusiOdvoza = brojCiklusa;
                        preuzmiOtpad(vozilo);
                    }
                }
            }
            brojCiklusa++;
        }
        // kraj dana i sva vozila koja nisu vec na odvozu se prazne
        Ispis.getInstance().ispisiDetalje("\nKraj dana!");
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.status == 0) {
                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " odvozi " + vozilo.trenutnaKolicina + " " + vozilo.vrstaString + " na odlagalište.\n");
                vozilo.status = 1;
                vozilo.trenutnaKolicina = 0;
            }
        }
        Ispis.getInstance().ispisiDetalje("\n");
    }

    private void preuzmiOtpadPremaVozilima() {
        int brojCiklusa = 0;
        while (radnoVrijeme) {
            Ispis.getInstance().ispisiDetalje((brojCiklusa + 1) + ". ciklus odvoza...\n");
            for (Vozilo vozilo : listaVozila) {
                if (vozilo.status == 0) {
                    preuzmiOtpadPremaVozilima(vozilo);
                } else {
                    if (brojCiklusa >= (vozilo.ciklusiOdvoza + Parametri.getBrojRadnihCiklusaZaOdvoz())) {
                        vozilo.status = 0;
                        vozilo.ciklusiOdvoza = brojCiklusa;
                        preuzmiOtpadPremaVozilima(vozilo);
                    }
                }
            }
            brojCiklusa++;
        }
        // kraj dana i sva vozila koja nisu vec na odvozu se prazne
        Ispis.getInstance().ispisiDetalje("\nKraj dana!");
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.status == 0) {
                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " odvozi " + vozilo.trenutnaKolicina + " " + vozilo.vrstaString + " na odlagalište.");
                vozilo.brojOdlazakaNaZbrinjavanje++;//ovo
                vozilo.status = 1;
                vozilo.trenutnaKolicina = 0;
            }
        }
        Ispis.getInstance().ispisiDetalje("\n");

    }

    private void preuzmiOtpad(Vozilo vozilo) {
        for (Ulica ulica : listaUlica) {
            for (Spremnik spremnik : ulica.spremnici) {
                if (vozilo.vrstaString.equals(spremnik.naziv) && spremnik.kolicinaOtpada > 0) {
                    vozilo.ciklusiOdvoza++;
                    if (Float.compare(vozilo.nosivost, (vozilo.trenutnaKolicina + spremnik.kolicinaOtpada)) > 0) {
                        vozilo.trenutnaKolicina += spremnik.kolicinaOtpada;
                        Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " preuzelo je iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ".");
                        Ispis.getInstance().ispisiDetalje("Trenutna popunjenost vozila " + vozilo.naziv + " iznosi: " + vozilo.trenutnaKolicina + "/" + vozilo.nosivost + "\n");
                        vozilo.brojKontejnera++;//ovo 

                        oduzmiUkupniOtpadUSpremnicima(spremnik.naziv, spremnik.kolicinaOtpada);
                        spremnik.kolicinaOtpada = 0;
                        if (Float.compare(ukupnoStaklo, 0) == 0 && Float.compare(ukupnoPapir, 0) == 0 && Float.compare(ukupnoMetal, 0) == 0 && Float.compare(ukupnoBio, 0) == 0 && Float.compare(ukupnoMjesano, 0) == 0) {
                            radnoVrijeme = false;
                        }
                    } else if (Float.compare(vozilo.nosivost, (vozilo.trenutnaKolicina + spremnik.kolicinaOtpada)) == 0) {
                        vozilo.trenutnaKolicina += spremnik.kolicinaOtpada;
                        Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " preuzelo je iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ".");
                        Ispis.getInstance().ispisiDetalje("Trenutna popunjenost vozila " + vozilo.naziv + " iznosi: " + vozilo.trenutnaKolicina + "/" + vozilo.nosivost + "\n");
                        vozilo.brojKontejnera++;//ovo 
                        oduzmiUkupniOtpadUSpremnicima(spremnik.naziv, spremnik.kolicinaOtpada);
                        spremnik.kolicinaOtpada = 0;
                        if (Float.compare(ukupnoStaklo, 0) == 0 && Float.compare(ukupnoPapir, 0) == 0 && Float.compare(ukupnoMetal, 0) == 0 && Float.compare(ukupnoBio, 0) == 0 && Float.compare(ukupnoMjesano, 0) == 0) {
                            radnoVrijeme = false;
                        }
                        Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " napunjeno je do svoje nosivosti. Odvoz otpada na odlagalište...");
                        vozilo.brojOdlazakaNaZbrinjavanje++;//ovo
                        //vozilo.ukupnaKolicinaOtpada+=vozilo.trenutnaKolicina;//ovo 
                        vozilo.status = 1;
                        vozilo.trenutnaKolicina = 0;
                    } else {
                        Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " napunjeno je " + vozilo.trenutnaKolicina + " i ne može preuzeti iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ". Odvoz otpada na odlagalište...");
                        vozilo.brojOdlazakaNaZbrinjavanje++;//ovo
                        //vozilo.ukupnaKolicinaOtpada+=vozilo.trenutnaKolicina;//ovo 
                        vozilo.status = 1;
                        vozilo.trenutnaKolicina = 0;

                        //Vozilo tempVozilo = vozilo;
                        //tempVozilo.trenutnaKolicina = 0;
                        //tempVozilo.status = 1;
                        //vozilo se stavlja na kraj liste
                        //listaVozila.remove(vozilo);
                        //listaVozila.add(tempVozilo);
                    }
                    return;
                }
                vozilo.brojMjesta++;//ovo 
                vozilo.ukupnaKolicinaOtpada += vozilo.trenutnaKolicina;//ovo 
            }
        }

    }

    private void preuzmiOtpadPremaVozilima(Vozilo vozilo) {
        for (Ulica ulicaVozila : vozilo.listaUlicaVozila) {
            for (Ulica ulica : listaUlica) {
                if (ulicaVozila.naziv.equals(ulica.naziv)) {
                    for (Spremnik spremnik : ulica.spremnici) {
                        if (vozilo.vrstaString.equals(spremnik.naziv) && spremnik.kolicinaOtpada > 0) {
                            vozilo.ciklusiOdvoza++;
                            if (Float.compare(vozilo.nosivost, (vozilo.trenutnaKolicina + spremnik.kolicinaOtpada)) > 0) {
                                vozilo.trenutnaKolicina += spremnik.kolicinaOtpada;
                                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " preuzelo je iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ".");
                                Ispis.getInstance().ispisiDetalje("Trenutna popunjenost vozila " + vozilo.naziv + " iznosi: " + vozilo.trenutnaKolicina + "/" + vozilo.nosivost + "\n");
                                vozilo.brojKontejnera++;//ovo 
                                oduzmiUkupniOtpadUSpremnicima(spremnik.naziv, spremnik.kolicinaOtpada);
                                spremnik.kolicinaOtpada = 0;
                                if (Float.compare(ukupnoStaklo, 0) == 0 && Float.compare(ukupnoPapir, 0) == 0 && Float.compare(ukupnoMetal, 0) == 0 && Float.compare(ukupnoBio, 0) == 0 && Float.compare(ukupnoMjesano, 0) == 0) {
                                    radnoVrijeme = false;
                                }
                            } else if (Float.compare(vozilo.nosivost, (vozilo.trenutnaKolicina + spremnik.kolicinaOtpada)) == 0) {
                                vozilo.trenutnaKolicina += spremnik.kolicinaOtpada;
                                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " preuzelo je iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ".");
                                Ispis.getInstance().ispisiDetalje("Trenutna popunjenost vozila " + vozilo.naziv + " iznosi: " + vozilo.trenutnaKolicina + "/" + vozilo.nosivost + "\n");
                                vozilo.brojKontejnera++;//ovo 
                                oduzmiUkupniOtpadUSpremnicima(spremnik.naziv, spremnik.kolicinaOtpada);
                                spremnik.kolicinaOtpada = 0;
                                if (Float.compare(ukupnoStaklo, 0) == 0 && Float.compare(ukupnoPapir, 0) == 0 && Float.compare(ukupnoMetal, 0) == 0 && Float.compare(ukupnoBio, 0) == 0 && Float.compare(ukupnoMjesano, 0) == 0) {
                                    radnoVrijeme = false;
                                }
                                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " napunjeno je do svoje nosivosti. Odvoz otpada na odlagalište...");
                                vozilo.brojOdlazakaNaZbrinjavanje++;//ovo
                                //vozilo.ukupnaKolicinaOtpada+=vozilo.trenutnaKolicina;//ovo 
                                vozilo.status = 1;
                                vozilo.trenutnaKolicina = 0;
                            } else {
                                Ispis.getInstance().ispisiDetalje("Vozilo " + vozilo.naziv + " napunjeno je " + vozilo.trenutnaKolicina + " i ne može preuzeti iz ulice " + ulica.naziv + " spremnik[" + spremnik.id + "] " + spremnik.kolicinaOtpada + "kg " + spremnik.naziv + ". Odvoz otpada na odlagalište...");
                                vozilo.brojOdlazakaNaZbrinjavanje++;//ovo
                                //vozilo.ukupnaKolicinaOtpada+=vozilo.trenutnaKolicina;//ovo 
                                vozilo.status = 1;
                                vozilo.trenutnaKolicina = 0;

                                //Vozilo tempVozilo = vozilo;
                                //tempVozilo.trenutnaKolicina = 0;
                                //tempVozilo.status = 1;
                                //vozilo se stavlja na kraj liste
                                //listaVozila.remove(vozilo);
                                //listaVozila.add(tempVozilo);
                            }
                            return;
                        }
                    }
                } else {
                    continue;
                }
            }
            vozilo.brojMjesta++;//ovo
            vozilo.ukupnaKolicinaOtpada += vozilo.trenutnaKolicina;//ovo
        }
    }

    private void zbrojiUkupniOtpadUSpremnicima() {
        List<String> tipoviVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (!tipoviVozila.contains(vozilo.vrstaString)) {
                tipoviVozila.add(vozilo.vrstaString);
            }
        }
        for (Ulica ulica : listaUlica) {
            for (Spremnik spremnik : ulica.spremnici) {
                switch (spremnik.naziv) {
                    case "staklo":
                        if (tipoviVozila.contains("staklo")) {
                            ukupnoStaklo += spremnik.kolicinaOtpada;
                        }
                        break;
                    case "papir":
                        if (tipoviVozila.contains("papir")) {
                            ukupnoPapir += spremnik.kolicinaOtpada;
                        }
                        break;
                    case "metal":
                        if (tipoviVozila.contains("metal")) {
                            ukupnoMetal += spremnik.kolicinaOtpada;
                        }
                        break;
                    case "bio":
                        if (tipoviVozila.contains("bio")) {
                            ukupnoBio += spremnik.kolicinaOtpada;
                        }
                        break;
                    case "mješano":
                        if (tipoviVozila.contains("mješano")) {
                            ukupnoMjesano += spremnik.kolicinaOtpada;
                        }
                        break;
                }
            }
        }
    }

    private void oduzmiUkupniOtpadUSpremnicima(String naziv, float kolicinaOtpada) {
        switch (naziv) {
            case "staklo":
                ukupnoStaklo -= kolicinaOtpada;
                break;
            case "papir":
                ukupnoPapir -= kolicinaOtpada;
                break;
            case "metal":
                ukupnoMetal -= kolicinaOtpada;
                break;
            case "bio":
                ukupnoBio -= kolicinaOtpada;
                break;
            case "mješano":
                ukupnoMjesano -= kolicinaOtpada;
                break;
        }
    }

    private static void izmijeniRedoslijedUListiVozila(List<Vozilo> lista) {
        for (int i = 0; i < lista.size(); i++) {
            int j = i + RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, lista.size() - i);
            zamijeniVozilo(lista, i, j);
        }
    }

    private static void zamijeniVozilo(List<Vozilo> lista, int i, int j) {
        Vozilo vozilo = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, vozilo);
    }

    private static void izmijeniRedoslijedUListiUlica(List<Ulica> lista) {
        for (int i = 0; i < lista.size(); i++) {
            int j = i + RndGenerator.getRndGeneratorInstance().dajSlucajniBroj(0, lista.size() - i);
            zamijeniUlicu(lista, i, j);
        }
    }

    private static void zamijeniUlicu(List<Ulica> lista, int i, int j) {
        Ulica ulica = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, ulica);
    }

    public List<Vozilo> getListaVozila() {
        return listaVozila;
    }

    public void setListaVozila(List<Vozilo> listaVozila) {
        this.listaVozila = listaVozila;
    }

    public List<Ulica> getListaUlica() {
        return listaUlica;
    }

    public void setListaUlica(List<Ulica> listaUlica) {
        this.listaUlica = listaUlica;
    }

    public boolean isRadnoVrijeme() {
        return radnoVrijeme;
    }

    public void setRadnoVrijeme(boolean radnoVrijeme) {
        this.radnoVrijeme = radnoVrijeme;
    }

    public float getUkupnoStaklo() {
        return ukupnoStaklo;
    }

    public void setUkupnoStaklo(float ukupnoStaklo) {
        this.ukupnoStaklo = ukupnoStaklo;
    }

    public float getUkupnoPapir() {
        return ukupnoPapir;
    }

    public void setUkupnoPapir(float ukupnoPapir) {
        this.ukupnoPapir = ukupnoPapir;
    }

    public float getUkupnoMetal() {
        return ukupnoMetal;
    }

    public void setUkupnoMetal(float ukupnoMetal) {
        this.ukupnoMetal = ukupnoMetal;
    }

    public float getUkupnoBio() {
        return ukupnoBio;
    }

    public void setUkupnoBio(float ukupnoBio) {
        this.ukupnoBio = ukupnoBio;
    }

    public float getUkupnoMjesano() {
        return ukupnoMjesano;
    }

    public void setUkupnoMjesano(float ukupnoMjesano) {
        this.ukupnoMjesano = ukupnoMjesano;
    }

}
