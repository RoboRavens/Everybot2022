
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DrivetrainSubsystem extends SubsystemBase {
  // private final CANSparkMax leftMotorFront = new CANSparkMax()

// private final PWMSparkMax leftMotorFront = new PWMSparkMax(0)

private final PWMSparkMax leftMotorFront = new PWMSparkMax(RobotMap.DRIVE_MOTOR_LEFT_FRONT);
private final PWMSparkMax leftMotorRear = new PWMSparkMax(RobotMap.DRIVE_MOTOR_LEFT_REAR);
private final PWMSparkMax rightMotorFront = new PWMSparkMax(RobotMap.DRIVE_MOTOR_RIGHT_FRONT);
private final PWMSparkMax rightMotorRear = new PWMSparkMax(RobotMap.DRIVE_MOTOR_RIGHT_REAR);


  /*
  private final Spark leftMotorFront = new Spark(RobotMap.DRIVE_MOTOR_LEFT_FRONT);
  private final Spark leftMotorRear = new Spark(RobotMap.DRIVE_MOTOR_LEFT_REAR);
  private final Spark rightMotorFront = new Spark(RobotMap.DRIVE_MOTOR_RIGHT_FRONT);
  private final Spark rightMotorRear = new Spark(RobotMap.DRIVE_MOTOR_RIGHT_REAR);
*/

  /** Creates a new ExampleSubsystem. */
  public DrivetrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double forwardPower, double turnPower) {
    double driveLeftPower = forwardPower - turnPower;
    double driveRightPower = forwardPower + turnPower;

    
    leftMotorFront.set(driveLeftPower * -1);
    leftMotorRear.set(driveLeftPower * -1);
    rightMotorFront.set(driveRightPower);
    rightMotorRear.set(driveRightPower); 
    
  }

  // Drive forward by calling the drive method with zero turning.
  public void driveForward(double power) {
    this.arcadeDrive(power, 0);
  }

  public void stop() {
    this.arcadeDrive(0, 0);
  }
}