//Programmet tester hvilket av de to Kjøtt pakkene er billigst
class Oving2Oppg2{
	public static void main(String[]args){
		double kA=35.90;
		double kB=39.50;
		double kjottAPrG=(kA/450);
		double kjottBPrG=(kB/500);
	
		String message = (kjottAPrG>kjottBPrG)? "Kjøtt A koster mer pr kg enn Kjøtt B derfor er B billigere enn A":
		"Kjøtt A koster mindre pr kg enn Kjøtt B derfor er A billigere enn B"; 
		
		System.out.println(message);
	}
}
