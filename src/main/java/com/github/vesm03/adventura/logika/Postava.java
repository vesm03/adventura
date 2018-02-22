/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy Postava představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String[] proslov;
    private int pocitadlo;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor nastaví jméno, proslov a počítadlo postavě
     */
    public Postava(String jmeno, String[] proslov)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.pocitadlo = 0;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací jméno postavy.
     * 
     * @return   String jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }
    
    /**
     * Getter na stav počítadla.
     * 
     * @return   int pocitadlo.
     */
    public int getPocitadlo() {
        return pocitadlo; 
    }
    
    /**
     * Setter na stav počítadla - navýšení 1.
     * 
     */
    public void setPocitadlo() {
        pocitadlo++; 
    }
    
    /**
     * Getter na počet různých vět, které může postava říct.
     * 
     * @return   int proslov.length.
     */
    public int getDelkaProslovu() {
        return proslov.length; 
    }
    
    /**
     * Metoda vrací proslov postavy podle počítadla.
     * 
     * @return   String proslov postavy.
     */
    public String getProslov() {
        return proslov[pocitadlo];
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
