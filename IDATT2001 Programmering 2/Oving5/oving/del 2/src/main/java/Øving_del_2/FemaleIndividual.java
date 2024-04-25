package Øving_del_2;

import java.time.LocalDate;

class FemaleIndividual extends Individual {
    private int noLitters;

    public FemaleIndividual(String norName,
                            String latName,
                            String latFamily,
                            LocalDate arrivalDate,
                            String name,
                            LocalDate dateOfBirth,
                            boolean farlig,
                            String adresse,
                            int noLitters){
        super(norName, latName, latFamily, arrivalDate, name, dateOfBirth, farlig,
                adresse);
        this.noLitters = noLitters;
    }
    public int getNoLitters() {
        return noLitters;
    }
    public void setNoLitters(int noLitters) {
        this.noLitters = noLitters;
    }

    @Override
    public int getAge() {
        return LocalDate.now().getYear()-(super.getDateOfBirth().getYear());
    }

    @Override
    public void move(String newAddress) {
        setAddress(newAddress);
    }

    @Override
    public String printInfo() {
        return super.toString();
    }

    @Override
    public int getNoOfLitters() {
        return noLitters;
    }

    public void addLitter(int number) {
        noLitters += number;
    }
    public void addNewLitter() {
        noLitters += 1;
    }
}
