package øving9;

import øving9.OppgaveOversikt;

public class Program {

    public static void main(String[] args){
        java.util.Scanner in=new java.util.Scanner(System.in);
        OppgaveOversikt k=new OppgaveOversikt();
        System.out.println("Register en øving9.Student: Skriv navnet til Studenten");
        String navn=in.nextLine();
        System.out.println("Skriv inn antall oppgaver studenten har løst");
        int antallOpp=in.nextInt();
        k.leggTilStudent(antallOpp,navn);


        boolean forsett=true;
        while (forsett){
            System.out.println("Velg en funksjon");
            System.out.println("1: Finn antall studenter registert");
            System.out.println("2: Finn antall oppgaver for en bestemt student");
            System.out.println("3: Register ny student");
            System.out.println("4: Øk antall oppgaver for en bestemt student");
            System.out.println("5: Avslutt program");

            int valg=in.nextInt();

            switch(valg) {
                case 1:
                    System.out.println("Det er " + k.getAntStud() + " studenter registrert");
                    break;
                case 2:
                    in.nextLine();
                    System.out.println("Skriv inn navnet på studenten");
                    String nyNavn = in.nextLine();
                    int antallOppggaver = k.getAntallOgg(nyNavn);
                    System.out.println(nyNavn+" har gjort "+antallOppggaver+" oppgaver");
                    break;
                case 3:
                    in.nextLine();
                    System.out.println("Register en øving9.Student: Skriv navnet til Studenten");
                    String nyttNavn = in.nextLine();
                    System.out.println("Skriv inn antall oppgaver studenten har løst");
                    int nyAntallOpp = in.nextInt();
                    k.leggTilStudent(nyAntallOpp, nyttNavn);
                    break;
                case 4:
                    in.nextLine();
                    System.out.println("Skriv inn navnet på studenten");
                    String nyttNavn2 = in.nextLine();
                    System.out.println("Skriv inn det ny antallet oppgaver");
                    int okning = in.nextInt();
                    k.okAntallOppg(okning, nyttNavn2);
                    break;
                case 5:
                    forsett=false;
            }
        }
    }
}
