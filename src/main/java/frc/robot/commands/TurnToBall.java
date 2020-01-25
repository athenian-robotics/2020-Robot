package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem subsystem;

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public TurnToBall(LimeLightSubsystem limelight) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.subsystem = limelight;
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        double[] list = this.subsystem.grabValues();
        SmartDashboard.putNumber("turntoball tv", list[0]);
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

