package com.github.pehovorka.utekZVezeni;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.pehovorka.utekZVezeni.logika.Prostor;
import com.github.pehovorka.utekZVezeni.logika.Vec;
import com.github.pehovorka.utekZVezeni.logika.Postava;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor("hala", "hala", "vstupní hala budovy VŠE na Jižním městě",0,0);
        Prostor prostor2 = new Prostor("bufet", "bufet", "bufet, kam si můžete zajít na svačinku",0,0);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    /**
     * Testuje zda jde vložit věc do prostoru a následně jí sebrat.
     */
    @Test
    public void testVeci()
    {
        Prostor prostor1 = new Prostor("a", "", "",0,0);
        Vec vec1 = new Vec("A","hezkyNazevVeciA", true, null);
        Vec vec2 = new Vec("B","hezkyNazevVeciB", true, null);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertSame(vec1, prostor1.najdiVec("A"));
        assertNull(prostor1.najdiVec("C"));
        prostor1.odeberVec(vec1);
        assertNull(prostor1.najdiVec("A"));
    }

    /**
     * Testuje vkládání postav do prostoru
     */
    @Test
    public void testPostava()
    {
        Postava postava1 = new Postava("A", "", "", "", "", null, null);
        Prostor prostor1 = new Prostor("prostorA","", "",0,0);
        prostor1.vlozPostavu(postava1);
        assertEquals(true, prostor1.jePostavaVProstoru("A"));
        assertEquals(postava1, prostor1.vratPostavu("A"));
        assertNull(prostor1.vratPostavu("B"));
    }

    /**
     * Testuje zamykání místností a přiřazování klíčů k místnostem
     */
    @Test
    public void testKlic()
    {
        Vec vec1 = new Vec("klíč","Hezký název pro klíč", true, null);
        Prostor prostor1 = new Prostor("prostor","", "",0,0);
        assertEquals(false, prostor1.jeZamceno());
        prostor1.zamknout(true);
        assertEquals(true, prostor1.jeZamceno());
        prostor1.setKlic(vec1);
        assertEquals(vec1, prostor1.getKlic());
    }
}


