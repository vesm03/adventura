/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.vesm03.adventura.logika.Prostor;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Martin Veselý
 * @version   28. 12. 2016
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        //fixture
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        //úklid po testu
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", false, 0.0, 0.0);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", false, 0.0, 0.0);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }


    /**
     * Testuje, zda je možné vkládat věco do prostoru a odebírat je z něj.
     */
    @Test
    public void testVeci()
    {
    	com.github.vesm03.adventura.logika.Prostor prostor2 = new com.github.vesm03.adventura.logika.Prostor("a", "a", false, 0.0, 0.0);
    	com.github.vesm03.adventura.logika.Vec vec2 = new com.github.vesm03.adventura.logika.Vec("vec1", true);
        assertEquals(true, prostor2.vlozVec(vec2));
        assertEquals(false, prostor2.jeVecVProstoru("jidlo"));
        assertNull(prostor2.odeberVec("jidlo"));
        assertNotNull(prostor2.odeberVec("vec1"));
    }
}

