package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class FollowTrajectory extends CommandBase {
    DrivetrainSubsystem drivetrain;
    public static final double ksVolts = 1.31;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.156;

    public static final double kPDriveVel = 0.538;

    public static final double kTrackwidthMeters = 0.65;
    public static final DifferentialDriveKinematics kDriveKinematics =
            new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    PIDController pid = new PIDController(0.39, 0.0, 0.01);


    public FollowTrajectory(DrivetrainSubsystem drivetrain) {
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {

    }

    public void execute() {

    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}
