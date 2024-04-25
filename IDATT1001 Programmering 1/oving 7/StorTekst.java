import java.util.Scanner;
class StorTekst{
	public static void main(String[]args){
		String rt="Tre minutter tidligere hadde den norske Genk-profilen ankommet Red Bull Arenas tettpakkede presserom. �sterrikere, tilreisende belgiere og VG var alle p� plass for � h�re fra 21-�ringen og manager Felice Mazz�.";
		TekstBehandler t=new TekstBehandler(rt);
		Scanner in=new Scanner(System.in);
		boolean fortsett=true;
		while(fortsett){
			System.out.println("1 for antall ord 2 for snitt p� ord lengde 3 for ord per setning 4 for � bytte ut et ord 5 print tekst 6 for � f� teksten med store bokstaver eller 7 for � avslutte");
			int valg=in.nextInt();
			if(valg==1){
				System.out.println("Det er "+t.antallOrd()+" ord i teksten din");
			}
			else if(valg==2){
				System.out.println("Gjennomsnitt lengde til alle ordene er "+t.snittOrdlengde());
			}
			else if(valg==3){
				System.out.println(t.ordPerSetning());
			}
			else if(valg==4){
				in.nextLine();
				System.out.println("Hvilket ord �nsker du � bytte ut?");
				String hentOrd=in.nextLine();
				System.out.println("Hvilket ord �nsker du � sette inn?");
				String settIn=in.nextLine();
				System.out.println(t.skiftUtOrd(hentOrd, settIn));
				
			}
			else if(valg==5){
				System.out.println(t.hentTekst());
			}
			else if(valg==6){
				System.out.println(t.storeBokstaver());
				
			}
			else{
				fortsett=false;
			}
		}
	}
}