
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;

public class Tester extends Metoder {
    public static void main(String[] args) throws Exception {
        new Metoder();
        Brick brick = BrickFinder.getDefault();
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        Keys keys = ev3.getKeys();

		delUtLukket();
		roter(160);
		delUtOpen();
		boolean fortsett = true;
		while (fortsett) {
			if (isPressed()) {
				delUtLukket();
			}
			if(keys.getButtons()==keys.ID_ESCAPE){
				System.exit(0);
			}
        }
    }
}
