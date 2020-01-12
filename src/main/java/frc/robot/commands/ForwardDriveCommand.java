package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class ForwardDriveCommand extends CommandBase {
    private final Drivetrain drivetrain;


    public ForwardDriveCommand(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void initialize() {

    }

    public void execute() {

        drivetrain.tankDrive(Constants.DriveConstants.speedScale, Constants.DriveConstants.speedScale);
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        if (interrupted) {
            drivetrain.tankDrive(0, 0);
        }

    }

}
