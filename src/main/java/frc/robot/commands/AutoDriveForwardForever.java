package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

import static frc.robot.Constants.DriveConstants.speedScale;

public class AutoDriveForwardForever extends CommandBase {
    private final DrivetrainSubsystem drivetrain;


    public AutoDriveForwardForever(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void initialize() {
        drivetrain.tankDrive(speedScale, speedScale);
    }

    public void execute() {
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

}
