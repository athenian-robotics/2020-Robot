package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.lib.Direction;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ButtonDriveTestCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Direction direction;
    private final DrivetrainSubsystem drivetrainSubsystem;


    public ButtonDriveTestCommand(Direction direction, DrivetrainSubsystem drivetrainSubsystem, RobotContainer robotContainer) {
        this.direction = direction;
        this.drivetrainSubsystem = drivetrainSubsystem;
        robotContainer.setNewCommand(this);


        // Use addRequirements() here to declare subsystem dependencies.

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrainSubsystem.tankDrive(0, 0);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        switch (direction) {
            case FORWARD:
                drivetrainSubsystem.tankDrive(0.5, 0.5);
                System.out.println("XXX HAS BEEN PRESSED");
                break;

            case STOP:
                drivetrainSubsystem.tankDrive(0, 0);
                System.out.println("AAA HAS BEEN PRESSED");

                break;
            case REVERSE:
                drivetrainSubsystem.tankDrive(-0.5, -0.5);
                System.out.println("BBB HAS BEEN PRESSED");
                break;
            default:
                System.out.println("BROKEN");
                break;
        }

    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("EEEEND");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}