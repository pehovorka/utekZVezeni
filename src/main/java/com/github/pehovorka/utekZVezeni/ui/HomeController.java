package com.github.pehovorka.utekZVezeni.ui;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.MenuItem;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou a logikou adventury
 * 
 * @author Filip Vencovsky, Petr Hovorka
 *
 */
public class HomeController extends GridPane implements Observer {

	@FXML
	private TextField textVstup;
	@FXML
	private TextArea textVypis;
	@FXML
	private Button odesli;
	@FXML
	private Button prohledatMistnost;
	@FXML
	private ListView<Prostor> seznamMistnosti;
	@FXML
	private ListView<String> seznamVeci;
	@FXML
	private ListView<Postava> seznamPostav;
	@FXML
	private ListView<String> seznamBatoh;
	@FXML
	private ComboBox<Postava> seznamPostavDej;
	@FXML
	private ComboBox<Vec> seznamVeciDej;
	@FXML
	private ToggleGroup prostoryGroup;
	@FXML
	private ImageView uzivatel;
	@FXML
	private Button dej;
	@FXML
	private MenuItem novaHra;
	@FXML
	private MenuItem konecHry;
	@FXML
	private MenuItem zavrit;
	@FXML
	private URL napoveda;
	private IHra hra;

	private ObservableList<String> batohObservableList = FXCollections.observableArrayList();
	private ObservableList<String> veciObservableList = FXCollections.observableArrayList();

	/**
	 * Metoda pro zpracování příkazů zadávané kliknutím na prvky v GUI.
	 * 
	 * @param text
	 *            příkaz pro zpracování
	 */
	public void vypis(String text) {
		String odpoved = "\n--------\n" + text;
		textVypis.appendText(odpoved);
		textVstup.setText("");
		if (hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
			prohledatMistnost.setDisable(true);
			seznamMistnosti.setDisable(true);
			seznamVeci.setDisable(true);
			seznamPostav.setDisable(true);
			seznamBatoh.setDisable(true);
			seznamPostavDej.setDisable(true);
			seznamVeciDej.setDisable(true);
			dej.setDisable(true);

		}
	}

	/**
	 * Metoda čte příkaz ze vstupního textového pole a zpracuje ho...
	 */
	public void odesliPrikaz() {
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n" + textVstup.getText() + "\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");

		if (hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
			prohledatMistnost.setDisable(true);
		}
	}

	/**
	 * Metoda pro zpracování kliku v seznamu prostorů
	 * 
	 * @param klik
	 *            – kliknutí na prvek v seznamu prostorů
	 */
	@FXML
	public void klikMistnost(MouseEvent klik) {

		if (seznamMistnosti.getSelectionModel().isEmpty()) {
		} else {

			if (prostoryGroup.getSelectedToggle().getUserData().toString().equals("Jdi")) {
				vypis(hra.zpracujPrikaz("jdi " + seznamMistnosti.getSelectionModel().getSelectedItem().getNazev()));
			} else {
				vypis(hra.zpracujPrikaz("odemkni " + seznamMistnosti.getSelectionModel().getSelectedItem().getNazev()));
			}
		}
	}

	/**
	 * Metoda pro zpracování kliku v seznamu věcí
	 * 
	 * @param klik
	 *            – kliknutí na prvek v seznamu věcí
	 */
	@FXML
	public void klikVec(MouseEvent klik) {
		if (seznamVeci.getSelectionModel().isEmpty()) {
		} else {

			vypis(hra.zpracujPrikaz("seber " + seznamVeci.getSelectionModel().getSelectedItem()));
		}
	}

	/**
	 * Metoda pro zpracování kliku v seznamu postav
	 * 
	 * @param klik
	 *            – kliknutí na prvek v seznamu postav
	 */
	@FXML
	public void klikPostava(MouseEvent klik) {
		if (seznamPostav.getSelectionModel().isEmpty()) {
		} else {

			vypis(hra.zpracujPrikaz("mluv " + seznamPostav.getSelectionModel().getSelectedItem()));
		}
	}

	/**
	 * Metoda pro zpracování kliku v seznamu batohu
	 * 
	 * @param klik
	 *            – kliknutí na prvek v seznamu batohu
	 */
	@FXML
	public void klikBatoh(MouseEvent klik) {
		if (seznamBatoh.getSelectionModel().isEmpty()) {
		} else {
			String nazev = seznamBatoh.getSelectionModel().getSelectedItem();
			String hezkyNazev = hra.getHerniPlan().getBatoh().getVec(nazev).getHezkyNazev();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Odhození předmětu");
			alert.setHeaderText("Opravdu chcete odhodit " + hezkyNazev + " z batohu?");

			ButtonType buttonAno = new ButtonType("Ano", ButtonData.YES);
			ButtonType buttonNe = new ButtonType("Ne", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonAno, buttonNe);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonAno) {
				vypis(hra.zpracujPrikaz("odhoď " + hra.getHerniPlan().getBatoh().getVec(nazev).getNazev()));
			} else {
			}
		}
	}

	/**
	 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
	 * 
	 */
	@FXML
	public void klikProhledatMistnost() {
		vypis(hra.zpracujPrikaz("prohledatMístnost"));
	}

	/**
	 * Metoda pro zpracování kliku na tlačítko „Prohledat místnost“
	 * 
	 */
	@FXML
	public void klikDej() {
		if (seznamPostavDej.getSelectionModel().isEmpty() || seznamVeciDej.getSelectionModel().isEmpty()) {
		} else {
			vypis(hra.zpracujPrikaz("dej " + seznamPostavDej.getSelectionModel().getSelectedItem() + " "
					+ seznamVeciDej.getSelectionModel().getSelectedItem().getNazev()));
		}
	}

	/**
	 * Metoda pro zpracování příkazu nová hra v menu
	 * 
	 */
	@FXML
	public void klikNovaHra() {

		hra.getHerniPlan().novyBatoh();
		hra = new Hra();
		textVypis.clear();
		textVypis.appendText(hra.vratUvitani());
		textVstup.setDisable(false);
		odesli.setDisable(false);
		prohledatMistnost.setDisable(false);
		seznamMistnosti.setDisable(false);
		seznamVeci.setDisable(false);
		seznamPostav.setDisable(false);
		seznamBatoh.setDisable(false);
		seznamPostavDej.setDisable(false);
		seznamVeciDej.setDisable(false);
		dej.setDisable(false);
		inicializuj(hra);
	}

	/**
	 * Metoda pro zpracování příkazu konec hry v menu
	 * 
	 */
	@FXML
	public void klikKonecHry() {
		vypis(hra.zpracujPrikaz("konec"));

	}

	/**
	 * Metoda pro zpracování příkazu zavřít v menu
	 * 
	 */
	@FXML
	public void klikZavrit() {
		Platform.exit();
	}

	/**
	 * Metoda pro otevření WebView s nápovědou
	 * 
	 */
	@FXML
	public void klikNapoveda() {
		napoveda = this.getClass().getResource("/napoveda.html");
		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();
		engine.load(napoveda.toString());
		Scene scene = new Scene(webView);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		stage.setWidth(1024);
		stage.setHeight(720);
		stage.setMaxWidth(1600);
		stage.setMaxHeight(800);
		stage.setMinWidth(600);
		stage.setTitle("Útěk z vězení – nápověda");
	}

	/**
	 * Metoda provede inicializaci grafických prvků hry
	 * 
	 * @param hra
	 *            aktuální hra
	 * 
	 */
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		textVypis.setEditable(false);
		seznamVeciDej.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getViditelnePostavy());
		seznamPostavDej.getItems().addAll(hra.getHerniPlan().getPuvodniProstor().getViditelnePostavy());
		uzivatel.setX(hra.getHerniPlan().getPuvodniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getPuvodniProstor().getY());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getPuvodniProstor().addObserver(this);
		hra.getHerniPlan().getAktualniProstor().addObserver(this);
	}

	/**
	 * Metoda pro aktualizaci grafických prvků hry.
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().clear();
		seznamPostav.getItems().clear();
		seznamBatoh.getItems().clear();
		seznamVeciDej.getItems().clear();
		seznamPostavDej.getItems().clear();
		seznamVeciDej.getItems().addAll(hra.getHerniPlan().getBatoh().getSeznamVeci());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		seznamPostavDej.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getViditelnePostavy());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().getAktualniProstor().addObserver(this);

		if (hra.getHerniPlan().getBatoh().getSeznamVeci().isEmpty()) {
		} else {
			for (String nazevVeci : hra.getHerniPlan().getBatoh().getMapaVeci().keySet()) {
				if (hra.getHerniPlan().getBatoh().getVec(nazevVeci).jeViditelnaVBatohu() == false) {
				} else {
					batohObservableList.add(nazevVeci);
					seznamBatoh.setItems(batohObservableList);
					seznamBatoh.setCellFactory(param -> new ListCell<String>() {
						@Override
						public void updateItem(String nazev, boolean empty) {
							super.updateItem(nazev, empty);
							if (empty) {
								setText(null);
								setGraphic(null);
							} else {
								String URL = hra.getHerniPlan().getBatoh().getVec(nazev).getImg();
								Image img = new Image(getClass().getResourceAsStream(URL));
								ImageView imageView = new ImageView();
								imageView.setImage(img);
								setText(nazev);
								setGraphic(imageView);
							}
						}
					});
				}
			}
		}

		if (hra.getHerniPlan().getAktualniProstor().getViditelneVeci().isEmpty()) {
		} else {
			for (String nazevVeci : hra.getHerniPlan().getAktualniProstor().getMapaViditelneVeci().keySet()) {
				veciObservableList.add(nazevVeci);
				seznamVeci.setItems(veciObservableList);
				seznamVeci.setCellFactory(param -> new ListCell<String>() {
					@Override
					public void updateItem(String nazev, boolean empty) {
						super.updateItem(nazev, empty);
						if (empty) {
							setText(null);
							setGraphic(null);
						} else {
							String URL = hra.getHerniPlan().getAktualniProstor().najdiVec(nazev).getImg();
							Image img = new Image(getClass().getResourceAsStream(URL));
							ImageView imageView = new ImageView();
							imageView.setImage(img);
							setText(nazev);
							setGraphic(imageView);
						}
					}
				});
			}
			;

		}

	}
}
