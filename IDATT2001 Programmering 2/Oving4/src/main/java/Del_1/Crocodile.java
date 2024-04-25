package Del_1;

public class Crocodile extends Oviparous implements Swimmable, Walkable {
    public Crocodile(String name, int code) {
        super(name, code);
    }
    public boolean walk(){
        return true;
    }
    public boolean swim(){
        return true;
    }
}
