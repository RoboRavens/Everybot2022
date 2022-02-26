// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainDriveForDurationCommand extends CommandBase {
  private DrivetrainSubsystem _drivetrain;
  private Timer _driveTimer = new Timer();
  private double _drivePower;
  private double _duration;

  /** Creates a new IntakeEjectForDurationCommand. */
  public DrivetrainDriveForDurationCommand(DrivetrainSubsystem drivetrain, double drivePower, double duration) {
    _drivetrain = drivetrain;
    _drivePower = drivePower;
    _duration = duration;

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _driveTimer.reset();
    _driveTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _drivetrain.driveForward(_drivePower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean timeHasElapsed = false;

    if (_driveTimer.get() >= _duration) {
      timeHasElapsed = true;
    }

    return timeHasElapsed;
  }
}
