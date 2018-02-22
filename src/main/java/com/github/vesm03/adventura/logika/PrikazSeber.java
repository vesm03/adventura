/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param plan herní plánek hry (mapa) po kterém se hráč pohybuje
     *  @param batoh, batoh, do kterého se vkládají věci
     */
    public PrikazSeber(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "seber". Sbírá věci, které se nacházejí v prostoru a které lze sebrat, 
     *  poté je vkládá do batohu
     *  Pokud nejsou parametry, vypíše se chybové hlášení
     *
     *@param parametry - jako  parametr obsahuje jméno veci
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat?";
        }

        String nazevSbiraneVeci = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Vec sbiranaVec = plan.getAktualniProstor().odeberVec(nazevSbiraneVeci);

        if (sbiranaVec == null) {
            return "To tu není";
        }
        else {
            if (sbiranaVec.getJePrenositelna()){
                //je treba vyresit kapacitu batohu 
                if (batoh.vlozVecDoBatohu(sbiranaVec)){
                    return "Sebral jsi " + nazevSbiraneVeci;
                }
                else {
                    plan.getAktualniProstor().vlozVec(sbiranaVec);
                    return "Tolik věcí nepobereš";
                }
            }
            else{
                //vrátit věc do prostoru
                plan.getAktualniProstor().vlozVec(sbiranaVec);
                return "Tohle nemůžeš sebrat";
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
