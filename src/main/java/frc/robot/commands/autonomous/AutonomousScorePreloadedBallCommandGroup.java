// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Calibrations;
import frc.robot.commands.DrivetrainDriveForDurationCommand;
import frc.robot.commands.IntakeEjectForDurationCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousScorePreloadedBallCommandGroup extends SequentialCommandGroup {
  /** Creates a new AutonomousScorePreloadedBallCommandGroup. */
  public AutonomousScorePreloadedBallCommandGroup(DrivetrainSubsystem drivetrain, IntakeSubsystem intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new IntakeEjectForDurationCommand(intake, Calibrations.AUTONOMOUS_INTAKE_EJECT_DURATION),
      new DrivetrainDriveForDurationCommand(drivetrain, Calibrations.AUTONOMOUS_DRIVE_POWER, Calibrations.AUTONOMOUS_DRIVE_DURATION)
    );
  }
}
