class MinRandom{
	java.util.Random rand=new java.util.Random();

	public int nesteHeltall(int nedre, int ovre){
		int heltall=rand.nextInt(ovre-nedre)+nedre;
		return heltall;
	}
	public double nesteDesimaltall(double nedre, double ovre){
		double desimaltall=rand.nextDouble()*(ovre-nedre)+nedre;
		return desimaltall;
	}
	public static void main(String[]args){
		java.util.Scanner in=new java.util.Scanner(System.in);
		MinRandom j=new MinRandom();
		boolean fortsett=true;
		while(fortsett){
			System.out.println("velg 1 for et Helt tall eller 2 for et Desimaltall? Skriv 3 for å avslutte.");
			int valg=in.nextInt();
			if(valg==1){
				System.out.println("Skriv inn nedre grense");
				int x=in.nextInt();
				System.out.println("Skriv inn øvre grense");
				int y=in.nextInt();
				System.out.println("Her er ditt heltall "+j.nesteHeltall(x,y));
			}
			else if(valg==2){
				System.out.println("Skriv inn nedre grense");
				int x=in.nextInt();
				System.out.println("Skriv inn øvre grense");
				int y=in.nextInt();
				System.out.println("Her er ditt desimaltalls "+j.nesteDesimaltall(x,y));
			}
			else{
				fortsett=false;
				System.out.println("Bye bye");
			}
		}
	}
}
