package øving8;

class ArbTaker{
    java.util.GregorianCalendar kalender=new java.util.GregorianCalendar();
    int ar = kalender.get(java.util.Calendar.YEAR);
    private Person personalia;
    private int arbstakernr;
    private int ansettelsesar;
    private double manedslonn;
    private double skatteprosent;


    public ArbTaker(Person personalia, int arbstakernr, int ansettelsesar, double manedslonn, double skatteprosent ){
        this.personalia=personalia;
        this.arbstakernr=arbstakernr;
        this.ansettelsesar=ansettelsesar;
        this.manedslonn=manedslonn;
        this.skatteprosent=skatteprosent;
    }
    public double getSkatteProsent() {
        return skatteprosent;
    }

    public double getManedslonn() {
        return manedslonn;
    }

    public Person getPersonalia(){
        return personalia;
    }

    public int getArbstakernr(){
        return arbstakernr;
    }

    public int getAnsettelsesAr(){
        return ansettelsesar;
    }

    public void setPersonalia(Person newPersonalia){
        this.personalia=newPersonalia;
    }

    public void setArbstakernr(int newArbstakernr){
        this.arbstakernr=newArbstakernr;
    }

    public void setAnsettelsesAr(int newAnsettelsesar){
        this.ansettelsesar=newAnsettelsesar;
    }

    public void setSkatteprosent(int newSkatteprosent){
        this.skatteprosent=newSkatteprosent;
    }

    public void setManedslonn(int newManedslonn){
        this.manedslonn=newManedslonn;
    }

    public double skatteDrag(){
        return (manedslonn*(skatteprosent/100));
    }

    public double bruttoLonn(){
        return manedslonn*12;
    }

    public double skatteTrekkPrAr(){
        return skatteDrag()*10+(skatteDrag()/2);
    }

    public String getNavn(){
        return personalia.getEtternavn()+", "+personalia.getFornavn();
    }

    public int getAlder(){
        return ar-personalia.getFodselsar();
    }

    public int antallAr(){
        int antallAr=ar-ansettelsesar;
        return antallAr;
    }

    public boolean gittAr(int gittAr){
        return antallAr()>gittAr;
    }

}