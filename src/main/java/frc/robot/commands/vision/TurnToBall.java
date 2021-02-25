package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem limelight;
    private final DrivetrainSubsystem drivetrain;
    double setpoint;

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public TurnToBall(LimeLightSubsystem limelight, DrivetrainSubsystem drivetrain) {
        this.limelight = limelight;
        addRequirements(this.limelight);
        this.drivetrain = drivetrain;
        addRequirements(this.drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        double[] list = this.limelight.grabValues();
        double angleToTurn = list[3];

        if (list[0] == 1) { //if there's a valid target
            setpoint = drivetrain.getGyroAngle() + angleToTurn;
        } else {
            end(true); //if there is no valid target don't do anything
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double power = 0.01*(setpoint-drivetrain.getGyroAngle());
        drivetrain.tankDriveTurn(power, -power);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
        drivetrain.tankDriveTurn(0, 0);
        if (!interrupted) {
            System.out.println("reached ball");
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        double error = setpoint-drivetrain.getGyroAngle();
        return (error < 3 && error > -3); //if we're within 3 degrees of our target
    }
}

