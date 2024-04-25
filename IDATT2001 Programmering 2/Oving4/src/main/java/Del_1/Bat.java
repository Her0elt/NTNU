package Del_1;

public class Bat extends Mammal implements Flyable {
    public Bat(String name, int code) {
        super(name, code);
    }
    public boolean fly(){
        return true;
    }

}
