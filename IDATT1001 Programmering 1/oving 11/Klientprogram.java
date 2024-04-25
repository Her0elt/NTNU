package EiendomsRegister; /**
 * Klassen EiendomsRegister.EiendomsRegister
 * @version 1, 2019-10-31
 * @author Hermann Elton
 */

import java.util.Scanner;
public class Klientprogram {
    EiendomsRegister eRegister = new EiendomsRegister();
    private final int ADD_PROPERTY = 1;
    private final int LIST_ALL_PROPERTIES = 2;
    private final int FIND_PROPERTY = 3;
    private final int CALCULATE_AVERAGE_AREA = 4;
    private final int DELETE_PROPERTY = 5;
    private final int PROPERTIES_BY_SECTION_NUMBER = 6;
    private final int EXIT = 7;
    Scanner sc = new Scanner(System.in);

    /**
     * Viser fram menyen for valg
     * @return meny valget
     */
    private int showMenu() {
        int menuChoice = 0;

        System.out.println("\n***** Eiendoms Registrerings Applikasjon v1 *****\n");
        System.out.println("1. Legg til eiendom");
        System.out.println("2. Skriv ut alle eiendommer");
        System.out.println("3. Let etter eiendom");
        System.out.println("4. Regn ut gjennomsnitt areal");
        System.out.println("5. Slett en eiendom ");
        System.out.println("6. Hent alle eiendommer med samme gårdsnummer");
        System.out.println("7. Avslutt");
        System.out.println("\nVelg ett tall mellom 1 og 7.\n");
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * starter programmet etter at meny valget har blitt gjort
     */
    public void start() {
        boolean finished = false;

        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice) {
                case ADD_PROPERTY:
                    System.out.println("Skriv inn Kommunenummeret");
                    int kommuneNummer = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Skriv inn Kommunenavnet");
                    String kommuneNavn = sc.nextLine();
                    System.out.println("Skriv inn Gårdsnummeret");
                    int gnr = sc.nextInt();
                    System.out.println("Skriv inn bruksnummeret");
                    int bnr = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Skriv inn bruksnavnet, trykk enter hvis eiendommen ikke har noe bruksnavn");
                    String bruksNavn = sc.nextLine();
                    System.out.println("Skriv inn arealet til tomta");
                    int areal = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Skriv inn eiernavnet");
                    String eierNavn = sc.nextLine();
                    if(bruksNavn.equals("")) {
                        eRegister.nyEiendom(kommuneNummer, kommuneNavn, gnr, bnr,  areal, eierNavn);
                    }
                    else {
                        eRegister.nyEiendom(kommuneNummer, kommuneNavn, gnr, bnr, bruksNavn, areal, eierNavn);
                    }
                    break;

                case LIST_ALL_PROPERTIES:
                    System.out.println(eRegister);
                    break;

                case FIND_PROPERTY:
                    sc.nextLine();
                    System.out.println("Skriv inn Kommunenummeret");
                    int kNr = sc.nextInt();
                    System.out.println("Skriv inn gårdsnummeret");
                    int nGnr = sc.nextInt();
                    System.out.println("Skriv inn bruksnummeret");
                    int nBnr = sc.nextInt();
                    System.out.println(eRegister.hentEindomUnikId(kNr, nGnr, nBnr));

                    break;
                case CALCULATE_AVERAGE_AREA:
                    System.out.println("Gjennomsnitt av alle arealene er: " + eRegister.snittAreal());
                    break;
                case DELETE_PROPERTY:
                    sc.nextLine();
                    System.out.println("Skriv inn Kommunenummeret");
                    int kyNr = sc.nextInt();
                    System.out.println("Skriv inn gårdsnummeret");
                    int nyGnr = sc.nextInt();
                    System.out.println("Skriv inn bruksnummeret");
                    int nyBnr = sc.nextInt();
                    eRegister.slettEiendom(kyNr, nyGnr, nyBnr);
                    break;
                case PROPERTIES_BY_SECTION_NUMBER:
                    sc.nextLine();
                    System.out.println("Skriv inn gårdnummeret");
                    int gnrS = sc.nextInt();
                    eRegister.samtligeEiendommer(gnrS);
                    break;
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;

                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
                }
        }
    }
    public static void main(String[] args) {
        Klientprogram klient =new Klientprogram();
        klient.eRegister.nyEiendom(1445, "Gloppen", 77, 631, "",1017.6, "Jens Olsen");
        klient.eRegister.nyEiendom(1445, "Gloppen", 77, 131, "Syningom",661.3, "Nicolay Madsen");
        klient.eRegister.nyEiendom(1445, "Gloppen", 75, 19, "Fugletun",650.6, "Evilyn Jensen");
        klient.eRegister.nyEiendom(1445, "Gloppen", 74, 188, "",1457.2, "Karl Ove Bråten");
        klient.eRegister.nyEiendom(1445, "Gloppen", 69, 47, "Høiberg",1339.4, "Elsa Indregård");
        klient.start();

    }
}

