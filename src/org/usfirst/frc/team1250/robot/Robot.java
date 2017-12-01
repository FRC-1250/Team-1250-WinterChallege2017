package org.usfirst.frc.team1250.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1250.subsystems.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Robot extends SampleRobot {
	
	final int kJoystickChannel = 0; // The channel on the driver station that the joystick is connected to
	double buttonPressTime = 0;	
	public DriveTrain driveTrain;
	Joystick stick;
	CANTalon shootTalon;
	DigitalInput sensor;
	Timer time;
	I2C i2c;
	byte[] toSend = new byte[1];
	
	public Robot() {	
		stick = new Joystick(kJoystickChannel);
		driveTrain = new DriveTrain();
		shootTalon = new CANTalon(RobotMap.ShootTalon);
		sensor = new DigitalInput(RobotMap.SensorIO); 
		time = new Timer();
		time.start();
		i2c = new I2C(I2C.Port.kOnboard,84);
	}
	
    @Override
    public void disabled() {
    	toSend[0] = 0;
    	i2c.transaction(toSend,  1, null, 0);
    }
	

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		String sentByte;
		
		while (isOperatorControl() && isEnabled()) {
//		boolean on = false;
//			if (on)
//				toSend[0] = 76;
//			else
//				toSend[0] = 72;
//			on = ! on;
			
			
			driveTrain.Drive(stick);
			
			if(time.get() - buttonPressTime > 1)	
			{
				if(sensor.get() == false)	
					{
						//toSend[0] = 76;
						if(stick.getRawButton(8))
						{
							System.out.println("SHOOTING...WINDING");
							buttonPressTime = time.get();
							shootTalon.set(0.2);
						}
						else 
						{
							System.out.println("READY TO FIRE...");
							shootTalon.set(0);
							toSend[0] = 72;
						}
					}
					else
					{
						System.out.println("Arming...");
						shootTalon.set(0.2);
						toSend[0] = 76;
					}	
				System.out.printf("Value Sent = %d ", toSend[0]);
				//sentByte = String.format("Sent Byte: %d", toSend[0])
				i2c.transaction(toSend,  1, null, 0);
				Timer.delay(0.005); // wait 5ms to avoid hogging CPU cycles
			}
		}
	}
}
