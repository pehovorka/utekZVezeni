/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;



//import java.util.Map;
//import java.util.HashMap;

/*******************************************************************************
 * Třída Vec popisuje jednotlivé věci, které se objevují ve hře.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean viditelnost = true;
    private boolean prohledatelnost = false;
    //private Map<String,Vec> obsahVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  @param nazev - název věci
     *  @param prenositelnost - booleanovská hodnota, zda je věc přenositelná
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        //obsahVeci = new HashMap<String,Vec>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vrací název věci.
     * 
     * @return nazev - název věci
     */
    public String getNazev(){
        return nazev;
    }
    /**
     * Vrací přenositelnost věci.
     * 
     * @return prenostielnost - booleanovská hodnota zda je věc přenostitelná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    /**
     * Vrací viditelnost věci.
     * 
     * @return viditelnost - booleanovská hodnota zda je věc viditelná
     */
    public boolean jeViditelna(){
        return viditelnost;
    }
    /**
     * Vrací prohledatelnost věci.
     * 
     * @return prohledatelnost - booleanovská hodnota zda je věc prohledatelná
     */
    public boolean jeProhledatelna(){
        return prohledatelnost;
    }
    /**
     * Nastavuje viditelnost věci
     * 
     * @param viditelnost – booleanovská hodnota viditelnosti 
     */
    public void setViditelnost(boolean viditelnost){
        this.viditelnost=viditelnost;
    }
    /**
     * Nastavuje prohledatelnost věci
     * 
     * @param prohledatelnost – booleanovská hodnota prohledatelnosti 
     */
    public void setProhledatelnost(boolean prohledatelnost){
        this.prohledatelnost=prohledatelnost;
    }
   
    @Override
    public String toString() {
    	return getNazev();
    }

}
