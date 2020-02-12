package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardUltrasonic extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double distancefromwall;
    PIDController pid = new PIDController(0.55, 0.0, 0.01); // 0.39, 0.0, 0.01
    double setpoint;
    long startTime;
    double trapezoidTime = 3000;

    public AutoDriveForwardUltrasonic(DrivetrainSubsystem drivetrain, double distancefromwall) {
        this.drivetrain = drivetrain;
        this.distancefromwall = distancefromwall;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
        driveTimer.reset();
        driveTimer.start();
        this.setpoint = distancefromwall;
        System.out.println("Setting setpoint to " + setpoint);
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + elapsedTime);
        double power;
        if(elapsedTime <= trapezoidTime){
            power = Math.min(Math.abs(pid.calculate(drivetrain.getUltrasonicDistance())*(elapsedTime/trapezoidTime)), 0.4);
        }
        else{
            power = Math.min(Math.abs(pid.calculate(drivetrain.getUltrasonicDistance())), 0.4);
        }
        System.out.println("Power: " + power);
        drivetrain.tankDrive(power, power);
    }

    public boolean isFinished() {
        return drivetrain.getUltrasonicDistance() <= distancefromwall;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}
