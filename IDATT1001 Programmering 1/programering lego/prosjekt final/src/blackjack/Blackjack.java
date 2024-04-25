package blackjack;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;

public class Blackjack extends Metoder{

    public static void main(String[] args) throws Exception {

        try {
            new Metoder();

            //Select the amounth of players
            int players = 0;
            EV3 ev3 = (EV3) BrickFinder.getLocal();
            Keys keys = ev3.getKeys();
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

            //Blackjack game
            double RotationAmounth = (double) 180/(double) players;
            for(int j= 0 ; j<2; j++ ) {
                for(int i = 0; i < players; i++) {
                    delUtLukket();
                    roter((int)RotationAmounth);
                }
                roter(-180);

            }
            boolean hit = true;
            while(hit){
                if(keys.getButtons()==keys.ID_ESCAPE){
                    hit = false;
                }
                else{
                    if(isPressed()){
                        delUtLukket();

                    }
                }
            }

        } catch(Exception e){
            System.out.println("Feil: " + e);
            e.printStackTrace();
        }

    }//main

}//class
