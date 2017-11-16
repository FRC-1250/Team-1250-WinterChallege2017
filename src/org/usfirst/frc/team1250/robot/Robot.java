package src.org.usfirst.frc.team1250.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1250.subsystems.drivetrain;
import com.ctre.CANTalon;

public class Robot extends SampleRobot {
	
	final int kJoystickChannel = 0; // The channel on the driver station that the joystick is connected to
	double ButtonPressTime = 0;	
	public drivetrain Drivetrain;
	Joystick stick;
	CANTalon ShootTalon;
	DigitalInput Sensor;
	Timer time;
	
	public Robot() {	
		stick = new Joystick(kJoystickChannel);
		Drivetrain = new drivetrain();
		ShootTalon = new CANTalon(10);
		Sensor = new DigitalInput(9); 
		time = new Timer();
		time.start();
	}

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {

			Drivetrain.drive(stick);
			
			if(time.get() - ButtonPressTime > 1)	
			{
				if(Sensor.get() == false)	
					{
						
						if(stick.getRawButton(8))
						{
							System.out.println("SHOOTING...WINDING");
							ButtonPressTime = time.get();
							ShootTalon.set(0.2);
						}
						else 
						{
							System.out.println("READY TO FIRE...");
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
