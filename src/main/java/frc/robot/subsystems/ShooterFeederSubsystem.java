// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ShooterFeederSubsystem extends SubsystemBase {
  	
	CANSparkMax shooterFeederMotor;
	SparkPIDController shooterFeederController;

	public ShooterFeederSubsystem() {
		shooterFeederMotor = new CANSparkMax(Constants.ShooterFeeder.ShooterFeederMotorID, MotorType.kBrushless);
    shooterFeederMotor.restoreFactoryDefaults();
    shooterFeederController = shooterFeederMotor.getPIDController();
    shooterFeederController.setP(Constants.ShooterFeeder.ShooterFeeder_kP);
    shooterFeederController.setI(Constants.ShooterFeeder.ShooterFeeder_kI);
    shooterFeederController.setD(Constants.ShooterFeeder.ShooterFeeder_kD);
    shooterFeederController.setFF(Constants.ShooterFeeder.ShooterFeeder_kFF);
    shooterFeederController = shooterFeederMotor.getPIDController();
    shooterFeederMotor.setIdleMode(IdleMode.kCoast);
    shooterFeederMotor.setSmartCurrentLimit(25, 40, 1000);
    shooterFeederMotor.getEncoder().setPositionConversionFactor(1);
    shooterFeederMotor.burnFlash();
	}

	public void FeedingShooter(double shooterFeederSpeed){
		shooterFeederMotor.set(shooterFeederSpeed);
		SmartDashboard.putNumber("ShooterFeeder Speed", shooterFeederSpeed);
	}
    public void StopFeedingShooter(double shooterFeederSpeed){
		shooterFeederMotor.set(0);
		SmartDashboard.putNumber("ShooterFeeder Speed", shooterFeederSpeed);
	}
    public void setShooterFeederVelocity(double shooterFeederVelocity) {
       shooterFeederController.setReference(shooterFeederVelocity, CANSparkMax.ControlType.kVelocity);
  }

  
  @Override
  public void periodic() {
  }
}