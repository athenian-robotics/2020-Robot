package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunTelescopeRight extends CommandBase{
    private final ClimberSubsystem climb;

    public RunTelescopeRight(ClimberSubsystem climberSubsystem){
        this.climb = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {
//turn on
    }

    // Called every time the scheduler runs while the command is scheduled.
//    @Override
//    public void execute() {
//
//    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //turn oiff
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
