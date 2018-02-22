/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.vesm03.adventura.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Martin Veselý
 * @version 28. 12. 2016
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veciVProstoru;
    private boolean zamceny;
    private String klic;
    private Map<String, Postava> seznamPostav;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * * @param zamceny Definuje, zdali je prostor zamčený.
     */
    public Prostor(String nazev, String popis, boolean zamceny) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veciVProstoru = new HashMap<>();
        this.zamceny = zamceny;
        seznamPostav = new HashMap<String, Postava>();
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
     * Umožňuje zamknout/odemknout prostor,
     
     * @param zamceno Hodnota true/false pro zamčení/odemčení prostoru.
     *
     */
    public void zamknout(boolean zamceno){
        this.zamceny = zamceno;
    }
    
    /**
     * getter pro hodnotu zamčení prostoru.
     
     * @return true/false.
     *
     */
    public boolean jeZamceno(){
        return zamceny;
    }
    
    /**
     * Umožňuje znastavit klíč k prostoru.
     
     * @param klic Určuje název klíče k danému prostoru.
     *
     */
    public void nastavKlic(String klic){
          this.klic = klic;
    }
    
    /**
     * getter pro hodnotu klíče.
     
     * @return název klíče.
     *
     */
    public String getKlic(){
          return klic;
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
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + nazvyPostav();
    }
    
    /**
     * Metoda vloží postavu do prostoru.
     */

    public void vlozPostavu(Postava postava)
    {
        seznamPostav.put(postava.getJmeno(), postava);
    }
    
    /**
     * Metoda najde postavu.
     */

    public Postava najdiPostavu(String jmeno)
    {
        return seznamPostav.get(jmeno);
    }
    
    /** 
     * Vypíše názvy postav v prostoru
     */
    public String nazvyPostav () {
        String nazvy = "postavy: ";
        for (String jmenoPostavy : seznamPostav.keySet()){
            nazvy += jmenoPostavy + " ";
        }
        return nazvy;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci v prostoru.
     *
     * @return vracenyText - výčet věcí v prostoru
     */
    private String popisVeci() {
        String vracenyText = "věci: ";
        for (String nazev : veciVProstoru.keySet()) {
            vracenyText += " " + nazev;
        }
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
     * Umožňuje vložit věc do prostoru.
     *
     * @return true/false
     */
    public boolean vlozVec(Vec neco){
        veciVProstoru.put(neco.getNazev(), neco);
        return true;
    }
    
    /**
     * Vrací hodnotu podle toho, zdali je zadaná věc v prostoru.
     *
     * @return true/false
     */
    public boolean jeVecVProstoru(String nazev){
        return veciVProstoru.containsKey(nazev);
    }
    
    /**
     * Vrací hodnotu podle toho, zdali se podaří odebrat věc z prostoru.
     *
     * @return true/false
     */
    public Vec odeberVec(String nazev){
        return veciVProstoru.remove(nazev);
    }
}