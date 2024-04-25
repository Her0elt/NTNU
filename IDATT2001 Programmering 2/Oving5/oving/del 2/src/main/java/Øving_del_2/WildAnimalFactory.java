package Øving_del_2;

import java.time.LocalDate;

public class WildAnimalFactory {
    private WildAnimalFactory() {}

    public static ScandinavianWildAnimal newMaleBear(String name, LocalDate arrivalDate, LocalDate dateOfBirth, String address){
        return new MaleIndividual("bjørn", "Ursus arctos",
                "Ursidae", arrivalDate,name,dateOfBirth,true,address);
    }
    public static ScandinavianWildAnimal newFemaleWolf(String name, LocalDate arrivalDate, LocalDate dateOfBirth, String address){
        return  new FemaleIndividual("Ulv", "Canis lupus","Canidae",arrivalDate,
                                    name,dateOfBirth,true, address, 0);
    }
    public static ScandinavianWildAnimal newMaleWolf(String name,  LocalDate arrivalDate, LocalDate dateOfBirth,  String address){
        return new MaleIndividual("Ulv", "Canis lupus","Canidae",arrivalDate,
                name,dateOfBirth,true, address);
    }
}
class WildAnimalFactorySingelton{
    private WildAnimalFactorySingelton(){}

    public static WildAnimalFactorySingelton instance;

    public static WildAnimalFactorySingelton getInstance(){
        if(instance == null) {
            instance = new WildAnimalFactorySingelton();
        }
        return instance;

    }

    public static ScandinavianWildAnimal newMaleBear(String name,  LocalDate arrivalDate, LocalDate dateOfBirth,  String address) {
        return new MaleIndividual("bjørn", "Ursus arctos",
                "Ursidae", arrivalDate, name, dateOfBirth, true, address);
    }
    public static ScandinavianWildAnimal newFemaleWolf(String name, LocalDate arrivalDate, LocalDate dateOfBirth, String address){

            return  new FemaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate,
                    name, dateOfBirth, true, address, 0);
    }
    public static ScandinavianWildAnimal newMaleWolf(String name,  LocalDate arrivalDate, LocalDate dateOfBirth,  String address){

            return new MaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate,
                    name, dateOfBirth, true, address);
    }
}
