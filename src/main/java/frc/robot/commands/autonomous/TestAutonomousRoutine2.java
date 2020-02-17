package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.outtake.ShootLowGoal;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TestAutonomousRoutine2 extends SequentialCommandGroup {

    DrivetrainSubsystem drivetrain;
    //ShooterSubsystem shooter;

    public TestAutonomousRoutine2 (DrivetrainSubsystem drivetrain){
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        addCommands(
                new AutoDriveForwardUltrasonic(drivetrain, 15),
                //new ShootLowGoal(shooter),
                new AutoDriveForwardDistance(drivetrain, -3.5));
    }

}
