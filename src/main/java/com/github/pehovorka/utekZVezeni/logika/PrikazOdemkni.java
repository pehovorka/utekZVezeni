/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazOdemkni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param plan - herní plán ve kterém hráč odemyká prostory
     */
    public PrikazOdemkni(HerniPlan plan)
    {
        this.plan=plan;
    }

    /**
     *  Provádí příkaz "odemkni". Odemkne zamčený prostor.
     *
     *@param parametry - jeden paramter určující odemykaný prostor
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kterou místnost mám odemknout?";  
        }

        String prostor = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);

        if (sousedniProstor == null) {
            return "To se nedá odemknout, takový prostor tu není.";
        }
        if (sousedniProstor.jeZamceno()) {
            if (plan.getBatoh().obsahujeVec(sousedniProstor.getKlic().getNazev())) {
                sousedniProstor.zamknout(false);
                return "Je odemčeno, můžeš jít.";

            }
            else {
                return "Nemáš čím odemknout tuto místnost.";
            }
        }
        else {

            return "Tato místnost je již odemčená.";
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

    //== Nesoukromé metody (instancí i třídy) ======================================

    //== Soukromé metody (instancí i třídy) ========================================

}
