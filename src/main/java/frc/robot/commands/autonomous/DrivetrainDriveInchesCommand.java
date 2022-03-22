// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainDriveInchesCommand extends CommandBase {
  DrivetrainSubsystem _drivetrain;
  double _inchesToTravel;
  double _startingEncoderInches;
  double _targetEncoderInches;
  double _power;
  int _direction;

  /*
  This command will drive the robot some number of inches. It will slightly overshoot the target because
  it will not stop until the robot reaches the destination, at which point the robot will still have a small
  amount of momentum. There are more advanced ways that this could be solved that would fix this problem,
  such as PID or motion profiling, but this is a good introduction to using an encoder to measure distance. */
  public DrivetrainDriveInchesCommand(DrivetrainSubsystem drivetrain, double inches, double power, int direction) {
    addRequirements(drivetrain);
    _drivetrain = drivetrain;
    _power = power;
    _direction = direction;
    _inchesToTravel = inches; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Initialize the target inches now in case the encoder values change between
    // the command being created (which may happen on robot boot) and when autonomous starts
    // (e.g. you power on the robot, and then push it on the floor while setting it up before auto)
    _startingEncoderInches = _drivetrain.getEncoderInches();
    _targetEncoderInches = _startingEncoderInches + _inchesToTravel;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Direction is either 1 or -1, so just multiple by it. Don't turn.
    _drivetrain.arcadeDrive(_power * _direction, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Turn off the drivetrain.
    _drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // For this isFinished method, simply check if the robot has traveled the required distance.
    // Note that with this implementation, if your robot is driving in the wrong direction,
    // isFinished will still return true since it's looking at total displacement.
    boolean isFinished = false;

    double distanceTraveled = Math.abs(_drivetrain.getEncoderInches() - _startingEncoderInches);
    if (distanceTraveled >= _inchesToTravel) {
      isFinished = true;
    }

    return isFinished;
  }
}
