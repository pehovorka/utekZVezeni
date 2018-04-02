/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída Dvojice slouží k přenášení dvou odlišných hodnot. Využívá se ve třídě Postava.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class Dvojice
{
    //== Datové atributy (statické i instancí)======================================
    private Vec vracenaVec;
    private String vracenaRec;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  @param vracenaVec věc, kterou postava vrací
     *  @param vracenaRec textový řetězec, který říká postava
     */
    public Dvojice(Vec vracenaVec, String vracenaRec)
    {
        this.vracenaVec = vracenaVec;
        this.vracenaRec = vracenaRec;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vrátí věc, kterou postava vrací
     * 
     * @return věc, kterou dvojice prenáší
     */
    public Vec getVracenaVec()
    {

        return vracenaVec;
    }

    /**
     * Vrací textový řetězec, který vrací postava
     * 
     * @return textový řetězec, který dvojice přenáší
     */
    public String getVracenaRec(){
        return vracenaRec;
    }


}
