package Øving_del_2;

import java.time.LocalDate;

abstract class Individual extends Animal implements ScandinavianWildAnimal  {
    private String name;
    private LocalDate dateOfBirth;
    private boolean isDangerous;
    public Individual(String norName,
                      String latName,
                      String latFamily,
                      LocalDate arrivalDate,
                      String name,
                      LocalDate dateOfBirth,
                      boolean isDangerous,
                      String address){

        super(norName, latName, latFamily, arrivalDate, address);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.isDangerous = isDangerous;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public boolean isDangerous() {
        return isDangerous;
    }
    public void setDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }
}
