/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazSeber implements IPrikaz
{
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param plan herní plán
     */
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". 
     *
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám sebrat? Musíš zadat název věci.";
        }

        String nazevSbiraneVeci = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbiranaVec = aktualniProstor.najdiVec(nazevSbiraneVeci);
        Batoh batoh = plan.getBatoh();

        if (sbiranaVec == null || !sbiranaVec.jeViditelna()) {
            return "Taková věc tady není!";
        }
        else {
            if (sbiranaVec.jePrenositelna()){
                if (batoh.isMisto()){
                    aktualniProstor.odeberVec(sbiranaVec);
                    batoh.vlozVec(sbiranaVec);
                    return "Sebral jsi "+ nazevSbiraneVeci;           
                }
                else {
                    return "To se mi nevejde do batohu :( .";  
                }
            }
            else{
                return "Tohle neuzvednu :(.";
            }
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
