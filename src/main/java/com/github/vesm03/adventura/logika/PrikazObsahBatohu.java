/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazObsahBatohu představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PrikazObsahBatohu implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "obsah_batohu";
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param batoh, batoh, do kterého se vkládají věci
     */
    public PrikazObsahBatohu(Batoh batoh)
    {
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Vypíše hráči seznam věcí z batohu
     *  Pokud nejsou parametry, vypíše se chybové hlášení
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        return batoh.nazvyVeciVBatohu();
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
