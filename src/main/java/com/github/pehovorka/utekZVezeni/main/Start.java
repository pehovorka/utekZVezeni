/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.main;

import com.github.pehovorka.utekZVezeni.logika.*;
import com.github.pehovorka.utekZVezeni.ui.HomeController;
import com.github.pehovorka.utekZVezeni.ui.TextoveRozhrani;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author Jarmila Pavlíčková
 * @version ZS 2016/2017
 */
public final class Start extends Application {
	/***************************************************************************
	 * Metoda, prostřednictvím níž se spouští celá aplikace.
	 *
	 * @param args
	 *            Parametry příkazového řádku
	 */
	public static void main(String[] args) {

		/*
		 * IHra hra = new Hra(); TextoveRozhrani ui = new TextoveRozhrani(hra); if
		 * (args.length == 0){ ui.hraj();
		 * 
		 * } else{ ui.hrajZeSouboru(new File(args[0])); }
		 */
		if (args.length == 0) {
			launch(args);
		} else {
			if (args[0].equals("-text")) {
				IHra hra = new Hra();
				TextoveRozhrani ui = new TextoveRozhrani(hra);
				ui.hraj();
			} else {
				System.out.println("Neplatný parametr");
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/MainWindow.fxml"));
		Parent root = loader.load();

		HomeController controller = loader.getController();
		IHra hra = new Hra();
		controller.inicializuj(hra);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Útěk z vězení");
		primaryStage.setMinWidth(1024);
		primaryStage.setMinHeight(768);
		primaryStage.setMaxWidth(1600);
		primaryStage.setMaxHeight(1069);
	}
}
