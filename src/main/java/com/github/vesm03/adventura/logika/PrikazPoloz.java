/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;

import java.util.Observable;

/*******************************************************************************
 * Instance třídy PrikazPoloz představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PrikazPoloz extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "polož";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param plan herní plánek hry (mapa) po kterém se hráč pohybuje
     *  @param batoh, batoh, do kterého se vkládají věci
     */
    public PrikazPoloz(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "polož". Pokládá věci do prosotoru, ve kterém se hrář právě nachází.
     *  Pokud nejsou parametry, vypíše se chybové hlášení
     *
     *@param parametry - jako  parametr obsahuje jméno veci
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám položit? Musíš zadat jméno věci";
        }

        String jmenoVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
       
        Vec pokladanaVec = batoh.vyberVecVBatohu(jmenoVeci);
        if(pokladanaVec == null){
            return "Taková věc v batohu není";
        }
        aktualniProstor.vlozVec(pokladanaVec);
        setChanged();
        notifyObservers();
        return "Položil jsi věc " +jmenoVeci;     
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
