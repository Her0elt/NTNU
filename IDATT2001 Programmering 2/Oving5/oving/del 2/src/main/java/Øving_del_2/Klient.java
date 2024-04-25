package Øving_del_2;

import java.time.LocalDate;
import java.util.logging.Level;

public class Klient {
    public static void main(String[] args) {
        try {
            ScandinavianWildAnimal ulla = WildAnimalFactorySingelton.newFemaleWolf("Ulla",
                    LocalDate.of(2015, 2, 26), LocalDate.of(2015, 4, 29), "Innhegning 2");

            System.out.println(ulla.printInfo());

            ScandinavianWildAnimal hella = WildAnimalFactorySingelton.newMaleBear("fdfdsfsd",
                    LocalDate.of(2015, 2, 26), LocalDate.of(2015, 4, 29), "fdsfds 2");

            System.out.println(hella.printInfo());

        }catch (Exception e){
            ZooLogger.getLogger().log(Level.FINE, e.getMessage());
            ZooLogger.closeHandler();
            e.printStackTrace();
        }

    }
}
