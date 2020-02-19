package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ShuffleboardData;
import frc.robot.subsystems.DrivetrainSubsystem;

import static frc.robot.Constants.DriveConstants.speedScale;


public class FastTurnSpeed extends CommandBase {
    DrivetrainSubsystem drivetrain;
    private ShuffleboardData shuffleData;

    public FastTurnSpeed(DrivetrainSubsystem drivetrain, ShuffleboardData shuffeData){
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.shuffleData = shuffeData;
    }

    @Override
    public void initialize() {
        DrivetrainSubsystem.maxDriverSpeed = DrivetrainSubsystem.maxDriverSpeed > 0 ? speedScale : -speedScale;
        shuffleData.setSpeedModeFast();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}