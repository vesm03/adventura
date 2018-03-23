package com.github.vesm03.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.vesm03.adventura.logika.Hra;
import com.github.vesm03.adventura.logika.IHra;
import com.github.vesm03.adventura.logika.Prostor;
import com.github.vesm03.adventura.logika.Vec;
import com.github.vesm03.adventura.logika.Postava;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
	
	@FXML public void setNewGame() {
		vstupniText.setDisable(false);
		this.inicializuj(new Hra());
	}
	
	@FXML public void setEndGame() {
		hra.setKonecHry(true);
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
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
		if(hra.getBatoh().obsahujeVecNazev("bambitka")) {bambitka.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("banán")) {banan.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("klobouk")) {klobouk.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("lano")) {lano.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("nůž")) {nuz.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("kotva")) {kotva.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("lahev")) {lahev.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("maso")) {maso.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("lebka")) {lebka.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("střelný_prach")) {strelny_prach.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("zlato")) {zlato.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("rum")) {rum.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("pomeranč")) {pomeranc.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("klíč")) {klic.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("rezavý_klíč")) {rezavy_klic.setVisible(true);}
		if(hra.getBatoh().obsahujeVecNazev("tajný_klíč")) {tajny_klic.setVisible(true);}
	}

}
