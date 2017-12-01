package org.usfirst.frc.team1250.subsystems;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import org.usfirst.frc.team1250.robot.RobotMap;
import com.ctre.CANTalon;


public class DriveTrain {
	RobotDrive robotDrive;
	CANTalon MFrontRight;
	CANTalon SFrontRight;
	CANTalon MFrontLeft; 
	CANTalon SFrontLeft;
	CANTalon MBackRight;
	CANTalon SBackRight;
	CANTalon MBackLeft;
	CANTalon SBackLeft;
	
	public DriveTrain() {
	
		MFrontRight = new CANTalon(RobotMap.MFrontRight);
		SFrontRight = new CANTalon(RobotMap.SFrontRight);
		MFrontLeft = new CANTalon(RobotMap.MFrontLeft);
		SFrontLeft = new CANTalon(RobotMap.SFrontLeft);
		MBackRight = new CANTalon(RobotMap.MBackRight);
		SBackRight = new CANTalon(RobotMap.SBackLeft);
		MBackLeft = new CANTalon(RobotMap.MBackLeft);
		SBackLeft = new CANTalon(RobotMap.SBackRight);
	
	// Setting the Slaves control mode
		
		SFrontRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		SFrontLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		SBackRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		SBackLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
	
	// Setting slaves to their masters
		
		SFrontRight.set(MFrontRight.getDeviceID());
		SFrontLeft.set(MFrontLeft.getDeviceID());
		SBackRight.set(MBackRight.getDeviceID()); 
		SBackLeft.set(MBackLeft.getDeviceID());
		
		robotDrive = new RobotDrive(MFrontLeft, MBackLeft , MFrontRight, MBackRight);
	
	// Inverting Left side
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);														
//		robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
//		robotDrive.setSafetyEnabled(true);
	}
	
	public void Drive(Joystick stick) 
	{
		robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
	}						
}
