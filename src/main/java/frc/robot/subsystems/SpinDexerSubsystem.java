// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import java.io.ObjectInputFilter.Config;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.SpinDexer;

/** Add your docs here. */
public class SpinDexerSubsystem extends SubsystemBase{
	SparkMax spinDexerMotor;
	SparkClosedLoopController spinDexerController;
	SparkMaxConfig spinDexerConfig;

	public SpinDexerSubsystem() {
		spinDexerMotor = new SparkMax(Constants.SpinDexer.SpindDexerMotorID, MotorType.kBrushless);
		spinDexerConfig = new SparkMaxConfig();

		spinDexerConfig.inverted(true);
		spinDexerConfig.idleMode(IdleMode.kCoast);
		spinDexerConfig.encoder.positionConversionFactor(1);
		spinDexerConfig.encoder.velocityConversionFactor(1);
		spinDexerConfig.closedLoop.feedbackSensor(FeedbackSensor.kNoSensor);
		spinDexerConfig.closedLoop.pid(Constants.SpinDexer.SpinDexer_kP, Constants.SpinDexer.SpinDexer_kI, Constants.SpinDexer.SpinDexer_kD);
	}

	public void rotateClockWise(double spinDexerSpeed) {
		spinDexerMotor.set(spinDexerSpeed);
		SmartDashboard.putNumber("Spindexer Speed", spinDexerSpeed);
	}

	public void rotateCounterClockWise(double spinDexerSpeed) {
		spinDexerMotor.set(spinDexerSpeed);
		SmartDashboard.putNumber("Spindexer Speed", spinDexerSpeed);
	}

	public void stopRotating(double spinDexerSpeed) {
		spinDexerMotor.set(0);
	}
	public void setSpinDexerVelocity(double spinDexerVelocity) {
		spinDexerController.setReference(spinDexerVelocity, SparkMax.ControlType.kVelocity);
	  }
	@Override
	public void periodic() {
	}
}
