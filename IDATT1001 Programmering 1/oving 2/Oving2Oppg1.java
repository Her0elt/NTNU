//Programmet sjekker om �ret du skriver inn er et skudd�r eller ikke
import java.util.Scanner;

class Oving2Oppg1{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);

		System.out.println("Skriv inn et �r");
			int year=in.nextInt();

			if(year%400==0 && year%100==0){
				System.out.println(year+" er et skudd�r.");
			}
			else if(year%4==0 && year%100!=0){
				System.out.println(year+" er et skudd�r.");

			}
			else{
			System.out.println("Det er ikke noe spesielt med det �ret du har skrevet inn.");
			}
		}
	}

