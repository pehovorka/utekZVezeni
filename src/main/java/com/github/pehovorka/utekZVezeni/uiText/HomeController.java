package com.github.pehovorka.utekZVezeni.uiText;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import com.github.pehovorka.utekZVezeni.logika.IHra;
import com.github.pehovorka.utekZVezeni.logika.Postava;
import com.github.pehovorka.utekZVezeni.logika.Prostor;
import com.github.pehovorka.utekZVezeni.logika.Vec;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

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
	@FXML private Button prohledatMistnost;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private ListView<Vec> seznamVeci;
	@FXML private ListView<Postava> seznamPostav;
	@FXML private ListView<Vec> seznamBatoh;
	@FXML private ComboBox<Postava> seznamPostavDej;
	@FXML private ComboBox<Vec> seznamVeciDej;
	@FXML private ToggleGroup prostoryGroup;
	@FXML private ImageView uzivatel;
	@FXML private Button dej;
	private IHra hra;
	
	
	public void vypis (String text) {
		String odpoved = "\n--------\n"+text+"\n--------\n";
		textVypis.appendText(odpoved);
		textVstup.setText("");
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
			prohledatMistnost.setDisable(true);
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
			prohledatMistnost.setDisable(true);
		}
	}
		/**
		 * Metoda pro zpracování kliku v seznamu prostorů
		 * 
		 * @param	klik – kliknutí na prvek v seznamu prostorů
		 */
		@FXML public void klikMistnost (MouseEvent klik) {
			
			if (seznamMistnosti.getSelectionModel().isEmpty()) {}
			else {
			
			if (prostoryGroup.getSelectedToggle().getUserData().toString().equals("Jdi")) {
				vypis(hra.zpracujPrikaz("jdi "+seznamMistnosti.getSelectionModel().getSelectedItem()));
				System.out.println(prostoryGroup.getSelectedToggle().getUserData().toString());
			}
			else {vypis(hra.zpracujPrikaz("odemkni "+seznamMistnosti.getSelectionModel().getSelectedItem()));
			System.out.println(prostoryGroup.getSelectedToggle().getUserData().toString());}
		}
		}
		
		/**
		 * Metoda pro zpracování kliku v seznamu věcí
		 * 
		 * @param	klik – kliknutí na prvek v seznamu věcí
		 */
		@FXML public void klikVec (MouseEvent klik) {
			if (seznamVeci.getSelectionModel().isEmpty()) {}
			else {
			
			vypis(hra.zpracujPrikaz("seber "+seznamVeci.getSelectionModel().getSelectedItem()));
		}
		}
		
		/**
		 * Metoda pro zpracování kliku v seznamu postav
		 * 
		 * @param	klik – kliknutí na prvek v seznamu postav
		 */
		@FXML public void klikPostava (MouseEvent klik) {
			if (seznamPostav.getSelectionModel().isEmpty()) {}
			else {
			
			vypis(hra.zpracujPrikaz("mluv "+seznamPostav.getSelectionModel().getSelectedItem()));
		}
		}
		
		/**
		 * Metoda pro zpracování kliku v seznamu batohu
		 * 
		 * @param	klik – kliknutí na prvek v seznamu batohu
		 */
		@FXML public void klikBatoh (MouseEvent klik) {
			//vypis(hra.zpracujPrikaz("odhoď "+seznamBatoh.getSelectionModel().getSelectedItem()));
			if (seznamBatoh.getSelectionModel().isEmpty()) {}
			else {			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Odhození předmětu");
			alert.setHeaderText("Opravdu chcete odhodit "+seznamBatoh.getSelectionModel().getSelectedItem()+" z batohu?");

			ButtonType buttonAno = new ButtonType("Ano", ButtonData.YES);
			ButtonType buttonNe = new ButtonType("Ne", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonAno, buttonNe);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonAno){
				vypis(hra.zpracujPrikaz("odhoď "+seznamBatoh.getSelectionModel().getSelectedItem()));
			} 
			else {}
		}
		}
	
		/**
		 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
		 * 
		 */
		@FXML public void prohledatMistnost() {
			vypis(hra.zpracujPrikaz("prohledatMístnost"));
		}
		
		/**
		 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
		 * 
		 */
		@FXML public void dej() {
			vypis(hra.zpracujPrikaz("dej "+seznamPostavDej.getSelectionModel().getSelectedItem()+" "+seznamVeciDej.getSelectionModel().getSelectedItem()));
		}

	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelneVeci());
		seznamVeciDej.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		seznamPostavDej.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		seznamBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().addObserver(this);
	    hra.getHerniPlan().getBatoh().addObserver(this);
	    hra.getHerniPlan().getAktualniProstor().addObserver(this);
	  }
	
	
	

	@Override
	public void update(Observable o, Object arg) {
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().clear();
		seznamPostav.getItems().clear();
		seznamBatoh.getItems().clear();
		seznamVeciDej.getItems().clear();
		seznamPostavDej.getItems().clear();
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelneVeci());
		seznamVeciDej.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		seznamPostavDej.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		seznamBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
	    uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		System.out.println("Dostal jsem zprávu, updatuji se!");
	    hra.getHerniPlan().getAktualniProstor().addObserver(this);

		
	}

}
