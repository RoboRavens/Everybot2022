// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainHumanControlCommand extends CommandBase {
  private DrivetrainSubsystem _drivetrain;
  private Joystick _joystick;

  /** Creates a new DrivetrainHumanControlCommand. */
  public DrivetrainHumanControlCommand(DrivetrainSubsystem drivetrain, Joystick joystick) {
    _drivetrain = drivetrain;
    _joystick = joystick;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _drivetrain.arcadeDrive(_joystick.getRawAxis(RobotMap.JOYSTICK_FORWARD_AXIS), _joystick.getRawAxis(RobotMap.JOYSTICK_TURN_AXIS));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
