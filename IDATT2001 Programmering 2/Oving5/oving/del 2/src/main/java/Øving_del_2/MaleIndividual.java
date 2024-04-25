package Øving_del_2;

import java.time.LocalDate;

class MaleIndividual extends Individual {
    private int noLitters;
    public MaleIndividual(String norskNavn,
                          String latNavn,
                          String latFamilie,
                          LocalDate ankommetDato,
                          String navn,
                          LocalDate fDato,  boolean farlig,
                          String adresse){

        super(norskNavn, latNavn, latFamilie, ankommetDato, navn, fDato, farlig,
                adresse);
    }
    public int getNoLitters() {
        return 0;
    }
    public void setNoLitters(int noLitters) {
        this.noLitters = noLitters;
    }

    @Override
    public int getAge() {
        return LocalDate.now().getYear()-super.getDateOfBirth().getYear();
    }

    @Override
    public void move(String newAddress) {

    }

    @Override
    public String printInfo() {
        return super.toString();
    }

    @Override
    public int getNoOfLitters() {
        return 0;
    }

    @Override
    public void addLitter(int antall) {
        throw new IllegalArgumentException("Male can't have litter");
    }

    @Override
    public void addNewLitter() {
        throw new IllegalArgumentException("Male can't have litter");
    }
}
