import java.util.Scanner;

class OvingOppg3{
	public static void main(String[] args){
		Scanner in= new Scanner(System.in);
		System.out.println("Skriv inn sekunder");

		int sek=in.nextInt();
		int antallT=(sek/3600);
		int antallMin=((sek%3600)/60);
		int antallSek=((sek%3600)%60);
		System.out.println("Det er " +antallT+ " timer, " +antallMin+ " minutter, og "+ antallSek+" sekunder i "+sek+" sekunder");
		}
	}
