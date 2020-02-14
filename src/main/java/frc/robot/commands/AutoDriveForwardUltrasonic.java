package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardUltrasonic extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private Timer driveTimer = new Timer();
    private double distancefromwall;
    private PIDController pid = new PIDController(0.05, 0.0, 0.01); // 0.39, 0.0, 0.01
    private long startTime;
    //private long currentTime = startTime;
    private double trapezoidTime = 3000;

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
        double setpoint = distancefromwall;
        //System.out.println("Setting setpoint to " + setpoint);
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        //System.out.println("Time: " + elapsedTime);
        double power;
        if(elapsedTime <= trapezoidTime){
            power = Math.min(Math.abs(pid.calculate(drivetrain.getUltrasonicDistance())*(elapsedTime/trapezoidTime)), 0.2);
        }
        else{
            power = Math.min(Math.abs(pid.calculate(drivetrain.getUltrasonicDistance())), 0.2);
        }
        System.out.println("Power: " + power);
        drivetrain.tankDrive(power, power);
    }

    /*public boolean counter(){
        double count = 0 ;
        double previousCount;

        if(drivetrain.getAverageEncoderDistance()<=distancefromwall){
            previousCount = currentTime;
            count += currentTime - previousCount;
        }

        return count >= 250;
    }*/

    public boolean isFinished() {
        return drivetrain.getUltrasonicDistance() <= distancefromwall;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}
