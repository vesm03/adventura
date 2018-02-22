/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazOdemkni představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PrikazOdemkni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param plan herní plánek hry (mapa) po kterém se hráč pohybuje
     *  @param batoh, batoh, do kterého se vkládají věci
     */
    public PrikazOdemkni(HerniPlan plan, Batoh batoh){
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "odemkni". Zjišťuje, zda z aktuální místnosti je
     *  východ do zadané místnosti. Pokud místnost existuje a je zamčená,
     *  tak se zjistí, zda v batohu je potřebný klíč. Pokud ano, odemkne
     *  místnost.
     *
     *@param parametry - jako  parametr obsahuje jméno místnosti,
     *                         do které se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "Nevím co mám odemknout. Musíš zadat jméno místnosti";
        }

        String prostor = parametry[0];

        // hledám zadanou místnost mezi východy
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);

        if (sousedniProstor == null) {
            return "Odsud nevedou dveře do místnosti "+prostor+" !";
        }
        else {
            if (sousedniProstor.jeZamceno()) {
                if (batoh.obsahujeVecNazev(sousedniProstor.getKlic())){
                    sousedniProstor.zamknout(false);
                    batoh.vyberVecVBatohu(sousedniProstor.getKlic());
                    return "Otevřel jsi dveře do místnosti "
                           + prostor;
                }
                else {
                    return "Pro odemčení dveří do "+prostor+" potřebuješ mít "
                        + "u sebe "+sousedniProstor.getKlic();
                }
            }
            else {
                return "Místnost "+prostor+" již byla odemčená!!!";
            }
        }
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
