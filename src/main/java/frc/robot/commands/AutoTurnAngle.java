package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoTurnAngle extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double tolerance;
    double angleToTurn;
    double setpoint;
    double Kp = 0.004;
    double Ki = 0.0;
    double Kd = 0.001;
    //Attempt at changing valued from Shuffleboard
    /*private ShuffleboardTab tabPID = Shuffleboard.getTab("PID");
    private NetworkTableEntry maxKp = tabPID.add("Max Kp", 0.001).getEntry();
    double Kp = maxKp.getDouble(0.001);

    private NetworkTableEntry maxKi = tabPID.add("Max Ki", 0.0).getEntry();
    double Ki = maxKi.getDouble(0.0);

    private NetworkTableEntry maxKd = tabPID.add("Max Kd", 0.001).getEntry();
    double Kd = maxKd.getDouble(0.01);*/
    PIDController pid = new PIDController(Kp, Ki, Kd);

    public AutoTurnAngle(DrivetrainSubsystem drivetrain, double angleToTurn) {
        this.tolerance = 0.5;
        this.drivetrain = drivetrain;
        this.angleToTurn = angleToTurn;
        pid.setTolerance(tolerance);
        addRequirements(drivetrain);
//        System.out.println(setpoint);
    }

    public void initialize() {
        setpoint = drivetrain.getGyroAngle() + angleToTurn + tolerance;
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        double power = pid.calculate(drivetrain.getGyroAngle());
//        System.out.println(power);
        drivetrain.tankDrive(power, -power);
        SmartDashboard.putNumber("Angle PID Error:", pid.getPositionError());

    }


}