package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ChangeIntakeMode extends CommandBase {

    private final IntakeSubsystem intake;

    public ChangeIntakeMode(IntakeSubsystem intakeSubsystem){
        this.intake = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        if(intake.isRunning) {
            intake.setSpeed(0);
        } else {
            intake.setSpeed(0.4);
        }
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
        return false;
    }
}