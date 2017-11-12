package org.usfirst.frc.team1250.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.CANTalon;


public class drivetrain extends Subsystem {
	
	MFrontRight = new CANTalon(18);
	SFrontRight = new CANTalon(17);
	MFrontLeft = new CANTalon(11);
	SFrontLeft = new CANTalon(12);
	MBackRight = new CANTalon(16);
	SBackRight = new CANTalon(15);
	MBackLeft = new CANTalon(13);
	SBackLeft = new CANTalon(14);

// Setting the Slaves
	
	SFrontRight.changeControlMode(CANTalon.TalonControlMode.Follower);
	SFrontLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
	SBackRight.changeControlMode(CANTalon.TalonControlMode.Follower);
	SBackLeft.changeControlMode(CANTalon.TalonControlMode.Follower);

// Setting The Masters
	
	SFrontRight.set(MFrontRight.getDeviceID());
	SFrontLeft.set(MFrontLeft.getDeviceID());
	SBackRight.set(MBackRight.getDeviceID()); 
	SBackLeft.set(MBackLeft.getDeviceID());
	
	robotDrive = new RobotDrive(MFrontLeft, MBackLeft , MFrontRight, MBackRight);

// Inverting Left side
	
	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
																
															
	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
															
}
