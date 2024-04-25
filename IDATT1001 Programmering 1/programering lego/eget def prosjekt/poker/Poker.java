import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
class Poker extends Metoder{

    public static void main (String []args) throws Exception {
        new Metoder();
        Brick brick = BrickFinder.getDefault();
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        Keys keys = ev3.getKeys();
        int players = 0;
        boolean lokke = true;
        System.out.println("Press opp for å legge til spiller \n press ned for å slette spiller \n press enter for å starte spill");
        while(lokke){

            if(keys.getButtons()==keys.ID_ENTER){
                lokke = false;
            } else if(keys.getButtons()==keys.ID_LEFT){
                if(players > 0) {
                    --players;
                    System.out.println("-1 spiller");
                    System.out.println(players);

                } else System.out.println("Antall spillere kan ikke bli mindre enn 0");

            } else if(keys.getButtons()==keys.ID_RIGHT){
                ++players;
                System.out.println("+1 spiller");
                System.out.println(players);

            }
            Thread.sleep(150);
        }
        for (int j =0; j < 2; j++){
            for (int i = 0; i < players; i++) {
				roter(340/players);
                delUtLukket();

            }
        }

        roter(70);
        delUtLukket();
        for (int i = 0; i < 3; i++) {
            delUtOpen();

        }
        boolean fortsett = true;
        int teller=0;
        while (teller<2){
			if(isPressed()){
				delUtLukket();
				delUtOpen();
				teller++;
			}else if(keys.getButtons()==keys.ID_ESCAPE){
				System.exit(0);
			}
        }
    }
}