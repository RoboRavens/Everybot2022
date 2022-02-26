// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Calibrations {
    // Intake
    public static final double INTAKE_COLLECT_POWER = 1;
    public static final double INTAKE_EJECT_POWER = -1;

    // Arm
    public static final double ARM_HOLD_UP_POWER = .1;
    public static final double ARM_HOLD_DOWN_POWER = -.13;
    public static final double ARM_RAISE_POWER = .5;
    public static final double ARM_LOWER_POWER = -.5;
    public static final double ARM_RAISE_TIME_SECONDS = .5;
    public static final double ARM_LOWER_TIME_SECONDS = .35;
    
    // Autonomous
    public static final double AUTONOMOUS_INTAKE_EJECT_DURATION = 3;
    public static final double AUTONOMOUS_DRIVE_DURATION = 3;
    public static final double AUTONOMOUS_DRIVE_POWER = -.3;
}
