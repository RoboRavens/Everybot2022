// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Calibrations;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainTurnDegreesCommand extends CommandBase {
  private double _startingOrientation;
  private double _targetOrientation;
  private double _currentOrientation;
  private double _currentAngleError;
  private DrivetrainSubsystem _drivetrain;

  public DrivetrainTurnDegreesCommand(DrivetrainSubsystem drivetrain, double degreesToTurn) {
    addRequirements(_drivetrain);
    
    _drivetrain = drivetrain;
    _startingOrientation = _drivetrain.getOrientation() % 360;
    _currentOrientation = _startingOrientation;

    _targetOrientation = _startingOrientation + degreesToTurn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // This is NOT the most optimized way to solve this, but it works
    // as a starter method for students who have not yet been introduced
    // to more advanced methods such as PID or motion profiling.

    // Get the current orientation because it will change during execution.
    _currentOrientation = _drivetrain.getOrientation() % 360;

    // Determine the error between the current and target orientations.
    _currentAngleError = _targetOrientation - _currentOrientation;

    // Multiply the error by a constant to get a power value with which to turn your robot.
    // The larger the constant, the faster your robot will turn, but the more it will likely
    // overshoot the target. If the value is too small, it may not fully reach the target orientation.
    // 0.03 is a decent starting point but you'll want to test and raise or lower the value accordingly.
    // More advanced control methods such as those mentioned above can eliminate this problem.
    double gyroPower = _currentAngleError * Calibrations.GRYO_ADJUSTMENT_SCALE_FACTOR;

    // Run arcade drive with no forward power, but with the gyro power for turning.
    _drivetrain.arcadeDrive(0, gyroPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Turn the drivetrain off.
    _drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // For this method's isFinished, the easiest thing to do is check if the
    // current orientation is within a margin of error with the desired orientation.
    // Note that with such an implementation, the robot will likely still have
    // a small amount of turning momentum after isFinished returns true,
    // so it may be advisable to wait a small amount of time before running the next command.
    // You could alternatively handle that as part of a more advanced isFinished method.
    boolean newOrientationReached = false;
    
    // Take the absolute value of the current error because it could be positive or negative.
    // Then compare it to the threshold, and if it's less than the the threshold, that means we're aligned.
    if (Math.abs(_currentAngleError) <= Calibrations.GYRO_TURN_DEGREES_THRESHOLD) {
      newOrientationReached = true;
    }
    
    return newOrientationReached;
  }
}
