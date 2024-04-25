package Del_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ZooClient {
    public static void main(String[] args) throws ZooException {
       //try {

            Zoo zoo = new Zoo("Kristiansand Dyrepark");

            Collection<Animal> animals = new ArrayList<Animal>();

            animals.add(new Crocodile("Crocodylus niloticus", 1001));
            animals.add(new Crocodile("Crocodylus niloticus", 1002));
            animals.add(new Crocodile("Crocodylus porosus", 1101));
            animals.add(new Crocodile("Crocodylus porosus", 1102));
            animals.add(new Pelican("Brown Pelican  ", 4001));
            animals.add(new Pelican("Dalmatian Pelican  ", 4101));


            animals.add(new Whale("Blue whale", 2001));
            animals.add(new Whale("Blue whale", 2002));
            animals.add(new Whale("Minke whale", 2101));
            animals.add(new Whale("Minke whale", 2102));
            animals.add(new Bat("Acerodon ", 3001));
            animals.add(new Bat("Cistugo  ", 3002));
            zoo.setAnimals(animals);

            List<Object> walker = zoo.getAnimals().stream().filter(s -> s instanceof Walkable).collect(Collectors.toList());
                try {
                    walker.stream().forEach(s -> {
                        if (s instanceof Flyable) {
                            ((Flyable) s).fly();
                        }
                        else throw new ZooException();
                    });
                }catch (ZooException e){
                    e.printStackTrace();
                }


            System.out.println("");

            zoo.getAnimals().forEach(s -> {
                if (s instanceof Flyable) {
                    System.out.println(s.toString() + " " + ((Flyable) s).fly());
                }
            });
            System.out.println("");
            zoo.getAnimals().forEach(s -> {
                if (s instanceof Mammal) {
                    if (s instanceof Jumpable) System.out.println(s.toString() + " " + ((Jumpable) s).jump());
                }
            });
            System.out.println("");

      /*  }catch (ZooException e){
            e.printStackTrace();
        }*/
    }

}