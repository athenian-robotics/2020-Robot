package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistance extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    PIDController pid = new PIDController(0.55, 0.0, 0.01); // 0.39, 0.0, 0.01
    private double metersToDrive;
    private double setpoint;
    private double encoderSetPoint;
    private long startTime;

    public AutoDriveForwardDistance(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
        startTime = System.currentTimeMillis();

        this.setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        pid.setSetpoint(setpoint);
        encoderSetPoint = drivetrain.getRightEncoderDistance()-drivetrain.getLeftEncoderDistance();
    }

    public void execute() {
        double power = drivetrain.calculateTrapezoid(pid, startTime, 0.4);
        drivetrain.tankDrive(power + drivetrain.leftEncoderCorrection(encoderSetPoint),
                power + drivetrain.rightEncoderCorrection(encoderSetPoint));
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}
