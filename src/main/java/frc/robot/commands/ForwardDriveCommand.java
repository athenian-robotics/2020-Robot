package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class ForwardDriveCommand extends CommandBase {
    private final Drivetrain drivetrain;
    double leftSpeed;
    double rightSpeed;


    public ForwardDriveCommand(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    public void initialize() {

    }

    public void execute() {

        System.out.println("Hello world! DriveForwardCommand");
        drivetrain.tankDrive(leftSpeed * Constants.DriveConstants.speedScale, rightSpeed * Constants.DriveConstants.speedScale);
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
