/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.pehovorka.utekZVezeni.logika.Batoh;
import com.github.pehovorka.utekZVezeni.logika.Vec;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy Batoh.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class BatohTest
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }


    /**
     * Testuje vkládání věcí do batohu
     *
     */
    @Test
    public void testVkladani()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("nazevVeci","hezkyNazevVeci", true, null);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.obsahujeVec("nazevVeci"));
    }

    /**
     * Testuje odhození věcí do batohu
     *
     */
    @Test
    public void testOdhozeni()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("nazevVeci","hezkyNazevVeci", true, null);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.odlozVec("nazevVeci"));
        assertEquals(false, batoh1.obsahujeVec("nazevVeci"));
    }

    /**
     * Testuje kapacitu batohu
     *
     */
    @Test
    public void testKapacita()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("1","hezkyNazevVeci", true, null);
        Vec vec2 = new Vec("2","hezkyNazevVeci", true, null);
        Vec vec3 = new Vec("3","hezkyNazevVeci", true, null);
        Vec vec4 = new Vec("4","hezkyNazevVeci", true, null);
        Vec vec5 = new Vec("5","hezkyNazevVeci", true, null);
        Vec vec6 = new Vec("6","hezkyNazevVeci", true, null);
        assertEquals(true, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.vlozVec(vec2));
        assertEquals(true, batoh1.vlozVec(vec3));
        assertEquals(true, batoh1.vlozVec(vec4));
        assertEquals(true, batoh1.vlozVec(vec5));
        assertEquals(false, batoh1.vlozVec(vec6));
    }
}



