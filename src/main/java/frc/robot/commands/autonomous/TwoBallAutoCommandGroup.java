// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Calibrations;
import frc.robot.commands.ArmLowerFullyCommand;
import frc.robot.commands.ArmRaiseFullyCommand;
import frc.robot.commands.IntakeCollectCommand;
import frc.robot.commands.IntakeEjectForDurationCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAutoCommandGroup extends SequentialCommandGroup {
  /** Creates a new TwoBallAutoCommandGroup. */
  public TwoBallAutoCommandGroup(DrivetrainSubsystem drivetrain, ArmSubsystem arm, IntakeSubsystem intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // Wait a half second for the arm to fully raise, in case it has settled during pre-match.
      new WaitCommand(.5),

      // Eject the intake for one second.
      new IntakeEjectForDurationCommand(intake, 1),

      // Start driving backwards away from the goal.
      new DrivetrainDriveInchesCommand(drivetrain, 10, Calibrations.AUTONOMOUS_DRIVING_POWER, Calibrations.DRIVING_BACKWARD),

      // A parallel command group will execute its commands at the same time instead of one after another.
      // After all the commands finish, the command group will finish and the original sequence will resume.
      new ParallelCommandGroup(
        // Lower the arm and turn 180 degrees.
        new ArmLowerFullyCommand(arm),
        new DrivetrainTurnDegreesCommand(drivetrain, 180)
      ),

      // A parallel *deadline* group is like a normal parallel group, except instead of waiting for all the commands
      // in the group to end, whenever the first command given ends, the whole group ends.
      // In this case that is what you want because the intake collect command will run indefinitely if nothing
      // else cancels it. So the deadline condition is the drive condition. The intake runs while the robot is
      // approaching the ball, but when the drivetrain command finishes, it also terminates the intake command.
      new ParallelDeadlineGroup(
        // Drive the rest of the way to the second ball. YOU WILL NEED TO FIGURE OUT THE PROPER DISTANCE FOR YOURSELF.  
        new DrivetrainDriveInchesCommand(drivetrain, 50, Calibrations.AUTONOMOUS_DRIVING_POWER, Calibrations.DRIVING_FORWARD),
        // Turn the intake on.
        new IntakeCollectCommand(intake)
      ),

      // Now we have the second ball, and it's time to reverse the steps to get back to the goal and score.
      new ParallelCommandGroup(
        // Lower the arm and turn 180 degrees.
        new ArmRaiseFullyCommand(arm),
        new DrivetrainTurnDegreesCommand(drivetrain, 180)
      ),

      // This will drive back to the goal. The ".withTimeout" portion of the command adds a timeout - if this number of seconds passes
      // before the command's isFinished returns true, the command will end anyway.
      // This can be useful because the robot might, for example, hit the wall of the goal one inch before its target, and might never
      // drive the final inch.
      new DrivetrainDriveInchesCommand(drivetrain, 60, Calibrations.AUTONOMOUS_DRIVING_POWER, Calibrations.DRIVING_FORWARD).withTimeout(5),

      // Finally, once the robot is at the goal, eject the ball.
      new IntakeEjectForDurationCommand(intake, 1),

      // As a last step, you may want to drive away from the goal to be clear of the tarmac when auto ends.
      new DrivetrainDriveInchesCommand(drivetrain,80, Calibrations.AUTONOMOUS_DRIVING_POWER, Calibrations.DRIVING_BACKWARD).withTimeout(5)
    );
  }
}
