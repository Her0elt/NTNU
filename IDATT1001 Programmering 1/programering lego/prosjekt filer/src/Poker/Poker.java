package Poker;

import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
class Poker {

    public static void main (String []args) throws Exception {
        Metoder m = new Metoder();
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
        }
        boolean fortsett = true;
        int teller = 0;
        while (fortsett){
            if(teller == 0){
                for (int j =0; j < 2; j++){
                    for (int i = 0; i < players; i++) {
                        m.delUtLukket();
                        m.roter(160/players);
                    }
                }
                m.roter(70);
                m.delUtLukket();
                for (int i = 0; i < 3; i++) {
                    m.delUtOpen();

                }
                teller++;
            }
            else{
                if(m.isPressed()){
                    m.delUtLukket();
                    m.delUtOpen();
                }
            }
            if(keys.getButtons()==keys.ID_ESCAPE){
                fortsett = false;
            }
        }
    }
}