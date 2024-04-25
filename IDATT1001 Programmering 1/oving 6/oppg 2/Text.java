import java.util.Scanner;
class Text{
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		System.out.println("Skriv inn teksten din");
		String text=in.nextLine();
		System.out.println("Skriv en bokstav du har lyst til � lete etter");
		String userBokstav=in.nextLine();
		char ub=userBokstav.charAt(0);
		TekstAnalyse t1=new TekstAnalyse();
		t1.Analyse(text);
		t1.skrivArray();
		System.out.println("Det er "+t1.finnTotal()+" antall bokstaver i teksten");
		System.out.println("Det er "+t1.finnProsent()+"% er ikke bokstaver i teksten");
		System.out.println("Det er "+t1.forekomster(ub)+" av bokstaven "+ub);
		char flest = t1.flest();
		String msg = (flest != -1) ? (flest+" forekommer flest ganger") : "Det er ikke en bokstav som forekommer flest ganger";
	}
}