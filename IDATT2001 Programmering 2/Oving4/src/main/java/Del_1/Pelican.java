package Del_1;

public class Pelican extends Oviparous implements Flyable {
    public Pelican(String name, int code) {
        super(name, code);
    }

    @Override
    public boolean fly() {
        return true;
    }
}
