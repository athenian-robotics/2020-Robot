//package frc.robot.commands;
//
//import edu.wpi.first.wpilibj.controller.PIDController;
//import edu.wpi.first.wpilibj.controller.RamseteController;
//import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
//import edu.wpi.first.wpilibj.geometry.Pose2d;
//import edu.wpi.first.wpilibj.geometry.Rotation2d;
//import edu.wpi.first.wpilibj.geometry.Translation2d;
//import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
//import edu.wpi.first.wpilibj.trajectory.Trajectory;
//import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
//import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
//import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.RamseteCommand;
//import frc.robot.subsystems.AutonomousDrivetrainSubsystem;
//
//import java.util.List;
//
//public class FollowTrajectory extends CommandBase {
//    public static final double ksVolts = 1.31;
//    public static final double kvVoltSecondsPerMeter = 1.98;
//    public static final double kaVoltSecondsSquaredPerMeter = 0.156;
//
//    public static final double kPDriveVel = 0.538;
//
//    public static final double kTrackwidthMeters = 0.65;
//    public static final DifferentialDriveKinematics kDriveKinematics =
//            new DifferentialDriveKinematics(kTrackwidthMeters);
//
//    public static final double kMaxSpeedMetersPerSecond = 1;
//    public static final double kMaxAccelerationMetersPerSecondSquared = 1;
//
//    public static final double kRamseteB = 2;
//    public static final double kRamseteZeta = 0.7;
//
//    PIDController pid = new PIDController(0.39, 0.0, 0.01);
//
//    private final AutonomousDrivetrainSubsystem drivetrain;
//
//
//    public FollowTrajectory(AutonomousDrivetrainSubsystem drivetrain) {
//        this.drivetrain = drivetrain;
//        pid.setTolerance(0.5);
//        addRequirements(drivetrain);
//    }
//
//    public void initialize() {
//    }
//
//    public void execute() {
//    }
//
//    public boolean isFinished() {
//        drivetrain.tankDriveVolts(0,0);
//        return false;
//    }
//
//    public void end(boolean interrupted) {
//
//        System.out.println("Reached setpoint");
//    }
//    public Command ExampleAutonomousCommand() {
//
//        // Create a voltage constraint to ensure we don't accelerate too fast
//        var autoVoltageConstraint =
//                new DifferentialDriveVoltageConstraint(
//                        new SimpleMotorFeedforward(ksVolts,
//                                kvVoltSecondsPerMeter,
//                                kaVoltSecondsSquaredPerMeter),
//                        kDriveKinematics,
//                        10);
//
//        // Create config for trajectory
//        TrajectoryConfig config =
//                new TrajectoryConfig(kMaxSpeedMetersPerSecond,
//                        kMaxAccelerationMetersPerSecondSquared)
//                        // Add kinematics to ensure max speed is actually obeyed
//                        .setKinematics(kDriveKinematics)
//                        // Apply the voltage constraint
//                        .addConstraint(autoVoltageConstraint);
//
//        // An example trajectory to follow.  All units in meters.
//        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
//                // Start at the origin facing the +X direction
//                new Pose2d(0, 0, new Rotation2d(0)),
//                // Pass through these two interior waypoints, making an 's' curve path
//                List.of(
//                        new Translation2d(1, 1),
//                        new Translation2d(2, -1)
//                ),
//                // End 3 meters straight ahead of where we started, facing forward
//                new Pose2d(3, 0, new Rotation2d(0)),
//                // Pass config
//                config
//        );
//
//        RamseteCommand ramseteCommand = new RamseteCommand(
//                exampleTrajectory,
//                drivetrain::getPose,
//                new RamseteController(kRamseteB, kRamseteZeta),
//                new SimpleMotorFeedforward(ksVolts,
//                        kvVoltSecondsPerMeter,
//                        kaVoltSecondsSquaredPerMeter),
//                kDriveKinematics,
//                drivetrain::getWheelSpeeds,
//                new PIDController(kPDriveVel, 0, 0),
//                new PIDController(kPDriveVel, 0, 0),
//                // RamseteCommand passes volts to the callback
//                drivetrain::tankDriveVolts,
//                drivetrain
//        );
//
//        // Run path following command, then stop at the end.
//        return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
//    }
//
//}
