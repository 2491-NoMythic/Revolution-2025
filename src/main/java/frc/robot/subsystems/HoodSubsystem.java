package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HoodSubsystem extends SubsystemBase {
	Servo servo1;
	Servo servo2;

	/**
	 * Creates a new Shooter.
	 */
	public HoodSubsystem() {
		
		servo1 = new Servo(Constants.Shooter.Servo1PwmID);
		servo2 = new Servo(Constants.Shooter.Servo2PwmID);
		
		SmartDashboard.putNumber("hood position overide", 0);
	}

	/**
	 * Set hood position
	 * @param setHoodPosition (between 0-180 servo units)
	 * 0 is zero degrees and 180 is 270 degrees on servo (maximum supported hood position)
	 */
	public void setHoodPosition(double servoUnits){
		servo1.setAngle(180 - servoUnits);
		servo2.setAngle(servoUnits);
		SmartDashboard.putNumber("Hood servoUnits", servoUnits);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("servo1", servo1.getAngle());
		SmartDashboard.putNumber("servo2", servo2.getAngle());
	}
}
