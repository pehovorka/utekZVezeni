package com.github.pehovorka.utekZVezeni.logika;

/*******************************************************************************
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author    Petr Hovorka
 * @version   1.0
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    /**
     *  Konstruktor třídy
     *  @param plan herní plán, ve kterém lze "mluvit" s postavami
     * 
     */    
    public PrikazMluv( HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "mluv". Pokud je daná osoba v prostoru, vypíše se po zavolání 
     *  příkazu mluv její proslov.
     *
     *@param parametry - jako  parametr obsahuje jméno osoby,
     *                         s kterou se má mluvit.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz musí mít právě jeden parametr";
        }
        else {
            String jmeno = parametry[0];
            Prostor aktualniProstor = plan.getAktualniProstor();
            if(aktualniProstor.jePostavaVProstoru(jmeno)){
                Postava mluvimeS = aktualniProstor.vratPostavu(jmeno);
                if (mluvimeS.getZviditelnovanaPostava()!=null && !mluvimeS.getZviditelnovanaPostava().jeViditelna()){ //pokud postava zviditelňuje jinou postavu, tak ji zviditelní po rozhovoru 
                    mluvimeS.getZviditelnovanaPostava().setViditelna(true);
                    return mluvimeS.getMluv();
                }
                if (mluvimeS.getZprohledatelnovanaVec()!=null && !mluvimeS.getZprohledatelnovanaVec().jeProhledatelna()){ //pokud postava zprohledavateňuje věci v prostoru, tak je zprohledavatelní po rozhovoru 
                    mluvimeS.getZprohledatelnovanaVec().setProhledatelnost(true);
                    return mluvimeS.getMluv();
                }
                else{
                    return mluvimeS.getMluv();
                }
            }
            else{
                return "Tato postava zde není.";
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
