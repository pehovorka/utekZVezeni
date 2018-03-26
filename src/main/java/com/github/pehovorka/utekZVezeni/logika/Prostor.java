package com.github.pehovorka.utekZVezeni.logika;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Observable;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor extends Observable {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map<String, Vec> viditelneVeci;
    private Map<String, Postava> postavy;
    private boolean zamceno=false;
    private Vec klic;
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap <> ();
    }
    

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
        + popisVychodu()  + ".\n" + popisVeci() + ".\n" + popisPostav()+".";
    }

    /**
     *  Metoda nastavuje prostor na zamčený a naopak
     *  
     *  @param zamceno - true pokud má být zamčen
     */
    public void zamknout(boolean zamceno)
    {
        this.zamceno = zamceno;
    }

    /**
     *  Metoda vrací, zda je prostor zamčený.
     *  
     *  @return true - je zamčen
     */
    public boolean jeZamceno()
    {
        return zamceno;
    }

    /**
     * Metoda vrací klíč přiřazený určitému prostoru
     *
     * @return klíč
     */
    public Vec getKlic() {
        return klic;
    }

    /**
     * Metoda nastaví klíč zamčenému prostoru
     * 
     * @param klic - věc, která má sloužit, jako klíč k zamčenému prostoru
     */
    public void setKlic(Vec klic) {
        this.klic = klic;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        if (vychody.isEmpty()){
            vracenyText = "Odsud už se nedostanete, není tu žádný východ";}
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamceno()) {
                vracenyText += "(zamknuto)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje věci v místnosti, například:
     * "věci: oběd ".
     *
     * @return Popis věcí v prostoru
     */
    private String popisVeci() {     
        String vracenyText = "věci:";
        if (veci.isEmpty()){
            vracenyText +=" žádné";}
        else{
            for (String nazev:veci.keySet()) {
                if ((veci.get(nazev)).jeViditelna()){
                    vracenyText += " " + nazev;}
            }}
        return vracenyText;
            }
    
    

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vkládá věci do prostoru.
     * 
     * @param neco - vkládaná věc
     */
    public void vlozVec(Vec neco){
        veci.put(neco.getNazev(), neco);
        this.setChanged();
        this.notifyObservers();
    } 

    /**
     * Vrací věc, jejíž název je zadán jako parametr.
     * 
     * @param nazevVeci Název věci
     * @return Věc, která odpovídá zadanému názvu, nebo hodnota null
     */
    public Vec najdiVec(String nazevVeci){
        return veci.get(nazevVeci);
    }

    /**
     * Odebírá věci z prostoru.
     * 
     * @param neco - odebíraná věc
     */

    public void odeberVec(Vec neco){
        veci.remove(neco.getNazev(), neco);
        this.setChanged();
        this.notifyObservers();
    }
    


    /**
     * Vrací viditelné věci v prostoru. 
     * 
        * @return Kolekce věcí v prostoru
     */
    public Collection<Vec> getViditelneVeci() {
        viditelneVeci = new HashMap<>();
    	for (String nazev:veci.keySet()) {
            if ((veci.get(nazev)).jeViditelna()){
               viditelneVeci.put(nazev,veci.get(nazev));}
        };
        return Collections.unmodifiableCollection(viditelneVeci.values());
    }

    
    /**
     * Vrací názvy skrytých věcí v prostoru. Používá se po zadání příkazu prohledatMístnost. Nastavuje viditelnost skrytých věcí v místnosti na true.
     * 
     * @return skryteVeci - textový řetezec skrytých věcí (odděleny mezerou)
     */
    public String getSkryteVeci() {
        String skryteVeci = "";
        for (String nazev : veci.keySet()) {
            if(!veci.get(nazev).jeViditelna() && veci.get(nazev).jeProhledatelna()) {
                veci.get(nazev).setViditelnost(true);
                skryteVeci += nazev + " ";
            }
        }
        this.setChanged();
        this.notifyObservers();
        return skryteVeci;
    }

    /**
     * Vrací textový řetězec, který popisuje postavy v prostoru, například:
     * "postavy: Andrej ".
     *
     * @return Popis postav v prostoru
     */   
    private String popisPostav() {
        String vracenyText = "postavy:";
        if (postavy.isEmpty()){
            vracenyText +=" žádné";}
        else{
            for (String jmeno:postavy.keySet()) {
                if ((postavy.get(jmeno)).jeViditelna()){
                    vracenyText += " " + jmeno;}
                else{vracenyText +=" žádné";}
            }}
        return vracenyText;
    }

    /**
     * Vkládá postavu do prostoru
     * 
     * @param p – vkládaná postava 
     */
    public void vlozPostavu(Postava p) {
        postavy.put(p.getJmeno(), p);
    }

    /**
     * Vrací údaj o tom, zda je postava v prostoru
     * 
     * @param jmeno zjišťované postavy
     * @return booleanovská hodnota, zda je postava v prostoru
     */
    public boolean jePostavaVProstoru(String jmeno){
        return postavy.containsKey(jmeno);

    }

    /**
     * Vrací údaj o tom, zda je věc v prostoru
     * 
     * @param nazev zjišťované věci
     * @return booleanovská hodnota, zda je postava v prostoru
     */
    public boolean jeVecVProstoru(String nazev){
        return veci.containsKey(nazev);

    }

    /**
     * Vrací postavu v prostoru podle jména
     * 
     * @param jmeno postavy
     * @return postava podle jména
     */
    public Postava vratPostavu(String jmeno){

        return postavy.get(jmeno);

    }
    @Override
    public String toString() {
    	return getNazev();
    }
}
