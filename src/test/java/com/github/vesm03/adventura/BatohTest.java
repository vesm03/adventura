/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.vesm03.adventura.logika.Batoh;
import com.github.vesm03.adventura.logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Martin Veselý
 * @version   28. 12. 2016
 */
public class BatohTest
{
    private Vec vec1;
    private Vec vec2;
    private Vec vec3;
    private Vec vec4;
    private Batoh batoh1;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1 = new Vec("lahev", true);
        vec2 = new Vec("lano", true);
        vec3 = new Vec("rum", true);
        vec4 = new Vec("stůl", false);
        batoh1 = new Batoh();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Testuje metody vlozVecDoBatohu a obsahujeVecNazev
     */
    @Test
    public void testVlozVecDoBatohu()
    {
        assertEquals(false, batoh1.vlozVecDoBatohu(vec4));
        assertEquals(false, batoh1.obsahujeVecNazev("stůl"));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec1));
        assertEquals(true, batoh1.obsahujeVecNazev("lahev"));
        assertEquals(false, batoh1.vlozVecDoBatohu(vec2));
        assertEquals(false, batoh1.obsahujeVecNazev("lano"));
        
    }
    

    /**
     * Testuje metodu vlozVecDoBatohu
     */
    @Test
    public void testVyberVec()
    {
        assertEquals(true, batoh1.vlozVecDoBatohu(vec1));
        assertEquals(vec1, batoh1.vyberVecVBatohu("lahev"));
        assertEquals(null, batoh1.vyberVecVBatohu("klobouk"));
       
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
