// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Calibrations;
import frc.robot.subsystems.ArmSubsystem;

public class ArmRaiseFullyCommand extends CommandBase {
  private ArmSubsystem _arm;
  private Timer _armRaiseTimer = new Timer();

  /** Creates a new ArmExtendFullyCommand. */
  public ArmRaiseFullyCommand(ArmSubsystem arm) {
    _arm = arm;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _armRaiseTimer.reset();
    _armRaiseTimer.start();
    _arm.setUp(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _arm.raise();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _arm.hold();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean timeHasElapsed = false;

    if (_armRaiseTimer.get() >= Calibrations.ARM_RAISE_TIME_SECONDS) {
      timeHasElapsed = true;
    }

    return timeHasElapsed;
  }
}
