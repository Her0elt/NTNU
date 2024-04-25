package EiendomsRegister;

import java.util.ArrayList;

/**
 * Klassen EiendomsRegister.EiendomsRegister
 *
 * @author Hermann Elton
 * @version 1, 2019-10-31
 */
public class EiendomsRegister {
    /**
     *
     */
    private ArrayList<Eiendom> eiendommer = new ArrayList<Eiendom>();

    /**
     * Metode for å legge til ny eiendom kommune nr må vare kommuneNummer>101 && kommuneNummer<5054 og hvis ikke noe bruksnavn må skrives som n/a
     *
     * @param kommuneNummer
     * @param kommuneNavn
     * @param gnr
     * @param bnr
     * @param bruksNavn
     * @param areal
     * @param eierNavn
     * @return, om det er gjort eller ikke
     */
    public boolean nyEiendom(int kommuneNummer, String kommuneNavn, int gnr, int bnr, String bruksNavn, double areal, String eierNavn) {
        try {
            eiendommer.add(new Eiendom(kommuneNummer, kommuneNavn, gnr, bnr, bruksNavn, areal, eierNavn));
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
            return false;
        }
        return true;
    }
    // har derfor også to metoder for å legge til en eiendom fordi det er to konstruktører i Eiendom
    public boolean nyEiendom(int kommuneNummer, String kommuneNavn, int gnr, int bnr, double areal, String eierNavn) {
        //bruker try catch fordi jeg kaster et unntak i konstruktøren av eiendoms klassen
      try {
          eiendommer.add(new Eiendom(kommuneNummer, kommuneNavn, gnr, bnr, areal, eierNavn));
      }catch (IllegalArgumentException iae){
          iae.printStackTrace();
          return false;
      }
      return true;
    }

    /**
     * Metode for å slette en eiendom basert på unikID som er kommunenr-gnr/bnr
     *
     * @param kNr
     * @param gnr
     * @param bnr
     */
    public boolean slettEiendom(int kNr, int gnr, int bnr) {
        // lagrer knr gnr og bnr på unik ID format som samsvarer med formatet til getUnikID metoden i eiendom
        String unikID= kNr + "-" + gnr + "/" + bnr;
        //løper igjennom hele eiendommer og leter etter eiendomen med samme unike id som brukeren skrev inn
        for (int i = 0; i < eiendommer.size(); i++) {
            if (eiendommer.get(i).getUnikID().equals(unikID)) {
                //hvis den unike iden finnes så blir det slettet fra eiendommer listen
                eiendommer.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * henter antall eiendommer som er registerert
     *
     * @return antall eindomer som er registrert
     */
    public double hentAntallEiendomer() {
        // antall eiendommer registrert er lik antall elementer i eiendommer listen
        return eiendommer.size();
    }

    /**
     * henter eidenom etter unikID som er kommunenr-gnr/bnr
     *
     * @param kNr
     * @param gnr
     * @param bnr
     * @return retunerer eiendomm med den unikID-en
     */
    public String hentEindomUnikId(int kNr, int gnr, int bnr) {
        // lagrer knr gnr og bnr på unik ID format som samsvarer med formatet til getUnikID metoden i eiendom
        String unikID= kNr + "-" + gnr + "/" + bnr;
        //lager en string som jeg kan retunere til brukeren med info om eiendommen som ble søkt etter velger dette for å opprettholde komposisjon
        String eiendom = "";
        for (int i = 0; i < eiendommer.size(); i++) {
            //løper igjennom hele eiendommer og leter etter eiendomen med samme unike id som brukeren skrev inn
            if (eiendommer.get(i).getUnikID().equals(unikID)) {
                //hvis den unike iden finnes så blir infoen om eiendomen langt til i stringen eiendom
                eiendom += eiendommer.get(i).toString() + '\n';
            }
        }
        //returenerer funnet
        return eiendom;
    }

    /**
     * @return gjennomsnittet arealet til alle eiendommene som er registrert
     */
    public double snittAreal() {
        //lager en double som jeg kan lagre alle arealene  i
        double alleAreal = 0;
        //løper igjennom hele eiendommer og legger til alle arealene i snittAreal variabelen
        for (int i = 0; i < eiendommer.size(); i++) {
            alleAreal += eiendommer.get(i).getAreal();
        }
        //retunerer alle arealene delt på lengden på eiendomer listen
        return alleAreal / hentAntallEiendomer();
    }

    /**
     * @param gnr
     * @return eiendommer med gitt gnr
     */
    public String  samtligeEiendommer(int gnr) {
        //lager en string som jeg kan retunere til brukeren med info om eiendommen som ble søkt etter velger dette for å opprettholde komposisjon
        String sEiendommer = "";
        //løper igjennom hele eiendommer og legger til alle eiendommer som har samme bnr som det brukeren skrev inn og legger til to stringen til den eiendom in sEiendommer Stringen
        for (int i = 0; i < eiendommer.size(); i++) {
            if (eiendommer.get(i).getKommuneNummer() == gnr) {
                sEiendommer += eiendommer.get(i).toString();
            }
        }
        return sEiendommer;
    }

    /**
     * toString for eidendommer listen
     *
     * @return eiendommer listen
     */
    @Override
    public String toString() {
        String toString = "";
        for (int i = 0; i < eiendommer.size(); i++) {
            toString += eiendommer.get(i).toString() + "\n";

        }
        return toString;
    }
}
