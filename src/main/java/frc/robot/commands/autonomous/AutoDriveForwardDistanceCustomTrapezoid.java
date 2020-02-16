package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistanceCustomTrapezoid extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double metersToDrive;
    PIDController pid = new PIDController(0.6, 0.0, 0.01); // 0.39, 0.0, 0.01
    double setpoint;
    long startTime;
    double trapezoidTime = 1000;

    public AutoDriveForwardDistanceCustomTrapezoid(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
        System.out.println("In Constructor");
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
        driveTimer.reset();
        driveTimer.start();
        this.setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        //System.out.println("Current right encoder distance: " + drivetrain.getRightEncoderDistance());
        //System.out.println("Setting setpoint to " + setpoint);
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        //System.out.println("Time: " + elapsedTime);
        double power;
        if(elapsedTime <= trapezoidTime){
            power = Math.min(pid.calculate(drivetrain.getRightEncoderDistance())*(elapsedTime/trapezoidTime),0.4);
        }
        else{
            power = Math.min(pid.calculate(drivetrain.getRightEncoderDistance()), 0.4);
        }
        //System.out.println(power);
        drivetrain.tankDrive(power, power);
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        //System.out.println("Reached setpoint");
    }

}
