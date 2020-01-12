package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

import static frc.robot.Constants.DriveConstants.speedScale;

public class AutoDriveForwardForever extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;


    public AutoDriveForwardForever(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    public void initialize() {

    }

    public void execute() {
        drivetrainSubsystem.tankDrive(speedScale, speedScale);
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        drivetrainSubsystem.tankDrive(0, 0);
    }

}
