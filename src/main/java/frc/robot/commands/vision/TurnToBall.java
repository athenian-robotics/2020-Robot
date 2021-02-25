package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem limelight;
    private final DrivetrainSubsystem drivetrain;

    double tolerance;
    double setpoint;
    double validTarget;
    double Kp = 0.01;
    double Ki = 0.0;  //@TODO Fix PID values!
    double Kd = 0.001;
    double i_test = 0;

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
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        double[] list = this.limelight.grabValues();
        double angleToTurn = list[3];
        this.validTarget = list[0];

        if (this.validTarget == 1) {
            setpoint = drivetrain.getGyroAngle() + angleToTurn;
//            pid.setSetpoint(setpoint);
            SmartDashboard.putNumber("TurnToBall angleToTurn", angleToTurn);
        } else {
            end(true); //if there is no valid target don't do anything
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        i_test++;
//        double power = pid.calculate(drivetrain.getGyroAngle());
        double power = Kp*(setpoint-drivetrain.getGyroAngle());
        drivetrain.tankDriveTurn(power, -power);
        SmartDashboard.putNumber("TurnToBall Power", power);
        SmartDashboard.putNumber("Test!!!", i_test);
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
//        return ((pid.getPositionError() < 5) && (pid.getPositionError() > -5));
        double error = drivetrain.getGyroAngle()-setpoint;
        return (error < 3 && error > -3);
    }
}

