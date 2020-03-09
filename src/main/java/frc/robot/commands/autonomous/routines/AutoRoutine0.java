package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.old.AutoDriveForwardDistance;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoRoutine0 extends SequentialCommandGroup {
    public AutoRoutine0(DrivetrainSubsystem drivetrain) {
        addRequirements(drivetrain);
        addCommands(
                new AutoDriveForwardDistance(drivetrain, 0.5)
        );

    }


}
