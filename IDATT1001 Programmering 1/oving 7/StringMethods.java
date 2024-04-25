class StringMethods{
	String tekst;
	public StringMethods(String bTekst){
		tekst=bTekst;
	}
	public String forkorting(){
		StringBuilder sb = new StringBuilder();
		String[]ord=tekst.split(" ");
		for (int i=0;i<ord.length;i++){
			sb.append(ord[i].charAt(0));
		}
		return sb.toString();
	}
	public String fjerning(String bokstav){
		String str=tekst.replace(bokstav,"");
		return str;
	}
}