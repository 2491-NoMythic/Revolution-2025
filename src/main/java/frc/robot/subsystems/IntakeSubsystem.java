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
import pabeles.concurrency.IntOperatorTask.Max;

public class IntakeSubsystem extends SubsystemBase {

  SparkMax intakeMotor;
  SparkClosedLoopController intakeController;
  SparkMaxConfig config;
  SparkMax max;

  public IntakeSubsystem() {
    max = new SparkMax(Constants.Intake.IntakeMotorID, MotorType.kBrushless);
    config = new SparkMaxConfig();

    config.idleMode(IdleMode.kCoast);
    config.inverted(true);
    config.encoder.positionConversionFactor(1);
    config.encoder.velocityConversionFactor(1);
    config.closedLoop.feedbackSensor(FeedbackSensor.kNoSensor);
    config.closedLoop.pid(Constants.Intake.Intake_kP, Constants.Intake.Intake_kI, Constants.Intake.Intake_kD);

    max.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

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
    intakeController.setReference(intakeVelocity, SparkMax.ControlType.kVelocity);
  }

  @Override
  public void periodic() {
  }
}
