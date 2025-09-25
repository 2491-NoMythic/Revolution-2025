package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PhotonCannon extends SubsystemBase {

	private Relay photonCannonRelay;

	public PhotonCannon() {
        photonCannonRelay = new Relay(Constants.Shooter.PhotonCannonPwmID);
        shutdownPhotonCannon();
	}

	/**
	 * runs the anti jam motor at the given speed
	 * @param motorPercent is the speed of the motor
	 */
	public void firePhotonCannon() {
		photonCannonRelay.set(Value.kOn);
		SmartDashboard.putBoolean("Photon Cannon", true);
	}

	public void shutdownPhotonCannon() {
        photonCannonRelay.set(Value.kOff);
        SmartDashboard.putBoolean("Photon Cannon", false);
    }
	
}