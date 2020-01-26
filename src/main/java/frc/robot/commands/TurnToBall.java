package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem limelight;
    private final DrivetrainSubsystem drivetrain;

    Timer driveTimer = new Timer();
    double tolerance;
    double setpoint;
    double Kp = 0.004;
    double Ki = 0.0;
    double Kd = 0.001;

    PIDController pid = new PIDController(Kp, Ki, Kd);

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public TurnToBall(LimeLightSubsystem limelight, DrivetrainSubsystem drivetrain) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.limelight = limelight;
        addRequirements(this.limelight);
        this.drivetrain = drivetrain;
        addRequirements(this.drivetrain);

        this.tolerance = 0.5;
        pid.setTolerance(tolerance);
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        double[] list = this.limelight.grabValues();
        double angleToTurn = list[3];
        SmartDashboard.putNumber("turntoball tx", angleToTurn);

        setpoint = drivetrain.getGyroAngle() + angleToTurn + tolerance;
        pid.setSetpoint(setpoint);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double power = pid.calculate(drivetrain.getGyroAngle());
        drivetrain.tankDrive(power, -power);
        SmartDashboard.putNumber("Angle PID Error:", pid.getPositionError());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (pid.getPositionError() < 5) {
            return true;
        } else {
            return false;
        }
    }
}

