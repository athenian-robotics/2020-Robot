package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ButtonDriveTest extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private DrivetrainSubsystem drivetrainSubsystem;
    private double leftMotorSpeed;
    private double rightMotorSpeed;


    public ButtonDriveTest(DrivetrainSubsystem drivetrainSubsystem, double leftMotorSpeed, double rightMotorSpeed) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.leftMotorSpeed = leftMotorSpeed;
        this.rightMotorSpeed = rightMotorSpeed;



    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Timer.delay(0.1);
        drivetrainSubsystem.tankDrive(0, 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainSubsystem.tankDrive(leftMotorSpeed, rightMotorSpeed);
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.tankDrive(0, 0);
        System.out.println("END");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}