package com.github.pehovorka.utekZVezeni.uiText;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import com.github.pehovorka.utekZVezeni.logika.Hra;
import com.github.pehovorka.utekZVezeni.logika.IHra;
import com.github.pehovorka.utekZVezeni.logika.Postava;
import com.github.pehovorka.utekZVezeni.logika.Prostor;
import com.github.pehovorka.utekZVezeni.logika.Vec;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.control.MenuItem;

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
	@FXML private MenuItem novaHra;
	@FXML private MenuItem konecHry;
	@FXML private MenuItem zavrit;
	@FXML private URL napoveda;
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
				vypis(hra.zpracujPrikaz("jdi "+seznamMistnosti.getSelectionModel().getSelectedItem().getNazev()));
				System.out.println(prostoryGroup.getSelectedToggle().getUserData().toString());
			}
			else {vypis(hra.zpracujPrikaz("odemkni "+seznamMistnosti.getSelectionModel().getSelectedItem().getNazev()));
			}
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
			
			vypis(hra.zpracujPrikaz("seber "+seznamVeci.getSelectionModel().getSelectedItem().getNazev()));
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
				vypis(hra.zpracujPrikaz("odhoď "+seznamBatoh.getSelectionModel().getSelectedItem().getNazev()));
			} 
			else {}
		}
		}
	
		/**
		 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
		 * 
		 */
		@FXML public void klikProhledatMistnost() {
			vypis(hra.zpracujPrikaz("prohledatMístnost"));
		}
		
		/**
		 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
		 * 
		 */
		@FXML public void klikDej() {
			vypis(hra.zpracujPrikaz("dej "+seznamPostavDej.getSelectionModel().getSelectedItem()+" "+seznamVeciDej.getSelectionModel().getSelectedItem()));
		}
		
		/**
		 * Metoda pro zpracování příkazu nová hra v menu
		 * 
		 */
		@FXML public void klikNovaHra() {
			
			hra.getHerniPlan().novyBatoh();
			hra = new Hra();
			textVypis.clear();
			textVypis.appendText(hra.vratUvitani());
			textVstup.setDisable(false);
			odesli.setDisable(false);
			prohledatMistnost.setDisable(false);
//			hra.getHerniPlan().setAktualniProstor(hra.getHerniPlan().getPuvodniProstor());
			inicializuj(hra);
		}
		
		/**
		 * Metoda pro zpracování příkazu konec hry v menu
		 * 
		 */
		@FXML public void klikKonecHry() {
			vypis(hra.zpracujPrikaz("konec"));
			
		}
		
		/**
		 * Metoda pro zpracování příkazu zavřít v menu
		 * 
		 */
		@FXML public void klikZavrit() {
			Platform.exit();
		}
		
		/**
		 * Metoda pro otevření WebView s nápovědou
		 * 
		 */
		@FXML public void klikNapoveda() {
			napoveda = this.getClass().getResource("napoveda.html");
			WebView webView = new WebView();
			WebEngine engine = webView.getEngine();
			engine.load(napoveda.toString());
			Scene scene = new Scene(webView);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setWidth (1024);
			stage.setHeight(720);
			stage.setMaxWidth(1600);
			stage.setMaxHeight(800);
			stage.setMinWidth(600);
			stage.setTitle("Útěk z vězení – nápověda");
		}
		

	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getViditelneVeci());
		seznamVeciDej.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getViditelnePostavy());
		seznamPostavDej.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getViditelnePostavy());
		seznamBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		uzivatel.setX(hra.getHerniPlan().getPuvodniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getPuvodniProstor().getY());
		hra.getHerniPlan().addObserver(this);
	    hra.getHerniPlan().getBatoh().addObserver(this);
	    hra.getHerniPlan().getPuvodniProstor().addObserver(this);
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
