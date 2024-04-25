package øving10del2;

/**
 * klasse for en øving10del2.rett, en øving10del2.rett har en string som sier hva slags type øving10del2.rett det er forrett hovedrett, etc,
 * en int som har pris, og et navn som er en string
 */
public class rett {
    String oppskrift;
    int pris;
    String type;
    String navn;

    /**
     * Konstuktøren til øving10del2.rett trenger string string int og string for å bl brukt
     * @param type er typen øving10del2.rett altså forrett, etc
     * @param oppskrift er oppskriften til retten.
     * @param pris er prisen til den bestemte retten
     * @param navn er nvanet på retten
     */
    public rett(String type, String oppskrift, int pris, String navn) {
        // gir verdier til alle klasse variablene
        this.type = type;
        this.oppskrift = oppskrift;
        this.pris = pris;
        this.navn = navn;
    }

    /**
     *
     * @return prisen til retten
     */
    public int getPris() {
        // henter prisen til menyen
        return pris;
    }

    /**
     *
     * @return oppskriften til retten
     */
    public String getOppskrift() {
        //henter oppskriften som til hører retten
        return oppskrift;
    }

    /**
     *
     * @return hva slags type øving10del2.rett det er
     */
    public String getType() {
        //henter hva slags type øving10del2.rett det er
        return type;
    }

    /**
     *
     * @return navnet til retten
     */
    public String getNavn() {

        return navn;
    }

    @Override
    public String toString() {
        // lager en to string så det er lettere å skrive ut ett øving10del2.rett objekt
        return "    øving10del2.rett" +
                "oppskrift= " + oppskrift + '\n' +
                "pris= " + pris +
                "type= " + type + '\n' +
                " navn = " + navn + '\n'
                ;
    }
}