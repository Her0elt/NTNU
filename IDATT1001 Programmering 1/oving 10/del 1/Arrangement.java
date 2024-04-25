package øving10;

import java.util.Comparator;
/**
 * Klassen for et arrangement
 */
public class Arrangement {
    //klasse variabler
    String navn;
    String sted;
    String arrangor;
    String type;
    long tid;

    /**
     * Konstuktøren til arrangement
     * @param navn navnet til arrangementet
     * @param sted hvor arrangementet skal holdes
     * @param arrangor hvem som arrangerer arrangement
     * @param type hva slags type arrangement
     * @param tid når arrangementet skal holdes
     */
    public Arrangement(String navn, String sted, String arrangor, String type, long tid){
        //gir verider til alle klasse variablene
       this.navn=navn;
       this.sted=sted;
       this.arrangor=arrangor;
       this.type=type;
       this.tid=tid;
    }

    /**
     * Metode for å sammenligne tidene til arrangementer
     */
    public static Comparator<Arrangement> getSortTid = new Comparator<Arrangement>() {
        //lager objekter av arrangement
        public int compare(Arrangement s1, Arrangement s2) {
            //langerer to tider som skal sammenlignes
            long getTid1 = s1.getTid();
            long  getTid2 = s2.getTid();
            //retunerer om tid1 er større eller mindre enn tid2
            return Long.compare(getTid1,getTid2);
        }
    };
    /**
     * Metode for å sammenligne stedene til arrangementer
     */
    public static Comparator<Arrangement> getSortSted = new Comparator<Arrangement>() {
        //lager objekter av arrangement
        public int compare(Arrangement s1, Arrangement s2) {
            //lagere stedene til to arrangementer
            String arrrangementSted1 = s1.getSted().toUpperCase();
            String arrangementSted2 = s2.getSted().toUpperCase();
            //retunerer om arrangement 1 sitt sted kommer før eller etter arrangement 2 sitt sted
            // i alfabetet
            return arrrangementSted1.compareTo(arrangementSted2);
        }
    };
    /**
     * Metode for å sammenligne typene til arrangementer
     */
    public static Comparator<Arrangement> getSortType = new Comparator<Arrangement>() {
        //lager objekter av arrangement
        public int compare(Arrangement s1, Arrangement s2) {
            //lagrer typen til to arrangementer
            String arrrangementType1 = s1.getType().toUpperCase();
            String arrangementType2 = s2.getType().toUpperCase();
            //retunerer om arrangement 1 sin type kommer før eller etter arrangement 2 sin type
            //i alfabetet
            return arrrangementType1.compareTo(arrangementType2);
        }
    };

    /**
     *
     *@return restunerer navnet til arrangement
     */
    public String getNavn() {

        return navn;
    }

    /**
     *
     * @return retunerer stedet til arrangement
     */
    public String getSted() {

        return sted;
    }

    /**
     *
     * @return retunerer arrangoren til arrangement
     */
    public String getArrangor() {

        return arrangor;
    }

    /**
     *
     * @return retunerer hva slags type arrangement det er
     */
    public String getType() {

        return type;
    }

    /**
     *
     * @return retunerer datoen til arrangementet
     */
    public long getTid() {

        return tid;
    }

    @Override
    public String toString() {
        // to string for å skrive ut alle klasse variablene til et Arrangement objekt
            return
                    "\nNavn: "+ navn +
                    "\nSted: " + sted +
                    "\nArrangør: " + arrangor +
                    "\nType: " + type +
                    "\nTidspunkt: kl." + String.valueOf(getTid()).substring(8,10) + ":" +
                    String.valueOf(getTid()).substring(10,12) + "  Dato: " +
                    String.valueOf(getTid()).substring(6,8) + "." +
                    String.valueOf(getTid()).substring(4,6) + "." +
                    String.valueOf(getTid()).substring(0,4);
    }
}
