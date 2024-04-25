class brok{
	private int x;
	private int y;

	public brok(int teller, int nevner){
		x=teller;
		y=nevner;
		if(nevner==0){
		throw new ArithmeticException("Du har skrevet inn en ugyldig verdi");
		}

	}

	public brok(int teller){
	x=teller;
	y=1;

	}
	public int getTeller(){
		return x;
	}

	public int getNevner(){
		return y;
	}

	public String sub(int tall1,int tall2){
		int tellerSvar=(tall2*this.x)-(tall1*this.y);
		int nevnerSvar=(tall2*this.y);
		String svar=(tellerSvar+"/"+nevnerSvar);
	    return (svar);
	}
	public String multi(int tall1,int tall2){
		int tellerSvar=this.x*tall1;
		int nevnerSvar=this.y*tall2;
		String svar=(tellerSvar+"/"+nevnerSvar);
	    return (svar);

	}
	public String div(int tall1,int tall2){
		int tellerSvar=this.x*tall2;
		int nevnerSvar=this.y*tall1;
		String svar=(tellerSvar+"/"+nevnerSvar);
	    return ("Svaret blir da "+svar);
	}
	public String pluss(int tall1,int tall2){
		int tellerSvar=(tall2*this.x)+(tall1*this.y);
		int nevnerSvar=(tall2*this.y);
		String svar=(tellerSvar+"/"+nevnerSvar);
	    return (svar);
	}
	public static void main(String[]args){
		brok brok1=new brok(2,3);
		brok brok2=new brok(4);

		int a = brok2.getTeller();
		int b = brok2.getNevner();

		System.out.println("Svaret blir da "+brok1.pluss(a,b));
		System.out.println("Svaret blir da "+ brok1.sub(a,b));
        System.out.println(brok1.div(a,b));
        System.out.println(brok1.multi(a,b));

	}







}
