package com.github.pehovorka.utekZVezeni;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.pehovorka.utekZVezeni.logika.Hra;
import com.github.pehovorka.utekZVezeni.logika.PrikazKonec;
import com.github.pehovorka.utekZVezeni.logika.PrikazJdi;
import com.github.pehovorka.utekZVezeni.logika.SeznamPrikazu;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
    }

    /***************************************************************************
     * Testuje, zda lze vložit nový příkaz.
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
    }
    /***************************************************************************
     * Testuje, zda jsou příkazy platné.
     */
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    /***************************************************************************
     * Testuje názvy příkazů.
     */
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}
