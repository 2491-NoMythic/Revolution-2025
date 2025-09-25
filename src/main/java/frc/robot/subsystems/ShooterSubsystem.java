// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class ShooterSubsystem extends SubsystemBase{
    TalonFX leftShooterMotor;
    TalonFX rightShooterMotor;
    TalonFXConfigurator ShooterConfigurator;
    CurrentLimitsConfigs currentLimitConfigs;


    public ShooterSubsystem() {

        currentLimitConfigs = new CurrentLimitsConfigs();
        // setting the motors
        leftShooterMotor = new TalonFX(Constants.Shooter.LeftShooterMotorID);
        rightShooterMotor = new TalonFX(Constants.Shooter.RightShooterMotorID);
        leftShooterMotor.setNeutralMode(NeutralModeValue.Coast);
        rightShooterMotor.setNeutralMode(NeutralModeValue.Coast);
        rightShooterMotor.setControl(new Follower(4, true));

        // PID tuning
        ShooterConfigurator = leftShooterMotor.getConfigurator();
        ShooterConfigurator.apply(
                new Slot0Configs().
                withKP(Constants.Shooter.Shooter_kP).
                withKI(Constants.Shooter.Shooter_kI).
                withKD(Constants.Shooter.Shooter_kD));
    }
    public void currentLimit(double supplyLimit, double statorLimit) {
        currentLimitConfigs.SupplyCurrentLimit = supplyLimit;
		currentLimitConfigs.StatorCurrentLimit = statorLimit;
        ShooterConfigurator.apply(currentLimitConfigs);
    }

    public void ShooterOn(double shooterSpeed, double supplyLimit, double statorLimit) {
        leftShooterMotor.set(shooterSpeed);
        currentLimit(supplyLimit, statorLimit);
    }

    public void shooterOff() {
        leftShooterMotor.set(0);
    }

}
