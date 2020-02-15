package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.ArrayDeque;
import java.util.Deque;

public class AutoDriveForwardUltrasonic extends CommandBase {
    private DrivetrainSubsystem drivetrain;
    private Timer driveTimer = new Timer();
    private PIDController pid = new PIDController(0.05, 0.0, 0.01); // 0.39, 0.0, 0.01
    private long startTime;
    private double tolerance = 1;
    private double trapezoidTime = 1000;
    private double distancefromwall;
    //private long currentTime = startTime;

    //Variables for moving average calculation
    Deque<Double> queue;
    private double queueSize = 10;
    private double sum;
    private double count;
    private double movingAverageUltrasonic;

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
        queue = new ArrayDeque<>();
        sum = 0;
        count = 1;
    }

    public void execute() {

        double distance = drivetrain.getUltrasonicDistance();

        //Moving Average of ultrasonic values
        if(count <= queueSize){
            queue.add(distance);
            sum += distance;
            movingAverageUltrasonic = sum/count;
            count++;
        }
        else{
            sum = sum - queue.pop();
            sum += distance;
            queue.add(distance);
            movingAverageUltrasonic = sum/queueSize;
        }
        SmartDashboard.putNumber("Average", movingAverageUltrasonic);

        long elapsedTime = System.currentTimeMillis() - startTime;
        double power;
        //PID calculations
        if(elapsedTime <= trapezoidTime){
            power = Math.min(Math.abs(pid.calculate(movingAverageUltrasonic)*(elapsedTime/trapezoidTime)), 0.4);
        }
        else{
            power = Math.min(Math.abs(pid.calculate(movingAverageUltrasonic)), 0.4);
        }
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
        if(movingAverageUltrasonic <= distancefromwall+tolerance && movingAverageUltrasonic >= distancefromwall-tolerance){
            return true;
        }
        return false;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}
