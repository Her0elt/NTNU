package øving8;

class Person{
    final String fornavn;
    final String etternavn;
    final int fodselsar;

    public Person(String fNavn, String eNavn, int fAr){
        fornavn=fNavn;
        etternavn=eNavn;
        fodselsar=fAr;
    }
    public String getFornavn(){
        return fornavn;
    }
    public String getEtternavn(){
        return etternavn;
    }
    public int getFodselsar(){
        return fodselsar;
    }
}