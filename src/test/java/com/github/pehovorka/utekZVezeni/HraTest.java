package com.github.pehovorka.utekZVezeni;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.pehovorka.utekZVezeni.logika.Hra;
/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.

     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("jídelna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertNotNull(hra1.getHerniPlan().getAktualniProstor().najdiVec("oběd"));
        hra1.zpracujPrikaz("seber oběd");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("oběd"));
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().jePostavaVProstoru("údržbář"));
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().vratPostavu("údržbář").jeViditelna());
        hra1.zpracujPrikaz("jdi dvůr");
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().jePostavaVProstoru("Franta"));
        hra1.zpracujPrikaz("mluv Franta");
        hra1.zpracujPrikaz("prohledatMístnost");
        hra1.zpracujPrikaz("seber páčidlo");
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().jePostavaVProstoru("údržbář"));
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().vratPostavu("údržbář").jeViditelna());
        hra1.zpracujPrikaz("mluv údržbář");
        hra1.zpracujPrikaz("jdi chodbaCely");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().najdiVec("klíč1").jeViditelna());
        hra1.zpracujPrikaz("prohledatMístnost");
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().najdiVec("klíč1").jeViditelna());
        hra1.zpracujPrikaz("seber klíč1");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("klíč1"));
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi jídelna");
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("vzduchotechnika").jeZamceno());
        hra1.zpracujPrikaz("odemkni vzduchotechnika");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("vzduchotechnika").jeZamceno());
        hra1.zpracujPrikaz("jdi vzduchotechnika");
        hra1.zpracujPrikaz("prohledatMístnost");
        hra1.zpracujPrikaz("seber klíč2");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("klíč2"));
        hra1.zpracujPrikaz("jdi jídelna");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("dej údržbář klíč2");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("klíč2"));
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("cigarety"));
        hra1.zpracujPrikaz("dej údržbář klíč1");
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("klíč1"));
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("klíč2"));
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("cigarety"));
        hra1.zpracujPrikaz("jdi wc");
        assertEquals("wc", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("větracíŠachta").jeZamceno());
        hra1.zpracujPrikaz("odemkni větracíŠachta");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("větracíŠachta").jeZamceno());
        hra1.zpracujPrikaz("jdi větracíŠachta");
        hra1.zpracujPrikaz("odemkni stoka");
        hra1.zpracujPrikaz("jdi stoka");
        hra1.zpracujPrikaz("mluv bezdomovec");
        hra1.zpracujPrikaz("dej bezdomovec cigarety");
        assertEquals(false, hra1.konecHry());
        assertEquals("stoka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi svoboda");
        assertEquals("svoboda", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra1.konecHry());
        
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
}
