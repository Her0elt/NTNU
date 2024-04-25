import java.util.Scanner;

public class Valuta{

	private double exchangeRate;
	private String name;

 	public Valuta(double exchangeRate ,String navn){
		this.exchangeRate=exchangeRate;
		this.name=navn;
	}
	public double convertFromNok(double money){
		double ans=money/exchangeRate;
		return ans;
	}
	public double convertToNok(double money){
			double ans=money*exchangeRate;
			return ans;
	}
	public  static void ConvertFromNOK(Valuta valuta, Scanner in){
		System.out.println("Skriv inn ditt bel�p");
		double money=in.nextDouble();
		System.out.println("This is "+ valuta.convertFromNok(money)+ " "+ valuta.name);
	}

	public  static void ConvertToNOK(Valuta valuta, Scanner in){
		System.out.println("Skriv inn ditt bel�p");
		double money=in.nextDouble();
		System.out.println("This is "+ valuta.convertToNok(money)+ " "+ valuta.name);
	}
 	public static void main(String[]args){
		Valuta dollar=new Valuta(9,"dollar");
		Valuta euro=new Valuta(10,"euro");
		Valuta sek=new Valuta(0.93,"SEK");
		Scanner in=new Scanner(System.in);

		boolean fortsett=true;
		while(fortsett){
			System.out.println("Velg hvilken valuta du �nsker 1 Dollar 2 Euro 3 Svenske kroner 4 Avslutt program");
			int valg=in.nextInt();
			if(valg==1){
				System.out.println("Velg 1 for NOK til Dollar eller 2 for Dollar til NOK");
				int velger1=in.nextInt();
				if(velger1==1){

					ConvertFromNOK(dollar, in);
				}
				else{
					ConvertToNOK(dollar, in);
			}
			}
			if(valg==2){
				System.out.println("Velg 1 for Euro til Dollar eller 2 for Euro til NOK");
				int velger2=in.nextInt();
				if(velger2==1){

					ConvertFromNOK(euro, in);
				}
				else{
					ConvertToNOK(euro, in);
			}
			}
			if(valg==3){
				System.out.println("Velg 1 for NOK til SEK eller 2 for SEK til NOK");
				int velger3=in.nextInt();
				if(velger3==1){

					ConvertFromNOK(sek, in);
				}
				else{
					ConvertToNOK(sek, in);
				}	
			}
			if(valg==4){
				fortsett=false;
				System.out.println("Ha en fin dag, takk");

				}



		}
	}
}
