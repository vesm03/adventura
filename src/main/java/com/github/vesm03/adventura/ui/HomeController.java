package com.github.vesm03.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.vesm03.adventura.logika.Hra;
import com.github.vesm03.adventura.logika.IHra;
import com.github.vesm03.adventura.logika.Prostor;
import com.github.vesm03.adventura.logika.Vec;
import com.github.vesm03.adventura.logika.Postava;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Martin Vesely
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciMistnost;
	@FXML private ListView<Prostor> seznamVychodu;
	@FXML private ListView<Postava> seznamPostav;
	@FXML private ImageView uzivatel;
	@FXML private MenuItem newGame;
	@FXML private MenuItem endGame;
	@FXML private MenuItem help;
	
	@FXML private ImageView bambitka;
	@FXML private ImageView banan;
	@FXML private ImageView klobouk;
	@FXML private ImageView lano;
	@FXML private ImageView nuz;
	@FXML private ImageView kotva;
	@FXML private ImageView lahev;
	@FXML private ImageView maso;
	@FXML private ImageView lebka;
	@FXML private ImageView strelny_prach;
	@FXML private ImageView zlato;
	@FXML private ImageView rum;
	@FXML private ImageView pomeranc;
	@FXML private ImageView klic;
	@FXML private ImageView rezavy_klic;
	@FXML private ImageView tajny_klic;
	
	private IHra hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
	}
	
	/**
	 * začne novou hru od začátku
	 */
	@FXML public void setNewGame() {
		vstupniText.setDisable(false);
		this.inicializuj(new Hra());
	}
	
	/**
	 * ukončí aktuálně hranou hru
	 */
	@FXML public void setEndGame() {
		hra.setKonecHry(true);
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
	}
	
	@FXML public void displayHelp() {
		Stage stage = new Stage();
        stage.setTitle("Nápověda k aplikaci");
        WebView webview = new WebView();
        webview.getEngine().load(HomeController.class.getResource("help.htm").toExternalForm());
        stage.setScene(new Scene(webview, 500, 500));
        stage.show();
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostav.getItems().clear();
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		this.hra = hra;
		
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		
		hra.getHerniPlan().addObserver(this);
		hra.getBatoh().addObserver(this);
		for (int i = 0; i < hra.getHerniPlan().getProstory().length; i++) {
			hra.getHerniPlan().getProstory()[i].addObserver(this);
		}
		
		bambitka.setVisible(false);
		banan.setVisible(false);
		klobouk.setVisible(false);
		lano.setVisible(false);
		nuz.setVisible(false);
		kotva.setVisible(false);
		lahev.setVisible(false);
		maso.setVisible(false);
		lebka.setVisible(false);
		strelny_prach.setVisible(false);
		zlato.setVisible(false);
		rum.setVisible(false);
		pomeranc.setVisible(false);
		klic.setVisible(false);
		rezavy_klic.setVisible(false);
		tajny_klic.setVisible(false);
	}

	/**
	 * Metoda bude soužit pro aktualizaci grafických prvků při změně
	 * ve hře (sebrání věci, přechod do prostoru apod.
	 * @param Observable a Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostav.getItems().clear();
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		if(hra.getBatoh().obsahujeVecNazev("bambitka")) {bambitka.setVisible(true);} else {bambitka.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("banán")) {banan.setVisible(true);} else {banan.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("klobouk")) {klobouk.setVisible(true);} else {klobouk.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("lano")) {lano.setVisible(true);} else {lano.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("nůž")) {nuz.setVisible(true);} else {nuz.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("kotva")) {kotva.setVisible(true);} else {kotva.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("lahev")) {lahev.setVisible(true);} else {lahev.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("maso")) {maso.setVisible(true);} else {maso.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("lebka")) {lebka.setVisible(true);} else {lebka.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("střelný_prach")) {strelny_prach.setVisible(true);} else {strelny_prach.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("zlato")) {zlato.setVisible(true);} else {zlato.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("rum")) {rum.setVisible(true);} else {rum.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("pomeranč")) {pomeranc.setVisible(true);} else {pomeranc.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("klíč")) {klic.setVisible(true);} else {klic.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("rezavý_klíč")) {rezavy_klic.setVisible(true);} else {rezavy_klic.setVisible(false);}
		if(hra.getBatoh().obsahujeVecNazev("tajný_klíč")) {tajny_klic.setVisible(true);} else {tajny_klic.setVisible(false);}
	}

}
