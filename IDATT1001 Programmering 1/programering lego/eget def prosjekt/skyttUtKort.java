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



class skyttUtKort{
	public static void main(String []args)throws Exception{
	try{
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		Brick brick = BrickFinder.getDefault();
		Keys keys = ev3.getKeys();
		boolean f=true;
		while(f){
			if(keys.getButtons()==keys.ID_ENTER){
				System.out.println();
				Motor.D.setSpeed(100);
			   	Motor.D.forward();
				Thread.sleep(10);
				Motor.D.stop();
			}
		}
	}
		catch(Exception e){
		System.out.println("Feil"+ e);
		e.printStackTrace();
		}
	}
}