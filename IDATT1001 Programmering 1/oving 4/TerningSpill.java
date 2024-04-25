class TerningSpill{
	public static void main(String[]args){
		Spiller spiller1= new Spiller("Spiller 1");
		Spiller spiller2= new Spiller("Spiller 2");


		int kastNr=0;

		while(!spiller1.erFerdig() && !spiller2.erFerdig()){
			kastNr++;
			System.out.println("Dette er runde "+kastNr+":");
			System.out.println("");
			spiller1.kastTerning();
			spiller2.kastTerning();
			System.out.println("Spiller 1 har n� "+spiller1.getSumPoeng());
			System.out.println("Spiller 2 har n� "+spiller2.getSumPoeng());
			System.out.println("");
		}
		if(spiller1.erFerdig()){
            System.out.println("Spiller1 er ferdig");
		}
		else if(spiller2.erFerdig()){
			System.out.println("Spiller 2 er ferdig");
		}


	}
}
