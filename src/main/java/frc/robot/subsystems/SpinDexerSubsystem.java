// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class SpinDexerSubsystem extends SubsystemBase{
	CANSparkMax spinDexerMotor;
	SparkPIDController spinDexerController;

	public SpinDexerSubsystem() {
		spinDexerMotor = new CANSparkMax(Constants.SpinDexer.SpindDexerMotorID, MotorType.kBrushless);
		spinDexerMotor.restoreFactoryDefaults();
		spinDexerController = spinDexerMotor.getPIDController();
		spinDexerController.setP(Constants.SpinDexer.SpinDexer_kP);
		spinDexerController.setI(Constants.SpinDexer.SpinDexer_kI);
		spinDexerController.setD(Constants.SpinDexer.SpinDexer_kD);
		spinDexerController.setFF(Constants.SpinDexer.SpinDexer_kFF);
		spinDexerController = spinDexerMotor.getPIDController();
		spinDexerMotor.setIdleMode(IdleMode.kCoast);
		spinDexerMotor.setSmartCurrentLimit(25, 40, 1000);
		spinDexerMotor.getEncoder().setPositionConversionFactor(1);
		spinDexerMotor.burnFlash();
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
		spinDexerController.setReference(spinDexerVelocity, CANSparkMax.ControlType.kVelocity);
	  }
	@Override
	public void periodic() {
	}
}
