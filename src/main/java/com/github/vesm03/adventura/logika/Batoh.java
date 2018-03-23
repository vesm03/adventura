/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;
import java.util.Map;
import java.util.Observable;
import java.util.HashMap;


/*******************************************************************************
 * Instance třídy Batoh představují ...
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class Batoh extends Observable 
{
    //== Datové atributy (statické i instancí)======================================
    private static int kapacita = 1 ;
    private Map<String, Vec> seznamVeci ; 
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vloží věc do batohu, pokud se tam vejde
     * @return true když se věc vloží, false, pokud se nevložila
     * @param objekt třídy Vec
     */
    public boolean vlozVecDoBatohu (Vec vec){
        if (seznamVeci.size() < kapacita && vec.getJePrenositelna()){
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }
    
    /**
     * Dotaz zdali batoh obsahuje danou věc
     */
    public boolean obsahujeVec (Vec vec){
        String jmenoVeci = vec.getNazev();
        return seznamVeci.containsKey(jmenoVeci);
    }
    
    /**
     * Dotaz zdali batoh obsahuje danou věc
     */
    public boolean obsahujeVecNazev (String jmenoVeci){
        if (seznamVeci.containsKey(jmenoVeci)){
            return true;
        }
        return false;
    }
    
    /**
     * Vrátí věc z batohu a vyjme ji z něj
     */
    public Vec vyberVecVBatohu (String jmenoVeci){
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)){
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }
    
    /**
     * Vypíše věci, které jsou v batohu
     */
    public String nazvyVeciVBatohu(){
        String nazvy = "věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }
    
    /**
     * Vypíše kapacitu batohu
     */
    public int getKapacitaBatohu(){
        return kapacita;

    }

    //== Soukromé metody (instancí i třídy) ========================================

}
