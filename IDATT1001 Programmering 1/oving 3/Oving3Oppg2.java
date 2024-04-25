// Programmet tester om tallet du skriver inn er et primtall
import java.util.Scanner;

class Oving3Oppg2{
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		int m=0;
		do{
		System.out.println("Skriv inn ett tall du vil sjekke");
		int primtall=in.nextInt();

		int x=0;


		for(int i=2;i<=primtall;i++)
			if(primtall%i==0 && primtall!=i){
			x++;
			}
		if(x>0){
			System.out.println("Tallet er ikke et primtall");

		}else{
			System.out.println("Tallet er et primtall");
		}


		}while(m<100);

	}
}