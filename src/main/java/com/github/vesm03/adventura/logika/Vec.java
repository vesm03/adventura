/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
	public String nazev;
	public boolean prenositelnost;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Vrací název věci
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     *  Vrací true/false podle toho, zdali je věc přenositelná
     */
    public boolean getJePrenositelna(){
        return prenositelnost;
    }
    
    @Override
    public String toString() {
    // TODO Auto-generated method stub
    	return getNazev();
	}

    //== Soukromé metody (instancí i třídy) ========================================

}
