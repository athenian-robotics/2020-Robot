package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistance extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double metersToDrive;
    PIDController pid = new PIDController(2, 0, 0);
    double setpoint;

    public AutoDriveForwardDistance(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.01);
        addRequirements(drivetrain);
        this.setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;

    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
    }

    public void execute() {
        double power = pid.calculate(drivetrain.getLeftEncoderDistance(), setpoint);
        drivetrain.tankDrive(power, power);
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

}
