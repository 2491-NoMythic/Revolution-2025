// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AntiJamerCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.ShooterFeederCommand;
import frc.robot.commands.SpinDexerCommand;
import frc.robot.subsystems.AntiJamerSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SpinDexerSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final boolean intakeExists = Preferences.getBoolean("Intake", true);
  private final boolean shooterExists = Preferences.getBoolean("Shooter", true);
  private final boolean spindexerExists = Preferences.getBoolean("SpinDexer", true);
  private final boolean shooterfeederExists = Preferences.getBoolean("ShooterFeeder", true);
  private final boolean antijamerExists = Preferences.getBoolean("AntiJamer", true);
  private final boolean safeModeOn = Preferences.getBoolean("SafeMode", true);

  private DrivetrainSubsystem driveTrainSubsystem;
  private IntakeSubsystem intake;
  private SpinDexerSubsystem spinDexer;
  private ShooterSubsystem shooter;
  private ShooterFeederSubsystem shooterFeeder;
  private AntiJamerSubsystem antijamer;
  private Joystick m_mainJoystick;

  DriveCommand defaultDriveCommand;
  OuttakeCommand outtakeCommand;

  BooleanSupplier shootingSupplier;
  BooleanSupplier intakeSupplier;
  BooleanSupplier outtakSupplier;
  DoubleSupplier moveDoubleSupplier;
  DoubleSupplier turnDoubleSupplier;
  ParallelCommandGroup intakeCommand;
  ParallelCommandGroup shootingCommand;
  double safeMultipler = 1;

  public RobotContainer() {
    Preferences.initBoolean("Intake", false);
    Preferences.initBoolean("Shooter", false);

    if (intakeExists) {intakeInst();}
    if (shooterExists) {shooterInst();}
    if (spindexerExists) {spindexerInst();}
    if (shooterfeederExists) {shooterfeederInst();}
    if (antijamerExists) {antijamerInst();}
    if (safeModeOn) {safeMultipler = 0.50;}

    m_mainJoystick = new Joystick(Constants.OperatorConstants.MainControllerPort);
    shootingSupplier = m_mainJoystick::getTrigger;
    intakeSupplier = () -> m_mainJoystick.getPOV() == 0;
    outtakSupplier = () -> m_mainJoystick.getPOV() == 180;

    driveTrainInst();
    configureBindings();

  }

  private void driveTrainInst() {
    driveTrainSubsystem = new DrivetrainSubsystem();
    defaultDriveCommand = new DriveCommand(driveTrainSubsystem,
        moveDoubleSupplier = () -> m_mainJoystick.getY()*safeMultipler,
        turnDoubleSupplier = () -> m_mainJoystick.getZ()*safeMultipler);
    driveTrainSubsystem.setDefaultCommand(defaultDriveCommand);
  }

  private void intakeInst() {
    intake = new IntakeSubsystem();

  }

  private void shooterInst() {
    shooter = new ShooterSubsystem();
  }

  private void spindexerInst() {
    spinDexer = new SpinDexerSubsystem();
  }

  private void shooterfeederInst() {
    shooterFeeder = new ShooterFeederSubsystem();
  }

  private void antijamerInst() {
    antijamer = new AntiJamerSubsystem();
  }

  private void configureBindings() {
    if (antijamerExists&&spindexerExists&&shooterExists&&shooterfeederExists){  
    
      shootingCommand = new ParallelCommandGroup(
        new SpinDexerCommand(spinDexer),
        new ShooterFeederCommand(shooterFeeder),
        new ShooterCommand(shooter),
        new AntiJamerCommand(antijamer));
      new Trigger(shootingSupplier).whileTrue(shootingCommand);
    }
    if (antijamerExists&&intakeExists&&spindexerExists) {
      
      intakeCommand = new ParallelCommandGroup(
        new IntakeCommand(intake),
        new SpinDexerCommand(spinDexer),
        new AntiJamerCommand(antijamer));

      new Trigger(intakeSupplier).whileTrue(intakeCommand);
    }
      //new Trigger(outtakSupplier).whileTrue(outtakeCommand);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
