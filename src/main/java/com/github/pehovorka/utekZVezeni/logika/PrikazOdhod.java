/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída PrikazOdhod implementuje pro hru příkaz odhoď.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazOdhod implements IPrikaz
{
    private static final String NAZEV = "odhoď";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán ve kterém hráč může odhazovat věci
     */    
    public PrikazOdhod(HerniPlan plan) {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }

    /**
     *  Provádí příkaz "odhoď", který odhodí věc z batohu do aktuálního prostoru. Pokud v batohu není, tak vypíše chybovou hlášku.
     *  
     *
     *@param parametry - jméno odhazované věci
     *@return zpráva hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název odhazované věci), tak ....
            return "Co mám odhodit? Musíš zadat jméno věci";
        }

        String nazevVeci = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = batoh.getVec(nazevVeci);

        if (vec == null) {
            return "Taková věc v batohu není";            
        }
        else {
            plan.getBatoh().odlozVec(nazevVeci);
            aktualniProstor.vlozVec(vec);
            return "Věc " + nazevVeci + " byla vyhozena z batohu do této místnosti";
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
