package Del_1;

public class Whale extends Mammal implements Jumpable, Swimmable{
    public Whale(String name, int code) {
        super(name, code);
    }
    public boolean jump(){
        return true;
    }
    public boolean swim(){
        return true;
    }
}
