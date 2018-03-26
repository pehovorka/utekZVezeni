package com.github.pehovorka.utekZVezeni.uiText;

import java.util.Observable;
import java.util.Observer;

import com.github.pehovorka.utekZVezeni.logika.IHra;
import com.github.pehovorka.utekZVezeni.logika.Prostor;
import com.github.pehovorka.utekZVezeni.logika.Vec;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky, Petr Hovorka
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private ListView<Vec> seznamVeci;
	private IHra hra;
	
	
	public void vypis (String text) {
		String odpoved = "\n--------\n"+text+"\n--------\n";
		textVypis.appendText(odpoved);
		textVstup.setText("");
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
	}
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
	}
		/**
		 * Metoda pro zpracování kliku v seznamu prostorů
		 * 
		 * @param	klik – kliknutí na prvek v seznamu prostorů
		 */
		@FXML public void klikMistnost (MouseEvent klik) {
			vypis(hra.zpracujPrikaz("jdi "+seznamMistnosti.getSelectionModel().getSelectedItem()));
		}
		
		/**
		 * Metoda pro zpracování kliku v seznamu věcí
		 * 
		 * @param	klik – kliknutí na prvek v seznamu věcí
		 */
		@FXML public void klikVec (MouseEvent klik) {
			vypis(hra.zpracujPrikaz("seber "+seznamVeci.getSelectionModel().getSelectedItem()));
		}
	

	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelneVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		hra.getHerniPlan().addObserver(this);
	    hra.getHerniPlan().getBatoh().addObserver(this);
	    hra.getHerniPlan().getAktualniProstor().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelneVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
	}

}
