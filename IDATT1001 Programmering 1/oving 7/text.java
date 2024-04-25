import java.util.Scanner;
class text{
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		System.out.println("Skriv inn din tekst");
		String dinTekst=in.nextLine();
		System.out.println("Skriv inn en bokstav du har lyst til � ta bort");
		String dinBokstav=in.nextLine();
		StringMethods s=new StringMethods(dinTekst);
		System.out.println("Dette er din nye tekst uten "+dinBokstav);
		System.out.println(s.fjerning(dinBokstav));
		System.out.println("Her er teksten din med bare forbokstaver \n "+ s.forkorting()) ;
		
	}
}