class TekstBehandler{
	String tekst;
	public TekstBehandler(String minTekst){
		tekst=minTekst;
	}
	public int antallOrd(){
		String[]ord=tekst.split(" ");
		return ord.length;
	}
	public int snittOrdlengde(){
		String[]ord=tekst.split(" ");
		int lengde=0;
		for(int i=0;i<ord.length;i++){
			lengde+=ord[i].length();
		}
		int snitt=lengde/ord.length;
		return snitt;
	}
	public int ordPerSetning(){
		String[]setninger=tekst.split("\\.\\s+");
		int antallOrd=0;
		for(int i=0;i<setninger.length;i++ ){
			String[]ord=setninger[i].split(" ");
			antallOrd+=ord.length;
		}
		int snitt=antallOrd/setninger.length;
		return snitt;
	}
	public String  skiftUtOrd(String ordet, String nyttOrd){
		String str=tekst.replace(ordet,nyttOrd);
		return(str);
	}
	public String hentTekst(){
		return tekst;
	}
	public String storeBokstaver(){
		return tekst.toUpperCase();
	}

}