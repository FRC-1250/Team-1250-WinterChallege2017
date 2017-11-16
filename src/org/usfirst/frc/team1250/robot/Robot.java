package org.usfirst.frc.team1250.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1250.subsystems.*;
import com.ctre.CANTalon;

public class Robot extends SampleRobot {
	
	final int kJoystickChannel = 0; // The channel on the driver station that the joystick is connected to
	double buttonPressTime = 0;	
	public DriveTrain driveTrain;
	Joystick stick;
	CANTalon shootTalon;
	DigitalInput sensor;
	Timer time;
	
	public Robot() {	
		stick = new Joystick(kJoystickChannel);
		driveTrain = new DriveTrain();
		shootTalon = new CANTalon(RobotMap.ShootTalon);
		sensor = new DigitalInput(RobotMap.SensorIO); 
		time = new Timer();
		time.start();
	}

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {

			driveTrain.Drive(stick);
			
			if(time.get() - buttonPressTime > 1)	
			{
				if(sensor.get() == false)	
					{
						
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
						}
					}
					else
					{
						System.out.println("Arming...");
						shootTalon.set(0.2);
					}		
				Timer.delay(0.005); // wait 5ms to avoid hogging CPU cycles
			}
		}
	}
}
