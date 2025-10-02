// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ShooterFeederSubsystem extends SubsystemBase {
  	
	SparkMax shooterFeederMotor;
	SparkClosedLoopController shooterFeederController;
  SparkMaxConfig shooterFeederConfig;


	public ShooterFeederSubsystem() {
		shooterFeederMotor = new SparkMax(Constants.ShooterFeeder.ShooterFeederMotorID, MotorType.kBrushless);
    shooterFeederConfig = new SparkMaxConfig();

    shooterFeederConfig.inverted(false);
    shooterFeederConfig.idleMode(IdleMode.kCoast);
    shooterFeederConfig.encoder.positionConversionFactor(1);
    shooterFeederConfig.encoder.velocityConversionFactor(1);
    shooterFeederConfig.closedLoop.feedbackSensor(FeedbackSensor.kNoSensor);
    shooterFeederConfig.closedLoop.pid(Constants.ShooterFeeder.ShooterFeeder_kP, Constants.ShooterFeeder.ShooterFeeder_kI, Constants.ShooterFeeder.ShooterFeeder_kD);
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
       shooterFeederController.setReference(shooterFeederVelocity, SparkMax.ControlType.kVelocity);
  }

  
  @Override
  public void periodic() {
  }
}