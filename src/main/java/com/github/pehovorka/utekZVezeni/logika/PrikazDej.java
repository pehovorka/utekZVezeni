/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.logika;

/*******************************************************************************
 * Třída PrikazDej implementuje pro hru příkaz dej.
 *
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazDej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "dej";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor
     *  
     *  @param plan herní plán ve kterém hráč může dávat věci ostatním postavám
     */
    public PrikazDej(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Metoda provádí příkaz "dej". Zkouší dát určité osobě určitý předmět. Pokud tuto věc nemá nebo ji postava nechce,
     * tak vypíše chybovou hlášku.
     * Jinak dá věc příslušné postavě v prostoru.
     *
     * @param parametry - jako paramer obsahuje jméno věci, kterou se pokouší dát!!!!!!!!!!!!!
     * @return zpráva, kterou vypíše hra hráči
     */
    public String provedPrikaz(String... parametry) {
        if (parametry.length <= 1) {
            return "Komu mám co dát? (použij tvar 'dej osoba věc')";  
        }
        Prostor aktualniProstor = plan.getAktualniProstor();
        String jmenoOsoby = parametry[0];
        String nazevVeci = parametry[1];
        Batoh batoh = plan.getBatoh();
        if (batoh.obsahujeVec(nazevVeci)){
            if(aktualniProstor.jePostavaVProstoru(jmenoOsoby)){
                Vec davana = batoh.getVec(nazevVeci);
                Postava obdarovana = aktualniProstor.vratPostavu(jmenoOsoby);
                Dvojice dvojice = obdarovana.vymena(davana);
                Vec vracenaVec = dvojice.getVracenaVec();

                if(vracenaVec!=null){
                    batoh.odlozVec(nazevVeci);
                    batoh.vlozVec(vracenaVec);
                }
                return dvojice.getVracenaRec();
            }
            else{
                return "Tato postava zde není";
            }
        }
        else {
            return "Tuto věc nemáte";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo, které se používá při jejím volání)
     *
     * @return název příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}