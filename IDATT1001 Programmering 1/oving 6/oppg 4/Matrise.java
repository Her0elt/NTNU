import java.util.*;


class Matrise{
	java.util.Random rand=new java.util.Random();
	private int [][]matrise1;
	private int [][]matrise2;
	void defMatrise1(int dim1,int dim2){
		matrise1=new int[dim1][dim2];
		for(int i = 0; i < matrise1.length; ++i){
			for(int j = 0; j < matrise1[i].length; ++j){
				int nextTall=rand.nextInt(10)+1;
				matrise1[i][j]=nextTall;
			}
		}
	}
		void defMatrise2(int dim1, int dim2){
		matrise2=new int[dim1][dim2];
			for(int i = 0; i < matrise2.length; ++i){
				for(int j = 0; j < matrise2[i].length; ++j){
					int nextTall=rand.nextInt(10)+1;
					matrise2[i][j]=nextTall;
			}
		}
	}

	public int hentMatrise1(int tall1 ,int tall2){
		return matrise1[tall1][tall2];
	}

	public int getMatrise1R(){
		return matrise1.length;
	}

	public int getMatrise1K(){
			return matrise1[0].length;
	}



	public int[][] addere(){
		int[][]svar=new int[getMatrise1R()][getMatrise1K()];
		if(matrise1.length==matrise2.length && matrise1.length==matrise2[0].length){
			for(int i = 0; i < getMatrise1R(); ++i){
				for(int j = 0; j<getMatrise1K(); ++j){
					svar[i][j]=hentMatrise1(i,j)+matrise2[i][j];
				}
			}

		}
		else{
			throw new ArithmeticException("Dimensjonene stemmer ikke");
		}
		return svar;
	}

	public int[][] multi(){
		if(getMatrise1K()==matrise2.length){
			int[][]svar=new int[getMatrise1R()][matrise2[0].length];
			for(int i = 0; i < matrise2[0].length; i++){
				for(int j = 0; j < getMatrise1R(); j++){
					for(int k=0;k<getMatrise1K();k++){
						svar[j][i]=svar[j][i]+hentMatrise1(j,k)*matrise2[k][i];
					}
				}
			}
		}

		else if(getMatrise1K()!=matrise2.length){
			throw new ArithmeticException("Dimensjonene stemmer ikke");
		}
		return svar;
	}
		public int [][] trans(){
		int[][]svar=new int[getMatrise1R()][getMatrise1K()];
				for(int i = 0; i < getMatrise1R(); ++i){
					for(int j = 0; j < getMatrise1K(); ++j){
						svar[i][j]=hentMatrise1(j,i);
					}
				}
			return svar;
		}
}
