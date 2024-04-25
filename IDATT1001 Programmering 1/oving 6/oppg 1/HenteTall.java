import java.util.Random;
class HenteTall{
	public static void main(String[] args){
		Random random=new Random();
		int[] tallListe={};
		for (int i=0;i<=1000;i++){
			int tall=random.nextInt(10);
			tallListe[tall]+=1;

		}
		for (int s = 0; s < tallListe.length; s++) {
		  System.out.println(s+" forekommer "+tallListe[s]+" ganger");
		}
	}
}