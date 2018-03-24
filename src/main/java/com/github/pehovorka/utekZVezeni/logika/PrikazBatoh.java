/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;



/*******************************************************************************
 * Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    
    /***************************************************************************
     *  Konstruktor
     *  @param plan - herní plán ve kterém hráč přenáší batoh
     */
    
    public PrikazBatoh(HerniPlan plan)
    {
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "batoh". Vypíše všechny věci, které jsou v batohu.
     *
     *@param parametry - jako  parametr může být cokoliv
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (plan.getBatoh().getVeci().equals("")) {
            return "Batoh je prázdný";
        }
        else {
            return "V batohu jsou tyto věci: " + plan.getBatoh().getVeci(); 
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}

