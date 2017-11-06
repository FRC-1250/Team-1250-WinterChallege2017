package org.usfirst.frc.team1250.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon;
/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends SampleRobot {
	RobotDrive robotDrive;

	// Channels for the wheels
	CANTalon MFrontRight;
	CANTalon SFrontRight;
	CANTalon MFrontLeft; 
	CANTalon SFrontLeft;
	CANTalon MBackRight;
	CANTalon SBackRight;
	CANTalon MBackLeft;
	CANTalon SBackLeft;
	
	// The channel on the driver station that the joystick is connected to
	final int kJoystickChannel = 0;

	Joystick stick = new Joystick(kJoystickChannel);

	public Robot() {
		MFrontRight = new CANTalon(0);
		SFrontRight = new CANTalon(1);
		MFrontLeft = new CANTalon(2);
		SFrontLeft = new CANTalon(3);
		MBackRight = new CANTalon(4);
		SBackRight = new CANTalon(5);
		MBackLeft = new CANTalon(6);
		SBackLeft = new CANTalon(7);
	// Setting the Slaves
		
		SFrontRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		SFrontLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		SBackRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		SBackLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
	// Setting The Masters
		
		MFrontRight.set(MFrontRight.getDeviceID());
		MFrontLeft.set(MFrontLeft.getDeviceID());
		MBackRight.set(MBackRight.getDeviceID()); 
		MBackLeft.set(MBackLeft.getDeviceID());
		
		robotDrive = new RobotDrive(MFrontRight, MFrontLeft, MBackRight, MBackLeft);
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true); // invert the
																	// left side
																	// motors
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true); // you may need
																// to change or
																// remove this
																// to match your
																// robot
		robotDrive.setExpiration(0.1);
	}

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {

			// Use the joystick X axis for lateral movement, Y axis for forward
			// movement, and Z axis for rotation.
			// This sample does not use field-oriented drive, so the gyro input
			// is set to zero.
			robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);

			Timer.delay(0.005); // wait 5ms to avoid hogging CPU cycles
		}
	}
}
