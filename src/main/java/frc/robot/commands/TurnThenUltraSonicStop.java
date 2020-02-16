package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;


public class TurnThenUltraSonicStop extends SequentialCommandGroup {

    DrivetrainSubsystem drivetrain;

    /**
     * Turns then moves forward till a wall.
     *
     * @param drivetrain The drive subsystem this command will run on
     * @param angleToTurn Desired angle to turn
     * @param distanceToStop Desired distance from the wall to stop
     */
    public TurnThenUltraSonicStop(DrivetrainSubsystem drivetrain, double angleToTurn, double distanceToStop) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        addCommands(
                new AutoTurnAngle(drivetrain, angleToTurn),
                new AutoDriveForwardUltrasonic(drivetrain, distanceToStop)
        );
    }
}