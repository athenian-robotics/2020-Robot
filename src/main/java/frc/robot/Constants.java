/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
        public static double speedScale = 0.6;

        public static double EncoderDistancePerPulse = 1.0/2048.0;

        public static double minDrivePower = 0.32;
    }

    public static final class OIConstants {
        public static final int xboxControllerPort = 0;
        public static final int fightStickPort = 1;
    }

    public static final class MechanismConstants {
        public static final int shooterMotorPort = 4;
    }

}