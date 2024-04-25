package Øving_del_2;

import java.time.LocalDate;

public interface ScandinavianWildAnimal {
    String getName();
    LocalDate getDateOfBirth();
    int getAge();
    String getAddress();
    void move(String newAddress);
    String printInfo();
    int getNoOfLitters();
    void addLitter(int antall);
    void addNewLitter();
}
