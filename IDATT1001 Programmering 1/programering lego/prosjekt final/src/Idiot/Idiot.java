package Idiot;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;

public class Idiot extends Metoder {
    public static void main(String[] args) throws Exception {
        new Metoder();
        Brick brick = BrickFinder.getDefault();
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        Keys keys = ev3.getKeys();

        for (int i = 0; i < 6; i++) {
            delUtLukket();
            roter(160);
        }
        for (int i = 0; i < 6; i++) {
            delUtOpen();
            roter(160);
        }
        for (int i = 0; i < 6; i++) {
            delUtLukket();
            roter(160);
        }
        boolean fortsett = true;
        while (fortsett) {
            if (isPressed()) {
                delUtLukket();
            }
            if(keys.getButtons()==keys.ID_ESCAPE){
                fortsett = false;
            }
        }
    }
}
