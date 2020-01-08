package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {

    public final SpeedControllerGroup motorTest = new SpeedControllerGroup(new WPI_VictorSPX(0));
    private final SpeedControllerGroup leftMotors =
            new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.leftMotor1Port),
                    new WPI_VictorSPX(DriveConstants.leftMotor2Port));
    // The motors on the right side of the drive.
    private final SpeedControllerGroup rightMotors =
            new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.rightMotor1Port),
                    new WPI_VictorSPX(DriveConstants.rightMotor2Port));
    private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);


    public Drivetrain() {
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(-leftSpeed * 0.8, -rightSpeed * 0.8);
    }

    @Override
    public void periodic() {
        System.out.println("Drivetrain subsystem");
    }
}
