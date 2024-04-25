package EiendomsRegister;

public class Eiendom {
    /**
     * Klassen EiendomsRegister.EiendomsRegister.Eiendom
     *
     * @version 1, 2019-10-31
     * @author Hermann Elton
     */
    private int kommuneNummer;
    private String kommuneNavn;
    private int gnr;
    private int bnr;
    private String bruksNavn;
    private double areal;
    private String eierNavn;

    /**
     * Konstruktør som oppretter et objekt av typen EiendomsRegister.EiendomsRegister.Eiendom med Bruksnavn
     *
     * @param kommuneNummer
     * @param kommuneNavn
     * @param gnr
     * @param bnr
     * @param bruksNavn
     * @param areal
     * @param eierNavn
     */
    public Eiendom(int kommuneNummer, String kommuneNavn, int gnr, int bnr, String bruksNavn, double areal, String eierNavn) throws IllegalArgumentException {
        //kaster ett unntak fordi kommune nummer må være innen for inter vallet <101 , 5054>
        if (kommuneNummer < 101 || kommuneNummer > 5054) {
            throw new IllegalArgumentException("Feil i kommunenummer. " + kommuneNummer + " må være mellom 101 og 5054");
        }
        this.kommuneNummer = kommuneNummer;
        this.kommuneNavn = kommuneNavn;
        this.gnr = gnr;
        this.bnr = bnr;
        this.areal = areal;
        this.eierNavn = eierNavn;
        this.bruksNavn = bruksNavn;

    }

    /**
     * Konstruktør som oppretter et objekt av typen EiendomsRegister.EiendomsRegister.Eiendom uten Bruksnavn
     *
     * @param kommuneNummer
     * @param kommuneNavn
     * @param gnr
     * @param bnr
     * @param areal
     * @param eierNavn
     */
    //Oppgaven sier at brukeren kan registrere en eiendom uten at det blir gitt noe bruksnavn derfor er denn konstruktøren her
    public Eiendom(int kommuneNummer, String kommuneNavn, int gnr, int bnr, double areal, String eierNavn) {
        //kaster ett unntak fordi kommune nummer må være innen for inter vallet <101 , 5054>
        if (kommuneNummer < 101 || kommuneNummer > 5054) {
            throw new IllegalArgumentException("Feil i kommunenummer");
        }
        this.kommuneNummer = kommuneNummer;
        this.kommuneNavn = kommuneNavn;
        this.gnr = gnr;
        this.bnr = bnr;
        this.areal = areal;
        this.eierNavn = eierNavn;
    }

    /**
     * @return kommune nummeret
     */
    public int getKommuneNummer() {
        return kommuneNummer;
    }

    /**
     * Gir mulighet for å endre kommune nr fordi det er mulig at kommuner kan endres eller slås sammen
     *
     * @param kommuneNummer
     */
    public void setKommuneNummer(int kommuneNummer) {
        this.kommuneNummer = kommuneNummer;
    }

    /**
     * @return kommune navnet
     */
    public String getKommuneNavn() {
        return kommuneNavn;
    }

    /**
     * Gir mulighet for å endre kommune navn fordi det er mulig at kommuner kan endres eller slås sammen
     *
     * @param kommuneNavn
     */
    public void setKommuneNavn(String kommuneNavn) {
        this.kommuneNavn = kommuneNavn;
    }

    /**
     * @return Gårdsnummeret
     */
    public int getGnr() {
        return gnr;
    }

    /**
     * @return Bruksnummer
     */

    public int getBnr() {
        return bnr;
    }

    /**
     * @return bruksnavn
     */
    public String getBruksNavn() {
        return bruksNavn;
    }

    /**
     * Gir mulighet for å endre Bruksnavnet, fordi navnet på tomta kan endres eller kan tomta få et navn
     *
     * @param bruksNavn
     */
    public void setBruksNavn(String bruksNavn) {
        this.bruksNavn = bruksNavn;
    }

    /**
     * Henter Eiendommens areal i km^2
     *
     * @return arealet til tomta
     */
    public double getAreal() {
        return areal;
    }

    /**
     * Gir mulighet for å endre arealet, fordi det kan hende at tomta sone grenser endrer seg.
     *
     * @param areal Eiendommens areal i km^2
     */
    public void setAreal(double areal) {
        this.areal = areal;
    }

    /**
     * @return Eiernavnet
     */
    public String getEierNavn() {
        return eierNavn;
    }

    /**
     * Gir mulighet for å endre eiernavn fordi eier av tomta kan endres etter som tid går
     *
     * @param eierNavn
     */
    public void setEierNavn(String eierNavn) {
        this.eierNavn = eierNavn;
    }

    /**
     * @return
     */
    public String getUnikID() {
        return kommuneNummer + "-" + gnr + "/" + bnr;
    }

    /**
     * @return alle klasse variablene skrevet ut på den måten som er ønsket
     */
    @Override
    public String toString() {
        //to string gjør det lettere å skrive ut hva objektene av Eiendom. Har også to forskejellige to String for med og uten bruksnavn
        if(bruksNavn.equals(null)){
            return
                    "Kommunenavn = " + kommuneNavn + '\n' +
                    "UnikID: "+ getUnikID() + '\n' +
                    "Areal = " + areal + " km^2" + '\n' +
                    "Eiernavn = " + eierNavn + '\n'
                    ;


        }
        return
                "Kommunenavn = " + kommuneNavn + '\n' +
                "UnikID: "+ getUnikID() + '\n' +
                "Bruksnavn = " + bruksNavn + '\n' +
                "Areal = " + areal + " km^2" + '\n' +
                "Eiernavn = " + eierNavn + '\n'
        ;
    }



}
