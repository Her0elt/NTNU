package øving10;


public class menyProgram {
    public static void main(String[] args){
        java.util.Scanner in=new java.util.Scanner(System.in);
        ArrangementRegister k=new ArrangementRegister();
        boolean forsett=true;
        while (forsett){
            System.out.println("Velg en funksjon");
            System.out.println("1: Registrer nytt øving10.Arrangement");
            System.out.println("2: Finn alle arrangementer på et sted");
            System.out.println("3: Finn alle arrangementer på en dato");
            System.out.println("4: Finn alle arrangementer innenfor et tidsintervall ");
            System.out.println("5: Finn alle arragmenter sotert etter sted type eller tid");
            System.out.println("6: Avslutt program");

            int valg=in.nextInt();

            switch(valg) {
                case 1:
                    in.nextLine();
                    System.out.println("Skriv inn navnet på arrangementet");
                    String navn=in.nextLine();
                    System.out.println("Skriv inn stedet til arrangementet");
                    String sted=in.nextLine();
                    System.out.println("Skriv inn arrangor til arrangementet");
                    String arrangor=in.nextLine();
                    System.out.println("Skriv inn hva slags type arrangementet det er ");
                    String type=in.nextLine();
                    System.out.println("Skriv inn datoen til arrangementet");
                    long tid=in.nextLong();
                    System.out.println(k.nyttArrangement(navn,sted,arrangor,type,tid));
                    break;
                case 2:
                    in.nextLine();
                    System.out.println("Skriv inn navnet på stedet");
                    String stedFinn=in.nextLine();
                    System.out.println(k.hentArrangementSted(stedFinn));
                    break;
                case 3:
                    in.nextLine();
                    System.out.println("Skriv inn tiden du vil see etter");
                    int tidFinn = in.nextInt();
                    System.out.println(k.hentArrangementTid(tidFinn));
                    break;
                case 4:
                    in.nextLine();
                    System.out.println("Skriv inn fra klokkeslettet");
                    int fraTid = in.nextInt();
                    System.out.println("Skriv inn til klokkeslettet");
                    int  tilTid = in.nextInt();
                    System.out.println(k.hentArrangementFraTil(fraTid, tilTid));
                    break;
                case 5:
                    System.out.println("1: sorter etter sted");
                    System.out.println("2: sorter etter tid");
                    System.out.println("3: sorter etter type");
                    int hva=in.nextInt();
                    System.out.println(k.sorterEtter(hva));
                    break;
                case 6:
                    forsett=false;
            }
        }
    }
}
