package Idiot;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

class Metoder {
    static float[] trykkSample;
    static SampleProvider trykkSensor;
    static float[] gyroSample;
    static SampleProvider gyroSensor;
    static int offset = 0;

    /**
     * Konstuktøren som definerer alle objektene vi trenger for å kjøre de forskjellige Lejos komponentene
     */
    public Metoder() {
        try {
            Brick brick = BrickFinder.getDefault();

            Port sensKnapp = brick.getPort("S1");
            Port sensGyro = brick.getPort("S2");
            trykkSensor = new EV3TouchSensor(sensKnapp);
            trykkSample = new float[trykkSensor.sampleSize()];
            gyroSensor = new EV3GyroSensor(sensGyro).getAngleMode();
            gyroSample = new float[gyroSensor.sampleSize()];
        } catch(Exception e){
            System.out.println("Feil: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metode for å dele ut kort med bilde siden opp, alle verdier er hardkodet inn
     * @throws Exception trenger dette fordi Motoren har en mulighet for å ikke fungere.
     */
    public static void delUtOpen() throws Exception {
		/*Motor.A.setSpeed(900);
		Motor.A.forward();
		Thread.sleep(7);*/
		/*Motor.D.forward();
		Thread.sleep(180);
		Motor.D.stop();*/
        Motor.A.setSpeed(175);
        Motor.A.forward();
        Thread.sleep(200);
        Motor.A.backward();
        Thread.sleep(200);
        Motor.A.stop();
        Thread.sleep(100);
        Motor.D.setSpeed(300);//150
        Motor.D.forward();
        Thread.sleep(718);//1436
        Motor.D.stop();
    }

    /**
     *  Metode for å dele ut kort med bilde siden ned, alle verdier er hardkodet inn
     * @throws Exception trenger dette fordi Motoren har en mulighet for å ikke fungere.
     */
    public static void delUtLukket() throws Exception {
        Motor.A.setSpeed(300);
        Motor.A.forward();
        Thread.sleep(120);
        Motor.A.backward();
        Thread.sleep(140);
        Motor.A.stop();
    }

    /**
     * Denne metoden roterer roboten til en gitt vinkel
     * @param grader vinkelen gitt i grader med datatype int
     * @throws Exception trenger dette fordi Motoren har en mulighet for å ikke fungere.
     */
    public static void roter(int grader) throws Exception {
        while((getAngle() * -1) < grader) {
            System.out.println(getAngle());
            Motor.C.backward();
            Thread.sleep(10);
        }
        Motor.C.stop();
        reset();
    }

    /**
     * Henter gyro sensor verdien som roboten er på akkurat nå og trekker fra offset som er for å resette hvor roboten er
     * @return vinkelen som roboten er på som int
     * @throws Exception
     */
    public static int getAngle() throws Exception {
        gyroSensor.fetchSample(gyroSample, 0);
        return (int) gyroSample[0] - offset;
    }

    /**
     * Henter gyro sensor verdien
     * @throws Exception
     */
    public static void reset() throws Exception {
        gyroSensor.fetchSample(gyroSample, 0);
        offset = (int) gyroSample[0];
    }

    /**
     * Sjekker om trykksensoren har blitt trykket på
     * @return true eller false
     * @throws Exception
     */
    public static boolean isPressed() throws Exception {
        trykkSensor.fetchSample(trykkSample, 0);
        if (trykkSample[0] > 0){
            return true;
        } else {
            return false;
        }
    }
}