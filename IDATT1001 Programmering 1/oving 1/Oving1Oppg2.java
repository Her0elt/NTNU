import java.util.Scanner;

class Oving1Oppg2{
	public static void main(String[] args){
		 Scanner in=new Scanner(System.in);

		 System.out.println("skriv inn antall Timer");
		  	int timer= in.nextInt();

		 System.out.println("skriv inn antall Minutter");
		 	int min= in.nextInt();

		 System.out.println("skriv inn antall Sekunder");
			int sek= in.nextInt();


		 int antallSek=((timer*60)*60)+(min*60)+(sek);
		 	System.out.println(timer+" timer "+min+" minutter "+sek+" sekunder er: "+antallSek+ " sekunder");

		 }
	}
