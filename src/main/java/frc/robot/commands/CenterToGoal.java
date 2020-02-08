package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;


public class CenterToGoal extends SequentialCommandGroup {

    public CenterToGoal(LimeLightSubsystem limelight, DrivetrainSubsystem drivetrain) {
        limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
        double[] list = limelight.grabValues();
        double angleHalfToGoal = Math.atan((list[6] / 2) / list[5]);

        addCommands(new AutoTurnAngle(drivetrain, angleHalfToGoal), new AutoDriveForwardDistance(drivetrain, (Math.sin(angleHalfToGoal) * list[6]) / 36), new AutoTurnAngle(drivetrain, -angleHalfToGoal));
    }
}