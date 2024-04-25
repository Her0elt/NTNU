package øving10del2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * klasse for å lagre menyer
 */
public class MenyRegister {

    ArrayList<Meny> menyer=new ArrayList<Meny>();

    /**
     * metode for å finne menyer innenfor et bestemt intervall
     * @param startPris start en på intervallet
     * @param sluttPris slutten på intervallet
     * @return en string med alle menyene innenfor intervallet
     */
    public String getIntervallMeny(int startPris, int sluttPris) {
        //oppretter varibael som hvor man lagerer alle menyene som blir funnet
        String meny ="";
        //løper gjennom hele meny listen
        for (int i = 0; i < menyer.size(); i++) {
            //hvis prisen til en meny er innen for intervalle så løper man gjennom den menyen
            // og lager alle rettene og total prisen i meny stringen
             if (menyer.get(i).totalPris() < sluttPris && menyer.get(i).totalPris() > startPris) {
                for (int j = 0; j <menyer.get(i).getMeny().size() ; j++) {
                    meny += menyer.get(i).getMeny().get(j).getNavn()+ '\n';
                }
                 meny += "pris " + menyer.get(i).totalPris() + " kr" +'\n';

            }
        }
        // retunerer alle menyer som ble funnet i form av string
        return meny;
    }

    public static void main(String[] args) {
        MenyRegister mR=new MenyRegister();
        Meny m=new Meny();
        Scanner in = new Scanner(System.in);
        boolean fortsett=true;
        while (fortsett){
            System.out.println("Velg en funksjon");
            System.out.println("1: register ny øving10del2.rett");
            System.out.println("2: registrer ny meny");
            System.out.println("3: finn en øving10del2.rett ved navn");
            System.out.println("4: finn alle retter av en gitt type");
            System.out.println("5: finn alle menyer innenfor et intervall");
            System.out.println("6: Avslutt program");

            int valg = in.nextInt();

            switch (valg) {
                case 1:
                    in.nextLine();
                    System.out.println("Skriv inn om retten er en forrett hovedrett eller dessert");
                    String nyType = in.nextLine();
                    System.out.println("Skriv inn oppskriften til retten");
                    String nyOppskrift = in.nextLine();
                    System.out.println("Skriv inn navnet til retten");
                    String nyNavn = in.nextLine();
                    System.out.println("Skriv inn prisen til retten");
                    int nyPris = in.nextInt();
                    m.nyRett(nyType, nyOppskrift, nyPris, nyNavn);
                    break;
                case 2:
                    Meny mw=new Meny();
                    System.out.println("hvor mange retter skal menyen ha");
                    int antall= in.nextInt();
                    in.nextLine();
                    for (int j=0; j<antall;j++) {
                        for (int i = 1; i - 1 < m.retter.size(); i++) {
                            System.out.println(i + ": Velg " + m.retter.get(i - 1).getNavn());
                        }
                        int valg1 = in.nextInt();
                        mw.nyMeny(m.retter.get(valg1 - 1));
                    }

                        mR.menyer.add(mw);

                    break;
                case 3:
                    in.nextLine();
                    System.out.println("skriv inn navnet på en øving10del2.rett");
                    String navnRett = in.nextLine();
                    System.out.println(m.hentRettNavn(navnRett));
                    break;
                case 4:
                    in.nextLine();
                    System.out.println("Skriv inn om du vil finne forretter hovedretter eller dessertrer");
                    String valgType = in.nextLine();
                    System.out.println(m.hentRettType(valgType));
                    break;
                case 5:
                    in.nextLine();
                    System.out.println("Skriv inn start pris");
                    int startPris = in.nextInt();
                    System.out.println("Skriv inn slutt pris");
                    int sluttPris = in.nextInt();
                    System.out.println(mR.getIntervallMeny(startPris, sluttPris));
                    break;
                case 6:
                    fortsett = false;
                    break;
            }
        }
    }
}

