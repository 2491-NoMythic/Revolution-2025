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


public class AntiJamerSubsystem extends SubsystemBase {
  	
	SparkMax antiJamMotor;
	SparkClosedLoopController antiJamController;
  SparkMaxConfig antiJamConfig;

	public AntiJamerSubsystem() {
    antiJamMotor = new SparkMax(Constants.AntiJamer.AntiJamerMotorID, MotorType.kBrushless);
    antiJamConfig = new SparkMaxConfig();

    antiJamConfig.inverted(false);
    antiJamConfig.idleMode(IdleMode.kCoast);
    antiJamConfig.encoder.positionConversionFactor(1);
    antiJamConfig.encoder.velocityConversionFactor(1);
    antiJamConfig.closedLoop.feedbackSensor(FeedbackSensor.kNoSensor);
    antiJamConfig.closedLoop.pid(Constants.AntiJamer.Antijamer_kP, Constants.AntiJamer.Antijamer_kI, Constants.AntiJamer.Antijamer_kD);
	}

	public void RunAntiJamer(double antiJamerSpeed){
		antiJamMotor.set(antiJamerSpeed);
		SmartDashboard.putNumber("AntiJamer Speed", antiJamerSpeed);
	}
  public void StopAntiJamer(){
        antiJamMotor.set(0);
		SmartDashboard.putNumber("AntiJamer Speed", 0);
	}
  public void setVelocity(double antiJamerVelocity) {
    antiJamController.setReference(antiJamerVelocity, SparkMax.ControlType.kVelocity);
  }
  @Override
  public void periodic() {
  }
}
