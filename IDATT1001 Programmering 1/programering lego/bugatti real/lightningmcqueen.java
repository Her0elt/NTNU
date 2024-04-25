import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound.*;
import lejos.hardware.Audio;
import java.io.File;
import lejos.hardware.Sound;


class lightningmcqueen {
	public static  boolean lokke = true;
	public static void main(String[] arg) throws Exception {
		try{

			Brick brick = BrickFinder.getDefault();

			Port sensFarge1 = brick.getPort("S1");
			Port sensFarge2 = brick.getPort("S2");


			final EV3 ev3 = (EV3) BrickFinder.getLocal();
			TextLCD lcd = ev3.getTextLCD();
			final Keys keys = ev3.getKeys();
			//starter programet
			System.out.println("Begynn Race");
			keys.waitForAnyPress();

			EV3ColorSensor fargesensor1 = new EV3ColorSensor(sensFarge1);
			SampleProvider fargeLeser1 = fargesensor1.getMode("RGB");
			float[] fargeSample1 = new float[fargeLeser1.sampleSize()];
			EV3ColorSensor fargesensor2 = new EV3ColorSensor(sensFarge2);
			SampleProvider fargeLeser2 = fargesensor2.getMode("RGB");
			float[] fargeSample2 = new float[fargeLeser2.sampleSize()];



			int roteringsFart = 450; //450
			int motorSpeed = 787; //787

			// funker: 700/400

			Motor.A.setSpeed(motorSpeed);
			Motor.C.setSpeed(motorSpeed);
			Motor.A.forward();
			Motor.C.forward();



			Thread myThread = new Thread(){
				public int  sing(File file) throws Exception{
					return Sound.playSample(file, 100);
				}
				public void run(){
					System.out.println("sound");
					try{
						Sound.setVolume(100);
						File file =new File("highway.wav");
						int wavfilelength = sing(file);
					}
					catch(Exception e){
						System.out.println("Could not play file: " + e.getMessage ());
					}
				}
			};
			myThread.start();



			while(lokke){


				fargeLeser1.fetchSample(fargeSample1, 0);
				fargeLeser2.fetchSample(fargeSample2, 0);




				if(fargeSample1[0] < 0.07 && fargeSample2[0] < 0.07){
					//System.out.println(fargeSample1[0] + " og " + fargeSample2[0]);
					Motor.A.setSpeed(motorSpeed);
					Motor.C.setSpeed(motorSpeed);
					Motor.A.forward();
					Motor.C.forward();

				}
				else if(fargeSample1[0] > 0.07 && fargeSample2[0]> 0.07 ){

				}
				else{
					if(fargeSample1[0] > 0.07){
						System.out.println("venste");
						Motor.A.setSpeed(motorSpeed);
						Motor.C.setSpeed(roteringsFart);
						Motor.A.forward();
						Motor.C.forward();
						Thread.sleep(20);
					}
					else if(fargeSample2[0] > 0.07){
						System.out.println("høyre");
						Motor.C.setSpeed(motorSpeed);
						Motor.A.setSpeed(roteringsFart);
						Motor.A.forward();
						Motor.C.forward();
						Thread.sleep(20);
					}
				}

				if(keys.getButtons()==keys.ID_ENTER){
				        	/*lokke=false;
				        	myThread.stop();
				        	System.out.println("stop ");*/
				        	System.exit(0);
				}
			}
		} catch(Exception e){
			System.out.println("Feil: " + e);
			e.printStackTrace();
		}
	}
}