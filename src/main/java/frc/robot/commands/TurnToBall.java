package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem limelight;
    private final DrivetrainSubsystem drivetrain;

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public TurnToBall(LimeLightSubsystem limelight, DrivetrainSubsystem drivetrain) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.limelight = limelight;
        addRequirements(this.limelight);
        this.drivetrain = drivetrain;
        addRequirements(this.drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        double[] list = this.limelight.grabValues();
        double angleToTurn = list[3];
        SmartDashboard.putNumber("turntoball tx", angleToTurn);
        new AutoTurnAngle(this.drivetrain, angleToTurn);
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

