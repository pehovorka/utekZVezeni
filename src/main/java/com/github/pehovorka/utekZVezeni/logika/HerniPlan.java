package com.github.pehovorka.utekZVezeni.logika;

import java.util.Observable;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá: vytváří všechny
 * prostory, propojuje je vzájemně pomocí východů a pamatuje si aktuální
 * prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class HerniPlan extends Observable {

	private Prostor aktualniProstor;
	private Prostor viteznyProstor;
	private Prostor prohranyProstor;
	private Prostor puvodniProstor;
	private Vec viteznaVec;
	private Batoh batoh;

	/**
	 * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
	 * Jako výchozí aktuální prostor nastaví halu.
	 */
	public HerniPlan() {
		zalozProstoryHry();
		batoh = new Batoh();

	}

	/**
	 * Vytváří jednotlivé prostory a propojuje je pomocí východů. Jako výchozí
	 * aktuální prostor nastaví jídelnu.
	 */
	private void zalozProstoryHry() {
		// vytvářejí se jednotlivé prostory
		Prostor dvur = new Prostor("dvůr", "dvůr", "vězeňský dvůr", 340, 310);
		Prostor jidelna = new Prostor("jídelna", "jídelna", "vězeňská jídelna - gastronomické nebe", 523, 180);
		Prostor chodbaHlavni = new Prostor("chodba", "hlavní chodba", "hlavní spojovací chodba", 392, 212);
		Prostor chodbaCely = new Prostor("chodbaCely", "chodba u cel", "chodba u cel", 398, 121);
		Prostor vzduchotechnika = new Prostor("vzduchotechnika", "vzduchotechnika", "místnost vzduchotechniky", 535,
				267);
		Prostor toalety = new Prostor("wc", "toalety", "záchody - co to tu páchne?", 226, 281);
		Prostor cela = new Prostor("cela", "moje cela", "moje cela - můj dům", 243, 162);
		Prostor sachta = new Prostor("větracíŠachta", "větrací šachta", "větrací šachta - tady to páchne ještě hůř",
				180, 300);
		Prostor stoka = new Prostor("stoka", "stoka", "stoka - nechci vidět, co tu plave", 115, 280);
		Prostor svoboda = new Prostor("svoboda", "svoboda", "Jsi venku!", 210, 10);

		// vytváření věcí
		Vec obed = new Vec("oběd", "oběd", true, "/ikony/obed.png");
		Vec cigarety = new Vec("cigarety", "cigarety", true, "/ikony/cigarety.png");
		Vec klicVzduchotechnika = new Vec("klíč1", "nějaký klíč", true, "/ikony/klic1.png");
		Vec klicSachta = new Vec("klíč2", "neznámý klíč", true, "/ikony/klic2.png");
		Vec propustka = new Vec("", null, true, null);
		Vec potkan = new Vec("mrtvýPotkan", "mrtvého potkana", true, "/ikony/potkan.png");
		Vec misa = new Vec("záchodováMísa", "záchodová mísa", false, "/ikony/zachod.png");
		Vec zvykacka = new Vec("žvýkačka", "použitou žvýkačku", true, "/ikony/zvykacka.png");
		Vec pacidlo = new Vec("páčidlo", "páčidlo", true, "/ikony/pacidlo.png");

		// postavy – jméno, úvodní proslov, proslov k věci kterou nechce, proslov k věci
		// kterou chce, proslov po výměně, věc kterou chce, věc kterou dá
		Postava fero = new Postava("Fero", "Nazdárek kámo!", "Nic nechci", "", "", null, null);
		Postava lojza = new Postava("Lojza",
				"Čau, taky tě v noci budí ti potkani, co se prohání po celách?\nNějak se poslední dobou rozmnožili...",
				"Nic nechci", "", "", null, null);
		Postava franta = new Postava("Franta",
				"Včera jsem slyšel bachaře, když se bavil s údržbářem.\nVětrací šachta prý vede do stoky, která vytéká ven.\nZajímavý, co?",
				"Nic nechci", "", "", null, null);
		Postava andrej = new Postava("Andrej", "Všetci kradnú. Je to kampaň!", "Co já s tím? Sorry jako!", "", "", null,
				null);
		Postava udrzbar = new Postava("údržbář",
				"Hej ty, chceš cigára? Někde jsem tu ztratil klíč, když ho najdeš, cigarety jsou tvoje.\nA hejbni sebou!",
				"Chci ten klíč, ne tohle!", "To je on. Tady máš ty cigarety a zmiz!", "Co tu ještě chceš?",
				klicVzduchotechnika, cigarety);
		Postava bezdomovec = new Postava("bezdomovec",
				"Mám tě! Že ty zdrháš z basy?\nCo mi dáš za to, že tě neprásknu?\nCo třeba cigarety, máš?",
				"To nejsou cigarety!\nTo už na mě nezkoušej!", "Máš štěstí, můžeš jít.\nNikdy jsme se neviděli.",
				"Jdi už.", cigarety, propustka);

		// přiřazují se průchody mezi prostory (sousedící prostory)
		jidelna.setVychod(vzduchotechnika);
		jidelna.setVychod(chodbaHlavni);
		chodbaHlavni.setVychod(dvur);
		chodbaHlavni.setVychod(jidelna);
		chodbaHlavni.setVychod(chodbaCely);
		chodbaHlavni.setVychod(toalety);
		dvur.setVychod(chodbaHlavni);
		chodbaCely.setVychod(cela);
		chodbaCely.setVychod(chodbaHlavni);
		vzduchotechnika.setVychod(jidelna);
		toalety.setVychod(chodbaHlavni);
		toalety.setVychod(sachta);
		sachta.setVychod(stoka);
		sachta.setVychod(toalety);
		stoka.setVychod(svoboda);

		// vkládání věcí do prostorů
		jidelna.vlozVec(obed);
		vzduchotechnika.vlozVec(potkan);
		vzduchotechnika.vlozVec(klicSachta);
		chodbaCely.vlozVec(klicVzduchotechnika);
		toalety.vlozVec(misa);
		jidelna.vlozVec(zvykacka);
		dvur.vlozVec(pacidlo);

		// nastavování viditelností a prohledávatelností věcí
		potkan.setViditelnost(false);
		potkan.setProhledatelnost(true);
		klicVzduchotechnika.setViditelnost(false);
		klicSachta.setViditelnost(false);
		klicSachta.setProhledatelnost(true);
		zvykacka.setViditelnost(false);
		zvykacka.setProhledatelnost(true);
		pacidlo.setViditelnost(false);
		pacidlo.setProhledatelnost(true);

		// přiřazení klíčů k prostorům
		vzduchotechnika.setKlic(klicVzduchotechnika);
		sachta.setKlic(klicSachta);
		stoka.setKlic(pacidlo);

		// vkládání postav do prostorů
		dvur.vlozPostavu(lojza);
		dvur.vlozPostavu(andrej);
		dvur.vlozPostavu(fero);
		dvur.vlozPostavu(franta);
		chodbaHlavni.vlozPostavu(udrzbar);
		stoka.vlozPostavu(bezdomovec);

		// nastavování viditelnosti postav, postav které jiná postava zviditelňuje a
		// věcí, které postava zprohledatelňuje
		udrzbar.setViditelna(false);
		franta.setZviditelnovanaPostava(udrzbar);
		udrzbar.setZprohledatelnovanaVec(klicVzduchotechnika);

		// zamykání prostorů
		vzduchotechnika.zamknout(true);
		sachta.zamknout(true);
		stoka.zamknout(true);

		// nastavování viditelnosti věcí v batohu
		propustka.setViditelnostVBatohu(false);

		// definice prostorů a věcí důležitých pro příběh
		aktualniProstor = jidelna; // hra začíná v jídelně
		puvodniProstor = jidelna; // hra začáná v jídelně
		viteznyProstor = svoboda; // hra končí na svobodě
		prohranyProstor = cela; // hra končí neúspěšně, pokud hráč vleze do cely
		viteznaVec = propustka; // hra je vyhrána pokud dá hráč bezdomovci cigarety, ten mu za ní dá Věc
								// propustka (nepráskne ho)
	}

	/**
	 * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
	 *
	 * @return aktuální prostor
	 */

	public Prostor getAktualniProstor() {
		return aktualniProstor;
	}

	/**
	 * Metoda vrací odkaz na původní prostor, používá se při spuštění nové hry z
	 * menu.
	 *
	 * @return původní prostor
	 */

	public Prostor getPuvodniProstor() {
		this.setChanged();
		this.notifyObservers();
		return puvodniProstor;
	}

	/**
	 * Metoda vrací true, pokud hráč vyhrál
	 * 
	 * @return booleanovská hodnota, zda hráč vyhrál
	 */
	public boolean jeVyhra() {
		return aktualniProstor.equals(viteznyProstor) && batoh.obsahujeVec(viteznaVec.getNazev());
	}

	/**
	 * Metoda vrací true, pokud hráč došel do vítězného prostoru, ale nedonesl
	 * cigarety bezdomovci a ten ho 'prásknul'
	 * 
	 * @return booleanovská hodnota, zda hráč nevyhrál, protože nedonesl cigarety
	 *         bezdomovci
	 */
	public boolean neniVyhra() {
		return aktualniProstor.equals(viteznyProstor) && !batoh.obsahujeVec(viteznaVec.getNazev());
	}

	/**
	 * Metoda vrací true, pokud hráč vlezl do cely a tam byl zavřen
	 * 
	 * @return booleanovská hodnota, zda hráč vlezl do své cely a tím prohrál
	 */
	public boolean jeProhra() {
		return aktualniProstor.equals(prohranyProstor);
	}

	/**
	 * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi
	 * prostory
	 *
	 * @param prostor
	 *            nový aktuální prostor
	 */
	public void setAktualniProstor(Prostor prostor) {
		aktualniProstor = prostor;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Metoda vrací odkaz na aktuální batoh
	 *
	 * @return batoh
	 */
	public Batoh getBatoh() {
		return batoh;
	}

	/**
	 * Metoda vytváří batoh
	 *
	 * @return batoh
	 */
	public Batoh novyBatoh() {
		batoh = new Batoh();
		this.setChanged();
		this.notifyObservers();
		return batoh;
	}

}
