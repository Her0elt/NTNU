//Programmet skriver ut en gange tabell som starter med tabellen for det første tallet og slutter i tabellen til det andre tallet
import java.util.Scanner;

class Oving3Oppg1{
public static void main(String[]args) {
		Scanner in=new Scanner(System.in);
		System.out.println("Skriv inn tallet du ønsker å starte gange tabel med");
			int forste=in.nextInt();
		System.out.println("Skriv inn tallet du ønsker å slutte gange tabel med");
			int andre=in.nextInt();


		for(int i=forste;i<=andre;i++){
			System.out.println(i+" gangen:");

			for(int y=1;y<=10;y++){
				int z=i*y;
				System.out.println(i+"*"+y+"="+z);

			}
			System.out.println("");
		}
	}
}
