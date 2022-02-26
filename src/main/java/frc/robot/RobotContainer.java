// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmHoldCommand;
import frc.robot.commands.ArmLowerFullyCommand;
import frc.robot.commands.ArmRaiseFullyCommand;
import frc.robot.commands.DrivetrainHumanControlCommand;
import frc.robot.commands.IntakeCollectCommand;
import frc.robot.commands.IntakeEjectCommand;
import frc.robot.commands.IntakeStopCommand;
import frc.robot.commands.autonomous.AutonomousScorePreloadedBallCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem _drivetrain = new DrivetrainSubsystem();
  private final ArmSubsystem _arm = new ArmSubsystem();
  private final IntakeSubsystem _intake = new IntakeSubsystem();
  private final Joystick _joystick = new Joystick(RobotMap.JOYSTICK_CHANNEL);

  private final DrivetrainHumanControlCommand _drivetrainHumanControlCommand = new DrivetrainHumanControlCommand(_drivetrain, _joystick);
  
  private final ArmRaiseFullyCommand _armRaiseFullyCommand = new ArmRaiseFullyCommand(_arm);
  private final ArmLowerFullyCommand _armLowerFullyCommand = new ArmLowerFullyCommand(_arm);
  private final ArmHoldCommand _armHoldCommand = new ArmHoldCommand(_arm);

  private final IntakeCollectCommand _intakeCollectCommand = new IntakeCollectCommand(_intake);
  private final IntakeEjectCommand _intakeEjectCommand = new IntakeEjectCommand(_intake);
  private final IntakeStopCommand _intakeStopCommand  = new IntakeStopCommand(_intake);

  private final AutonomousScorePreloadedBallCommandGroup _autonomousScorePreloadedBallCommandGroup = new AutonomousScorePreloadedBallCommandGroup(_drivetrain, _intake);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    _drivetrain.setDefaultCommand(_drivetrainHumanControlCommand);
    _arm.setDefaultCommand(_armHoldCommand);
    _intake.setDefaultCommand(_intakeStopCommand);

    SmartDashboard.putBoolean("Go For Auto", false);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(_joystick, RobotMap.ARM_RAISE_BUTTON).whenPressed(_armRaiseFullyCommand);
    new JoystickButton(_joystick, RobotMap.ARM_LOWER_BUTTON).whenPressed(_armLowerFullyCommand);
    new JoystickButton(_joystick, RobotMap.INTAKE_COLLECT_BUTTON).whileHeld(_intakeCollectCommand);
    new JoystickButton(_joystick, RobotMap.INTAKE_EJECT_BUTTON).whileHeld(_intakeEjectCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Command autonomousCommand = null;

    boolean goForAuto = SmartDashboard.getBoolean("Go For Auto", false);
    
    if (goForAuto == true) {
      autonomousCommand = _autonomousScorePreloadedBallCommandGroup;
    }
    
    return autonomousCommand;
  }
}
