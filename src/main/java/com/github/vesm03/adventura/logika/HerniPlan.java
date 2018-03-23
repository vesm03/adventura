/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;
import com.github.vesm03.adventura.main.Start;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Martin Veselý
 *@version    28. 12. 2016
 */
public class HerniPlan extends Observable {
    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    public Prostor[] seznamProstoru2;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  
     */
    public HerniPlan() {
        zalozProstoryHry();
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů, věci, postavy a umisťuje je do prostorů.
     *  Jako výchozí aktuální prostor nastaví temnou místnost.
     *  Jako vítězný prostor nastaví palubu
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor paluba = new Prostor("paluba","paluba pirátské lodi", true, 244.0, 0.0);
        Prostor kajuta = new Prostor("kajuta", "opuštěná kajuta", false, 85.0, 116.0);
        Prostor chodba = new Prostor("chodba","chodba uprostřed podpalubí", false, 291.0, 116.0);
        Prostor kajuta_kuchare = new Prostor("kajuta_kuchaře","bývalá kajuta kuchaře", false, 418.0, 109.0);
        Prostor sklad_naradi = new Prostor("sklad_nářadí","místnost určená pro skladování nářadí", true, 85.0, 211.0);
        Prostor temna_mistnost = new Prostor("temná_místnost","temná místnost, do které piráti uvěznili Jordana", false, 284.0, 215.0);
        Prostor sklad_potravin = new Prostor("sklad_potravin","místnost určená pro skladování potravin", false, 418.0, 216.0);
        
        Prostor tajna_mistnost = new Prostor("tajná_místnost","tajná místnost v kajutě kuchaře", true, 457.0, 60.0);
        
        seznamProstoru2 = new Prostor[8];
        seznamProstoru2[0] = paluba;
        seznamProstoru2[1] = kajuta;
        seznamProstoru2[2] = chodba;
        seznamProstoru2[3] = kajuta_kuchare;
        seznamProstoru2[4] = sklad_naradi;
        seznamProstoru2[5] = temna_mistnost;
        seznamProstoru2[6] = sklad_potravin;
        seznamProstoru2[7] = tajna_mistnost;
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        kajuta.setVychod(chodba);
        kajuta.setVychod(sklad_naradi);
        chodba.setVychod(kajuta);
        chodba.setVychod(kajuta_kuchare);
        chodba.setVychod(temna_mistnost);
        chodba.setVychod(paluba);
        kajuta_kuchare.setVychod(chodba);
        kajuta_kuchare.setVychod(sklad_potravin);
        //kajuta_kuchare.setVychod(tajna_mistnost); //otevrit az po splneni ukolu
        sklad_naradi.setVychod(kajuta);
        sklad_naradi.setVychod(temna_mistnost);
        temna_mistnost.setVychod(chodba);
        temna_mistnost.setVychod(sklad_naradi);
        temna_mistnost.setVychod(sklad_potravin);
        sklad_potravin.setVychod(temna_mistnost);
        sklad_potravin.setVychod(kajuta_kuchare);
        tajna_mistnost.setVychod(kajuta_kuchare);
        
        paluba.nastavKlic("klíč");
        tajna_mistnost.nastavKlic("tajný_klíč");
        sklad_naradi.nastavKlic("obyčejný_klíč");
        
        Vec rum = new Vec("rum", true);
        Vec pomeranc = new Vec("pomeranč", true);
        Vec stul = new Vec("stůl", false);
        Vec klic_tajny = new Vec("tajný_klíč", true);
        Vec klic = new Vec("klíč", true);
        Vec klic_rezavy = new Vec("rezavý_klíč", true);
        
        Vec postel = new Vec("postel", false);
        Vec skrin = new Vec("skříň", false);
        Vec stul2 = new Vec("stůl", false);
        Vec lano = new Vec("lano", true);
        Vec lahev = new Vec("lahev", true);
        Vec klobouk = new Vec("klobouk", true);
        Vec kotva = new Vec("kotva", true);
        Vec plachta = new Vec("plachta", true);
        Vec banan = new Vec("banán", true);
        Vec maso = new Vec("shnilé_maso", true);
        Vec bambitka = new Vec("bambitka", true);
        Vec strelny_prach = new Vec("střelný_prach", true);
        Vec postel2 = new Vec("postel", false);
        Vec nuz = new Vec("nůž", true);
        Vec zlato = new Vec("zlato", true);
        Vec lebka = new Vec("lebka", true);
        
        
        kajuta_kuchare.vlozVec(rum);
        kajuta_kuchare.vlozVec(stul);
        kajuta_kuchare.vlozVec(pomeranc);
        sklad_potravin.vlozVec(klic_tajny);
        tajna_mistnost.vlozVec(klic);
        temna_mistnost.vlozVec(klic_rezavy);
        
        kajuta.vlozVec(postel);
        kajuta.vlozVec(skrin);
        sklad_naradi.vlozVec(stul2);
        sklad_naradi.vlozVec(lano);
        sklad_naradi.vlozVec(kotva);
        sklad_naradi.vlozVec(plachta);
        chodba.vlozVec(lahev);
        chodba.vlozVec(klobouk);
        sklad_potravin.vlozVec(banan);
        sklad_potravin.vlozVec(maso);
        kajuta_kuchare.vlozVec(bambitka);
        kajuta_kuchare.vlozVec(strelny_prach);
        kajuta_kuchare.vlozVec(postel2);
        tajna_mistnost.vlozVec(nuz);
        tajna_mistnost.vlozVec(zlato);
        tajna_mistnost.vlozVec(lebka);
        
        
        String[] kostlivec_rec = {"Přines mi rum a možná ti něco poradím", "Rozhlídni se lépe po temné místnosti", "Teď už si poraď sám"};
        Postava kostlivec = new Postava("kostlivec", kostlivec_rec);
        kajuta.vlozPostavu(kostlivec);
        
        String[] duch_rec = {"", "V kajutě kuchaře je tajná místnost, otevři mi jí.", "No tak na co čekáš?!", "Teď už si poraď sám"};
        Postava duch = new Postava("duch", duch_rec);
        sklad_naradi.vlozPostavu(duch);
                
        aktualniProstor = temna_mistnost;  // hra začíná v domečku
        viteznyProstor = paluba;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda pro vložení věci do prostoru.
     *
     */
    public void vlozVecDoProstoru(Vec vec, Prostor prostor){
        prostor.vlozVec(vec);
        setChanged();
        notifyObservers();
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       setChanged();
       notifyObservers();
    }
    
    /**
     *  Metoda vrací true/false podle toho, zda se nachází hráč ve vítězném prostoru
     *
     */
    public boolean jeVyhra(){
        return aktualniProstor.equals(viteznyProstor);
    }

}
