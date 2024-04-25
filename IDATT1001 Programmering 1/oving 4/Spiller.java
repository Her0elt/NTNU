
class Spiller{
	 int sumPoeng;
     String spillerNavn;
    public Spiller(String name){
       this.spillerNavn = name;

    }


	public int getSumPoeng(){
	 return sumPoeng;
	}


	public void kastTerning( ){
		java.util.Random terning=new java.util.Random();
		int terningkast=terning.nextInt(6)+1;
		System.out.println(this.spillerNavn+ " har kastet "+terningkast);
		System.out.println("");
		if(terningkast==1){
			sumPoeng=0;
		}
		else{
			sumPoeng+=terningkast;
		}
		if(sumPoeng>100){
			sumPoeng-=terningkast;
		}
	}

	 public boolean erFerdig(){
        return this.sumPoeng == 100;
	}

}
