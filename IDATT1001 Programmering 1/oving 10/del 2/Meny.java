package øving10del2;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * øving10del2.Meny klassen her lagres flere retter og en meny kan oppretes
 */
public class Meny {
    ArrayList<rett> retter=new ArrayList<rett>();
   ArrayList<rett> meny = new ArrayList<rett>();

    /**
     * Metode for å legge til en øving10del2.rett i listen retter
     * @param type er typen øving10del2.rett altså forrett, etc
     * @param oppskrift er oppskriften til retten.
     * @param pris er prisen til den bestemte retten
     * @param navn er nvanet på retten
     * @return true eller false etter som om en øving10del2.rett har blitt opprettet eller ikke
     */
    public boolean nyRett(String type, String oppskrift, int pris, String navn) {
        //hvis det blir skrevet inn en type, en oppskrift, en pris, og ett navn så blir det laget et øving10del2.rett objekt
        //som blir lagt til i listen retter og true rettuneres til brukeren
        if(retter.add(new rett(type, oppskrift, pris, navn))){
            return true;
        }
        //hvis det ikke blir laget noe øving10del2.rett objekt så retuneres det false til brukeren
        return false;
    }

    /**
     * Metode for å finne retter etter en innskrevet type
     * @param type typen metoden skal lette etter
     * @return alle retter med den innskrevede typen i form av sring
     */
    public String hentRettType(String type) {
        //lager en string hvor jeg kan samle alle rettene som blir funnet
        String rettene = "";
        //løper gjennom listen retter og letter etter alle retter som har den innskrevede typen
        //og legger den til i rettene stringen
        for (int i = 0; i < retter.size(); i++) {
            if (type.equals(retter.get(i).getType())) {
                rettene += retter.get(i).toString();
            }
        }
        //retunerer alle retter som har blitt funnet
        return rettene;
    }

    /**
     * metode for å finne en øving10del2.rett etter et gitt navn
     * @param navn navnet på retten som skal lettes etter
     * @return hvis retten er funnet så retuneres den i form av string
     */
    public String hentRettNavn(String navn){
        //lager en string hvor jeg kan samle alle retten som blir funnet
        String rett = "";
        //løper gjennom listen retter og letter etter retten som har det innskrevede navnet
        //og hvis navnet finnes så blir det lagt til i øving10del2.rett stringen
        for(int i=0;i<retter.size(); i++){
            if(navn.equals(retter.get(i).getNavn())){
                rett += retter.get(i).toString();
            }
        }
        //retunerer retten som ble funnet i form av string
        return  rett;
    }

    /**
     * metode for å hente retter listen
     * @return listen retter
     */
    public ArrayList<rett> getRetter() {

        return retter;
    }

    /**
     * metode for å finne total prisen til en meny
     * @return total prisen til en meny lagret i meny lista
     */
    public int totalPris(){
        // lager en variabel som kan samle alle prisene til rettene i en meny
        int sum = 0;
        //løper gjennom meny listen og legger til alle prisene til rettene i sum variabelen
        for (int i = 0; i < meny.size(); i++) {
            sum += meny.get(i).getPris();
        }
        //retunerer total prisen til menyen i form av int
        return sum;
    }

    /**
     * metode for å legge til en ny øving10del2.rett i en meny
     * @param nyrett ett øving10del2.rett objekt som skal inni menyen
     */
    public void nyMeny(rett nyrett) {
        //legger til en øving10del2.rett i meny listen
        meny.add(nyrett);
    }

    /**
     * metode for å hente meny listen
     * @return meny listen
     */
    public ArrayList<rett> getMeny() {
        //retunerer meny listen til brukeren
        return meny;
    }

    @Override
    public String toString() {
        //to string som gjør om alle objektene i listene meny og retter til string
        //som kan retuneres til brukeren
        return "øving10del2.Meny{" +
                "retter=" + retter +
                ", meny=" + meny +
                '}';
    }
}
