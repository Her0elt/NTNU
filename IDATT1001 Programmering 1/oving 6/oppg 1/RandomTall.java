import java.util.Random;
class RandomTall{
	public static void main(String[] args){
		Random random=new Random();
		int[] tallListe=new int[10];
		for (int i=0;i<=1000;i++){
			int tall=random.nextInt(10);
			tallListe[tall]+=1;
		}

		for (int s = 0; s < tallListe.length; s++){
			System.out.println("");
			System.out.print(s+" forekommer "+tallListe[s]+" ganger");
			for(int g=0;g<tallListe[s]/10;g++){
				System.out.print("*");
			}



		}
	}
}
