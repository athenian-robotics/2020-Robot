package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardTimer extends CommandBase {
    DrivetrainSubsystem drivetrainSubsystem;
    Timer driveTimer = new Timer();
    double secondsTodrive;

    public AutoDriveForwardTimer(DrivetrainSubsystem drivetrainSubsystem, Double secondsToDrive) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.secondsTodrive = secondsToDrive;

    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
    }

    public void execute() {

        if (driveTimer.get() < secondsTodrive) {
            drivetrainSubsystem.tankDrive(0.4, 0.4);
        } else {
            drivetrainSubsystem.tankDrive(0, 0);
        }

    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        drivetrainSubsystem.tankDrive(0, 0);
    }

}
