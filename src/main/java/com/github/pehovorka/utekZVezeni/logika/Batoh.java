/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;
import java.util.*;


/*******************************************************************************
 * Třída batoh umožňuje vkládat věci do batohu.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class Batoh extends Observable
{
    //== Datové atributy (statické i instancí)======================================
    private Map<String, Vec> seznamVeci;
    private static final int MAX_VECI = 5;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy.
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

     /**
     * Metoda přidá věc do batohu pokud je tam ještě místo
     *
     * @param neco - věc, který se má přidat do batohu
     * @return true pokud se podaří věc přidat
     */
    public boolean vlozVec(Vec neco){
        if (isMisto()){
            seznamVeci.put(neco.getNazev(),neco);
            this.setChanged();
            this.notifyObservers();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Metoda odstraní věc z batohu pokud tam je
     *
     * @param neco - věc kterou chceme odstanit
     * @return true pokud se odstraní
     */
    public boolean odlozVec(String neco) {
        boolean vyhozena = false;
        if (seznamVeci.containsKey(neco)) {
            vyhozena = true;
            seznamVeci.remove(neco);

        }
        return vyhozena;
    }

    /**
     * Metoda zjistí zda je věc v batohu či není
     *
     * @param nazev - nazev hledané věci
     * @return true pokud je v batohu
     */
    public boolean obsahujeVec (String nazev) {
        return (seznamVeci.containsKey(nazev));
    }

    /**
     * Metoda najde věc, kterou chceme získat
     * @return věc
     */
    public Vec getVec(String nazev) {
        return seznamVeci.get(nazev);
    }
    
    /**
     * Metoda vrátí, zda je v batohu místo (porovná maximální počet věcí v batohu s aktuálním počtem věcí v batohu).
     *
     * @return booleanovská hodnota, zda se může přidat ještě další věc
     */
    public boolean isMisto() {
        return (seznamVeci.size() < MAX_VECI);
    }

    /**
     * Metoda pro zjištění obsahu batohu
     *
     * @return seznam věcí v batohu
     */
    public String getVeci() {
        String text = "";
        for (String nazev : seznamVeci.keySet()) {
            text +=  nazev + " ";
        }
        return text;
    }
    
    /**
     * Metoda pro zjištění obsahu batohu
     *
     * @return seznam věcí v batohu
     */
    public Map<String, Vec> getMapaVeci() {
        return seznamVeci;
    }
    
    
    /**
     * Vrací kolekci věcí v batohu. 
     * 
        * @return Kolekce věcí v batohu
     */
    public Collection<Vec> getSeznamVeci() {
        
        return Collections.unmodifiableCollection(seznamVeci.values());
    }
    

}