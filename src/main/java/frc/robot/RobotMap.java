// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class RobotMap {
    // Joysticks/controllers
    public static final int JOYSTICK_CHANNEL = 0;
    public static final int JOYSTICK_FORWARD_AXIS = 1;
    public static final int JOYSTICK_TURN_AXIS = 4;

    // Drivetrain
    public static final int DRIVE_MOTOR_LEFT_FRONT = 0;
    public static final int DRIVE_MOTOR_LEFT_REAR = 2;
    public static final int DRIVE_MOTOR_RIGHT_FRONT = 1;
    public static final int DRIVE_MOTOR_RIGHT_REAR = 3;

    // Arm
    public static final int ARM_MOTOR = 5;
    
    // Intake
    public static final int INTAKE_MOTOR = 6;

    // Button bindings
    public static final int ARM_RAISE_BUTTON = 7;
    public static final int ARM_LOWER_BUTTON = 8;
    public static final int INTAKE_COLLECT_BUTTON = 5;
    public static final int INTAKE_EJECT_BUTTON = 6;
}
