package øving10;

import java.util.ArrayList;
import java.util.Collections;
/**
 * klasse for å sammle arrangementer
 */
public class ArrangementRegister {
    //list er listen som holder alle arrangementer
    ArrayList<Arrangement> list = new ArrayList<Arrangement>();


    /**
     * metode for å legge til arrangement til list listen
     * @param navn navnet til arrangementet
     * @param sted hvor arrangementet skal holdes
     * @param arrangor hvem som arrangerer arrangement
     * @param type hva slags type arrangement
     * @param tid når arrangementet skal holdes
     * @return true eller false etter som om det ble lagt til noe i list
     */
    public boolean nyttArrangement(String navn, String sted, String arrangor, String type, long tid) {
        //hvis det blir skrevet inn ett navn, sted, arangor, type, og dato så blir det laget et
        //arrangement objekt som blir lagt til i listen list og true retunreres til brukeren
        if(list.add(new Arrangement(navn, sted, arrangor, type, tid))) {
            return true;
        }else {
            //hvis det ikke blir skrevet inn riktig tin så blir det retunert false til brukeren
            return false;
        }
    }

    /**
     * metode for å hente alle arrangementer på ett gitt sted
     * @param sted stedet som metoden skal finne arrangemanter på
     * @return string med alle arrangementer på det innskrevede stedet
     */
    public String hentArrangementSted(String sted) {
        //oppreter string som kan samle alle arrangermenter som blir funnet
        String arrangement = "";
        //løper igjennom listen med arrangementer og sjekker om noen av dem er på samme sted
        //som brukeren skrev inn og hvis dette er sant blir arrangementet lagt til i arrangement stringen
        for (int i = 0; i < list.size(); i++) {
            if (sted.equals(list.get(i).getSted())) {
                arrangement += list.get(i).toString();
            }
        }
        //retunerer alle arrangementene som ble funnet i form av en string
        return arrangement;
    }

    /**
     * metode for å hente arrangementer på en gitt dato
     * @param tid datoen som meotden skal finne arrangementer på
     * @return string med alle arrangementer på den gitte datoen
     */
    public String hentArrangementTid(long tid) {
        //oppreter string som kan samle alle arrangermenter som blir funnet
        String arrangement = "";
        //løper gjennom
        for (int i = 0; i < list.size(); i++) {
            if (tid==(list.get(i).getTid())) {
                arrangement += list.get(i).toString();
            }
        }
        return arrangement;
    }

    /**
     * metode for å hente arrangementer mellom et gitt dato intervall
     * @param fra start datoen for intervallet
     * @param til slutt datoen for intervallet
     * @return en string med alle arrangementer innenfor det gitte intervallet
     */
    public String hentArrangementFraTil(long fra, long til) {
        //lager en liste som kan sammle alle arangementene som blir funnet
        ArrayList<Arrangement> sorted = new ArrayList<Arrangement>();
        //kopierer alle arrangementer innenfor det gitte dato intervallet over i sorted fra list
        for (int i = 0; i < list.size(); i++) {
            if (fra < (list.get(i).getTid()) && til > (list.get(i).getTid())) {
                sorted.add(list.get(i));
            }
        }
        //sorterer sorted etter tid
        Collections.sort(sorted, Arrangement.getSortTid);
        //oppreter en string som skal samle alle arrangementene i sorted
        String arrangement = "";
        //løper gjennom sorted og samler alle arrangementene
        for (int i = 0; i < sorted.size(); i++) {
            arrangement += list.get(i).toString();

        }
        //retunerer alle de funnede arrangementene i form av en string
        return arrangement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrangementRegister)) return false;
        ArrangementRegister that = (ArrangementRegister) o;
        return list.equals(that.list);
    }

    /**
     * metode for å storetere arrangementer etter sted tid og type
     * @param valg brukeren skriver inn et valg 1 for sted 2 for tid 3 for type
     * @return retunerer en string med den sorterte listen
     */
    public String  sorterEtter(int valg){
        //lager en liste som kan samle alle arrangementer og sorteres etter valget
        ArrayList<Arrangement> sorted = new ArrayList<Arrangement>();
        //kopierer alt fra list til sorted listen
        for (int i = 0; i < list.size(); i++) {
                sorted.add(list.get(i));

        }
        //string for å samle aller arrangementer
        String arrangement = "";
        //switch for å velge hva som skal bli sorter etter 1 sted 2 tid 3 type
        switch (valg){
            case 1:
                //sorterer etter sted og legger til listen i arrangement stringen
                Collections.sort(sorted,Arrangement.getSortSted);
                for (int i = 0; i < sorted.size(); i++) {
                    arrangement += list.get(i).toString();
                }
                break;
            case 2:
                //sorterer etter tid og legger til listen i arrangement stringen
                Collections.sort(sorted,Arrangement.getSortTid);
                for (int i = 0; i < sorted.size(); i++) {
                    arrangement += list.get(i).toString();
                }
                break;
            case 3:
                //sorterer etter type og legger til listen i arrangement stringen
                Collections.sort(sorted,Arrangement.getSortType);
                for (int i = 0; i < sorted.size(); i++) {
                    arrangement += list.get(i).toString();
                }
                break;

        }
        //retunerer den sorterte listen i form av en string
        return arrangement;
    }

    @Override
    public String toString() {
        //lager en to string for å letter retunerer listene til brukeren
        //og for å ikke måtte retunerere objektene til brukeren
            String alleArrangement = "";
            for (int i = 0; i < list.size(); i++) {
                alleArrangement += list.get(i).toString() + "\n";
            }
            if (alleArrangement.equals("")){
                return "\nDet er ikke registerert noen arrangementer.";
            }
            else return alleArrangement;

    }
}
