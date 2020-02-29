package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoDriveForwardUltrasonic;
import frc.robot.commands.autonomous.components.AutoDumperUp;
import frc.robot.commands.autonomous.components.AutoForwardDistance;
import frc.robot.commands.autonomous.components.AutoShooterTime;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRoutineDriveBackShootCross extends SequentialCommandGroup {
    public AutoRoutineDriveBackShootCross(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter) {
        addRequirements(drivetrain, shooter);
        addCommands(
                new AutoDumperUp(shooter, 300),
                new AutoDriveForwardUltrasonic(drivetrain, 8),
                new AutoShooterTime(shooter, 1000),
                new AutoForwardDistance(drivetrain, 2.8)
        );
    }
}
