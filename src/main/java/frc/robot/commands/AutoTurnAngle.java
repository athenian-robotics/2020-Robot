package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoTurnAngle extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double angleToTurn;
    PIDController pid = new PIDController(0.003, 0, 0.002);
    double setpoint;

    public AutoTurnAngle(DrivetrainSubsystem drivetrain, double angleToTurn) {
        this.drivetrain = drivetrain;
        this.angleToTurn = angleToTurn;
        pid.setTolerance(0.1);
        addRequirements(drivetrain);
//        System.out.println(setpoint);
    }

    public void initialize() {
        setpoint = drivetrain.getGyroAngle() + angleToTurn;
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        double power = pid.calculate(drivetrain.getGyroAngle());
//        System.out.println(power);
        drivetrain.tankDrive(power, -power);
    }


}
