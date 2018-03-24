/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída Postava popisuje jednotlivé postavy, které se objevují ve hře.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String proslov;
    private String mluvPo;
    private Vec coChce;
    private Vec coMa;
    private String recNechce;
    private String recChce;
    private boolean probehlaVymena=false;
    private boolean viditelna = true;
    private Postava zviditelnovanaPostava = null;
    private Vec zprohledatelnovanaVec = null;

    //== Konstruktory a tovární metody =============================================

    /**
     * Vytvoření postav včetně jejich vlastností
     *
     * @param jmeno - jednoznačné jméno postavy
     * @param proslov - text, který se vypíše po prvním zadání příkazu "mluv"
     * @param recNechce - text, který se vypíše pokud postavě dáme věc příkazem "dej", kterou nechce
     * @param recChce - text, který se vypíše pokud postavě dáme věc příkazem "dej", kterou chce
     * @param mluvPo - text, který se vypíše po úspěšné výměně věcí příkazem "dej", pokud použijeme příkaz "mluv"
     * @param coChce - věc, kterou daná postava přijme při úspěšné výměně pomocí příkazu "dej"
     * @param coMa - věc, kterou daná postava dá hráči do batohu po úspěšné výměně pomocí příkazu "dej"
     * 
     */
    public Postava(String jmeno, String proslov, String recNechce, String recChce, String mluvPo, Vec coChce, Vec coMa)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.recNechce = recNechce;
        this.recChce = recChce;
        this.mluvPo = mluvPo;
        this.probehlaVymena = probehlaVymena;
        this.coChce = coChce;
        this.coMa = coMa;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací jméno postavy
     *
     * @return jméno postavy
     */
    public String getJmeno() {
        return jmeno;       
    }
    
    /**
     * Metoda vrací řeč postavy po zadání příkazu "mluv"
     *
     * @return řeč postavy v závislosti na tom, zda již proběhla výměna věcí pomocí příkazu "dej"
     */
    public String getMluv()
    {
        if (probehlaVymena) {
            return mluvPo;
        }
        else {
            return proslov;
        }
    }
    
    /**
     * Metoda vrací věc, kterou postava chce při zadání příkazu "dej"
     *
     * @return věc, kterou postava chce
     */
    public Vec getCoChce() {
        return coChce;
    }
    
    /**
     * Metoda vrací věc, kterou daná postava dá hráči do batohu po úspěšné výměně pomocí příkazu "dej"
     *
     * @return věc, kterou postava dá při výměně
     */
    public Vec getCoMa() {
        return coMa;
    }
    
    /**
     * Metoda vrací, zda již proběhla výměna věcí pomocí příkazu "dej"
     *
     * @return booleanovská hodnota, zda již proběhla výměna věcí
     */
    public boolean probehlaVymena(){
        return probehlaVymena;
    }
    
    /**
     * Metoda vrací řeč postavy, pokud chce věc dávanou příkazem "dej"
     *
     * @return jméno postavy a řeč, pokud chce dávanou věc
     */
    public String getChci() {
        {return jmeno+": "+recChce;}
    }
    
    /**
     * Metoda vrací řeč postavy, pokud nechce věc dávanou příkazem "dej"
     *
     * @return jméno postavy a řeč, pokud nechce dávanou věc
     */
    public String getNechci() {
        {return jmeno+": "+recNechce;}
    }
    
    /**
     * Metoda vykoná výměnu předmětů s druhou postavou.
     *
     * @param věc, kterou hráč nabízí postavě
     * @return dvojice - věc, kterou postava dá hráči a proslov, který přednese
     */
    public Dvojice vymena(Vec nabidka){
         if(nabidka.equals(coChce)){
            probehlaVymena = true;
            return new Dvojice(coMa, getChci());
            
        }
        else{
            return new Dvojice(null, getNechci());
            
        }
    }
    
     /**
     * Nastaví jinou postavu, kterou může postava zviditelnit
     * 
     * @param postava, ktorou zviditelní postava
     */
    public void setZviditelnovanaPostava(Postava zviditelnovana){
        this.zviditelnovanaPostava = zviditelnovana;
    }
    
    /**
     * Vrátí jinou postavu, kterou může postava zviditelnit
     * 
     * @return postava,kterou postava může zviditelnit
     */
    public Postava getZviditelnovanaPostava(){
        return zviditelnovanaPostava;
    }
    
     /**
     * Nastaví věc, kterou může postava učinit prohledatelnou
     * 
     * @param věc, ktorou zprohledatelní postava
     */
    public void setZprohledatelnovanaVec(Vec prohledavatelna){
        this.zprohledatelnovanaVec = prohledavatelna;
    }
    
    /**
     * Vrátí věc, kterou může postava učinit peohledatelnou
     * 
     * @return věc, kterou postava může učinit prohledatelnou
     */
    public Vec getZprohledatelnovanaVec(){
        return zprohledatelnovanaVec;
    }
    
    /**
     * Vrací údaj o tom, zda je postava viditelná
     * 
     * @return booleanovská hodnota, zda je postava viditelná
     */
    public boolean jeViditelna() 
    {
        return viditelna;
    }
    
    /**
     * Nastavuje viditelnost postavy
     * 
     * @param booleanovská hodnota, zda má být viditelná
     */
    public void setViditelna (boolean viditelna) 
    {
        this.viditelna = viditelna;
    }
   
}
