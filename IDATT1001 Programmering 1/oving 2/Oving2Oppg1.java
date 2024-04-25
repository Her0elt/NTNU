//Programmet sjekker om året du skriver inn er et skuddår eller ikke
import java.util.Scanner;

class Oving2Oppg1{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);

		System.out.println("Skriv inn et år");
			int year=in.nextInt();

			if(year%400==0 && year%100==0){
				System.out.println(year+" er et skuddår.");
			}
			else if(year%4==0 && year%100!=0){
				System.out.println(year+" er et skuddår.");

			}
			else{
			System.out.println("Det er ikke noe spesielt med det året du har skrevet inn.");
			}
		}
	}

