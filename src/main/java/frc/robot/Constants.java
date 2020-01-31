/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.lib.RobotType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveConstants {

        /**
         * Change these to 0, 1, 2, 3 if they aren't there
         */
        public static final int leftMotor1Port = 0;
        public static final int leftMotor2Port = 1;
        public static final int rightMotor1Port = 2;
        public static final int rightMotor2Port = 3;

        public static final int encoderLeftA = 1;
        public static final int encoderLeftB = 2;
        public static final int encoderRightA = 5;
        public static final int encoderRightB = 6;
        public static double speedScale = 1.0;

        public static double EncoderDistancePerPulse = 1.0/2048.0;

        public static double minDrivePower = 0.32;
    }
    public static final class AutonomousConstants{
        public static final double ksVolts = 0.8; //1.31;
        public static final double kvVoltSecondsPerMeter = 0.3; //1.98
        public static final double kaVoltSecondsSquaredPerMeter = 0.04; //0.156
        public static final double kPDriveVel = 0.538;
        public static final double kTrackwidthMeters = 0.65;
        public static final DifferentialDriveKinematics kDriveKinematics =
                new DifferentialDriveKinematics(kTrackwidthMeters);
        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxAccelerationMetersPerSecondSquared = 1;
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
    }
    public static final class OIConstants {
        public static final int xboxControllerPort = 0;
        public static final int fightStickPort = 1;
    }
    public static final class MechanismConstants {
        public static final int intakeMotorPort = 4;
        public static final int shooterMotorPort = 5;
        public static final int climberWinchLeftPort = 6;
        public static final int climberWinchRightPort = 7;
        public static final int climberTelescopeLeftPort = 8;
        public static final int climberTelescopeRightPort = 9;
    }
}