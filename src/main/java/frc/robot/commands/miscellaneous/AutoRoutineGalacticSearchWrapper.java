package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.routines.AutoRoutineGalacticSearch;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;


public class AutoRoutineGalacticSearchWrapper extends SequentialCommandGroup {
    public AutoRoutineGalacticSearchWrapper(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, LimeLightSubsystem limelight, int ballCount) {
        addRequirements(drivetrain, intake, limelight);
        addCommands(
                new AutoRoutineGalacticSearch(drivetrain, intake, limelight, ballCount)
        );
    }
} //all other AutoRoutine commands are SequentialCommandGroups, so that is the required type for our chooser (see RobotContainer) and we need a wrapper to start the routine from SmartDashboard