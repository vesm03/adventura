/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazPromluv představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PrikazPromluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "promluv";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param plan herní plánek hry (mapa) po kterém se hráč pohybuje
     *  @param batoh, batoh, do kterého se vkládají věci
     */
    public PrikazPromluv(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "promluv". Díky tomuto příkazu některé postavy promluví a pomohou hráči dále ve hře.
     *  -> poodhalí mu některé předměty, východy z místností atp.
     *  @param - jméno postavy
     *  @return - proslov postavy
     *
     */ 
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nezadal jsi název postavy, se kterou chceš promluvit.";
        }
        String jmeno = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        if (postava == null) {
            return "Tato postava tu není.";
        }
        if (postava.getJmeno().contains("kostlivec")) {
            if (postava.getPocitadlo() == 1){
                postava.setPocitadlo();
            }
            
            if (batoh.obsahujeVecNazev("rum")){
                batoh.vyberVecVBatohu("rum");
                Vec klic_obycejny = new Vec("obyčejný_klíč", true);
                plan.vlozVecDoProstoru(klic_obycejny, plan.seznamProstoru2[5]);
                if (postava.getDelkaProslovu() >= postava.getPocitadlo() + 1){
                    postava.setPocitadlo();
                }
            }
            
            return postava.getJmeno() + ": " + postava.getProslov();
        }
        
        if (postava.getJmeno().contains("duch")) {
            if (postava.getPocitadlo() >= 1 && postava.getDelkaProslovu() > postava.getPocitadlo() + 1){
                postava.setPocitadlo();
            }
            if (postava.getPocitadlo() == 0){
                postava.setPocitadlo();
                plan.seznamProstoru2[3].setVychod(plan.seznamProstoru2[7]);
            }
            return postava.getJmeno() + ": " + postava.getProslov();
        }
        
        return "Nikoho takového tu nevidím.";
        
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
