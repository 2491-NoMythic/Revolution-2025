// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int MainControllerPort = 0;
  }

  public final class Drivetrain {
    public final static int DriveTalonLeftMotor1 = 0;
    public final static int DriveTalonLeftMotor2 = 1;
    public final static int DriveTalonRightMotor1 = 2;
    public final static int DriveTalonRightMotor2 = 3;
    public static final double STATOR_LIMIT = 50;
    public static final double SUPPLY_LIMIT = 50;
    
    //PID
    public static final double Drivetrain_KP = 0.5;
    public static final double Drivetrain_KI = 0;
    public static final double Drivetrain_KD = .0;
  }

  public final class Shooter {
    public final static int LeftShooterMotorID = 4;
    public final static int RightShooterMotorID = 5;
    public final static int Servo1PwmID = 6;
    public final static int Servo2PwmID = 7;
    public final static int PhotonCannonPwmID = 8;

    public final static double ShooterEncoderTicks = 2048.0; // Encoder ticks per wheel rotation is 2048
    public final static double ShooterWheelDiameter = 4.0; // Inches
    public final static double ShooterEncoderToInches = ShooterWheelDiameter * Math.PI / ShooterEncoderTicks; // Makes number of inches
    public final static double ShooterEncoderVelocityToRPS = 1.0 / ShooterEncoderTicks * 10;
    public final static double setHoodPosition = 190;
    public final static double ShooterSpeed = .5;

    //PID
    public static final double Shooter_kP = 0.00002;
    public static final double Shooter_kI = 0;
    public static final double Shooter_kD = 0.0008;
  }

  public final class SpinDexer {
    public final static int SpindDexerMotorID = 9;
    public final static double RotateClockWise = .33; 
		public final static double RotateCounterClockWise = -.33;

    //PID
    public static final double SpinDexer_kP = 0;
    public static final double SpinDexer_kI = 0;
    public static final double SpinDexer_kD = 0;
    public static final double SpinDexer_kFF = 0;
  }

  public final class Intake {
    public final static int IntakeMotorID = 10;
    public final static double IntakeSpeed = .25;
    public final static double OuttakeSpeed = -.25;

    //PID
    public static final double Intake_kP = 0;
    public static final double Intake_kI = 0;
    public static final double Intake_kD = 0;
    public static final double Intake_kFF = 0;
  }

  public final class ShooterFeeder {
    public final static int ShooterFeederMotorID = 11;
    public final static double ShooterFeeding = .5;

    //PID
    public static final double ShooterFeeder_kP = 0;
    public static final double ShooterFeeder_kI = 0;
    public static final double ShooterFeeder_kD = 0;
    public static final double ShooterFeeder_kFF = 0;
  }

  public final class AntiJamer {
    public final static int AntiJamerMotorID = 12;
    public final static double RunAntiJamer = .25;

    //PID
    public static final double Antijamer_kP = 0;
    public static final double Antijamer_kI = 0;
    public static final double Antijamer_kD = 0;
    public static final double Antijamer_kFF = 0;
  }
}
