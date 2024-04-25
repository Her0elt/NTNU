import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;
import java.io.File;

class FolgSvart{
	Brick brick = BrickFinder.getDefault();
    Port s1 = brick.getPort("S1");
 	Port s2 = brick.getPort("S2");

 	EV3ColorSensor fargesensor = new EV3ColorSensor(s1);
	SampleProvider fargeLeser = fargesensor.getMode("RGB");
	float[] fargeSample = new float[fargeLeser.sampleSize()];
	Motor.A.setSpeed(600);
    Motor.B.setSpeed(600);
	int i=0;
	boolean fortsett=true;

		while (fortsett) {
		           	fargeLeser.fetchSample(fargeSample, 0);  // hent verdi fra fargesensor
		            // lcd.drawString("Farge: " + fargeSample[0], 0, 3);
		            // lcd.drawString("i: " + i, 0, 6);

					if (fargeSample[0] > 0.25) {
						Motor.A.setSpeed(900);
						Motor.B.setSpeed(900);
						Motor.A.forward();
						Motor.B.forward();

					} else if (fargeSample[0] > 0.08) {
		                //if(i == 4){
		                  //  Motor.A.stop(true);
		                    //Motor.B.stop(true);
							// }

						if(i == 20){
							Motor.A.setSpeed(150);
							Motor.B.setSpeed(150);
		                    Motor.B.forward();
		                    Motor.A.backward();
		                    Thread.sleep(20);
						} else {
		                    Motor.A.setSpeed(150);
						    Motor.B.setSpeed(150);
						    Motor.B.backward();
		                    Motor.A.forward();
		                    Thread.sleep(10);
							i++;
		                }

		       	 	} else {
					    Motor.A.setSpeed(600);
		                Motor.B.setSpeed(600);
		                Motor.A.forward();
			            Motor.B.forward();
					    i = 0;

		}
     }
}