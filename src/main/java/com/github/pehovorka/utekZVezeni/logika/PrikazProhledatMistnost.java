/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;


/*******************************************************************************
 * Třída PrikazProhledatMistnost implementuje pro hru příkaz prohledatMístnost.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazProhledatMistnost implements IPrikaz
{
    private static final String NAZEV = "prohledatMístnost";
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param plan herní plán ve kterém hráč může prohledávat místnosti, zda v nich nejsou skryté věci
     */
    public PrikazProhledatMistnost(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "prohledatMístnost". Prohledá se aktuální místnost a vrátí se textový řetězec s nalezenými předměty.
     *
     * @param parametry - pokud se zadá nějaký parametr, vrátí zprávu o tam, že se příkaz používá pez parametrů
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            // pokud zadá i nějaký parametr
            return "Zadej pouze prohledatMístnost bez parametrů.";
        }
        String text;
        String veci = plan.getAktualniProstor().getSkryteVeci();
        if (veci.equals("")){
            text = "nic skrytého tu není";
        }
        else {
            text = "Našel jsi: " + veci;
        }

        return text;
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
