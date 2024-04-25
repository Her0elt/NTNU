	import java.util.Scanner;

class MatriseBruk{
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		System.out.println("Skrive inn hvor mange rader for matrise 1");
		int dim11=in.nextInt();
		System.out.println("Skrive inn hvor mange rader for matrise 2");
		int dim21=in.nextInt();
		System.out.println("Skrive inn hvor mange kolonner for matrise 1");
		int dim12=in.nextInt();
		System.out.println("Skrive inn hvor mange kolonner for matrise 2");
		int dim22=in.nextInt();


		Matrise mat=new Matrise();
	    System.out.println(	mat.defMatrise1(dim11,dim12));
		mat.defMatrise2(dim21,dim22);
		System.out.println(mat.addere());
		System.out.println(mat.multi());
		System.out.println(mat.trans());
	}

}
