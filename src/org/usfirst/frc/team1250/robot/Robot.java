package org.usfirst.frc.team1250.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team1250.subsystems.drivetrain;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
// -----------------------------------------------------------------------------------------------
public class Robot extends SampleRobot {

	public drivetrain Drivetrain;

	public void robotInit() {
	
	Drivetrain = new drivetrain();
}
		RobotDrive robotDrive;
	
	// The channel on the driver station that the joystick is connected to
	final int kJoystickChannel = 0;

	Joystick stick = new Joystick(kJoystickChannel);
	
	CANTalon ShootTalon;
	
	DigitalInput Sensor;
	
	Timer time;
	
	double ButtonPressTime = 0;	
	
	public Robot() {
		
		ShootTalon = new CANTalon(10);
		Sensor = new DigitalInput(9); 
		time = new Timer();
		time.start();
		
		robotDrive.setExpiration(0.1);
	}

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {

			robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
//			System.out.println(time.get()); Noted Out For Testing
//			All below is logic for shooter. I will be making a sub system for this.
			if(time.get() - ButtonPressTime > 1)	
		{
			if(Sensor.get() == false)	
				{
					
					if(stick.getRawButton(8))
					{
						System.out.println("SHOOTING");
						ButtonPressTime = time.get();
						ShootTalon.set(0.2);
					}
					else 
					{
						System.out.println("READY TO FIRE");
						ShootTalon.set(0);
					}
				}
				else
				{
					ShootTalon.set(0.2);
				}		
			Timer.delay(0.005); // wait 5ms to avoid hogging CPU cycles
		}
		}
		
	}
}
