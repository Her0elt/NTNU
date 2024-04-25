class TestTemp{
	public static void main(String[]args){


		double [][]temps={{2,13,4,12,12},{13,2,-8,4,-6}};
		Temperatur temp=new temperatur(temps);
		double [] dsnitt =temp.dsnitt();
		for(int t=0;t<dSnitt.length;t++){
			System.out.println("gjennomsnittet for dag "+(t+1)+" i m�neden er "+dsnitt);
		}
		
		double [] msnitt  = temp.tsnitt();
		for(int k=0;k<tSnitt.length;k++){
			System.out.println("gjennomsnittet for hver time i m�neden er "+msnitt[k]+" for time "+(k+1));
		}
		temp.msnitt();
		System.out.println("gjennomsnittet for hele m�neden er "+temp.msnitt());
		temp.intervaller();
	}
}