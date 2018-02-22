/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.vesm03.adventura.logika.Postava;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PostavaTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class PostavaTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        //fixture
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
        //úklid po testu
    }

    /**
     * Testuje metody getJmeno a getProslov
     */
    @Test
    public void testPostava()
    {
        String[] kostlivec_rec = {"Přines mi rum a možná ti něco poradím", "Rozhlídni se lépe po temné místnosti", "Teď už si poraď sám"};
        Postava postava1 = new Postava("kostlivec", kostlivec_rec);
        assertEquals("kostlivec", postava1.getJmeno());
        assertEquals("Přines mi rum a možná ti něco poradím", postava1.getProslov());
    }
    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
}
