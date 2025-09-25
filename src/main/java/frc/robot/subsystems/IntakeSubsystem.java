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

public class IntakeSubsystem extends SubsystemBase {

  CANSparkMax intakeMotor;
  SparkPIDController intakeController;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(Constants.Intake.IntakeMotorID, MotorType.kBrushless);
    intakeMotor.restoreFactoryDefaults();
    intakeController = intakeMotor.getPIDController();
    intakeController.setP(Constants.Intake.Intake_kP);
    intakeController.setI(Constants.Intake.Intake_kI);
    intakeController.setD(Constants.Intake.Intake_kD);
    intakeController.setFF(Constants.Intake.Intake_kFF);
    intakeController = intakeMotor.getPIDController();
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakeMotor.setSmartCurrentLimit(25, 40, 1000);
    intakeMotor.getEncoder().setPositionConversionFactor(1);
    intakeMotor.burnFlash();
  }

  public void Intaking(double intakeSpeed) {
    intakeMotor.set(intakeSpeed);
    SmartDashboard.putNumber("Intake Speed", intakeSpeed);
  }

  public void Outtaking(double intakeSpeed) {
    intakeMotor.set(intakeSpeed);
    SmartDashboard.putNumber("Intake Speed", intakeSpeed);
  }

  public void Stopintake(double intakeSpeed) {
    intakeMotor.set(0);
    SmartDashboard.putNumber("Intake Speed", intakeSpeed);
  }

  public void setIntakeVelocity(double intakeVelocity) {
    intakeController.setReference(intakeVelocity, CANSparkMax.ControlType.kVelocity);
  }

  @Override
  public void periodic() {
  }
}
