// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeEjectForDurationCommand extends CommandBase {
  private IntakeSubsystem _intake;
  private Timer _intakeEjectTimer = new Timer();
  private double _duration;

  /** Creates a new IntakeEjectForDurationCommand. */
  public IntakeEjectForDurationCommand(IntakeSubsystem intake, double duration) {
    _intake = intake;
    _duration = duration;

    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _intakeEjectTimer.reset();
    _intakeEjectTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _intake.eject();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean timeHasElapsed = false;

    if (_intakeEjectTimer.get() >= _duration) {
      timeHasElapsed = true;
    }

    return timeHasElapsed;
  }
}
