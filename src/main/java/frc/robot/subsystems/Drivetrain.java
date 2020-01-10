package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.lib.ROBOT;

import static frc.robot.lib.ROBOT.*;

public class Drivetrain extends SubsystemBase {

    public final SpeedControllerGroup motorTest = new SpeedControllerGroup(new WPI_VictorSPX(0));

    private final SpeedControllerGroup leftMotors;
    private final SpeedControllerGroup rightMotors;
    private final DifferentialDrive drive;

    public Drivetrain() {
        this(JANKBOT);
    }

    public Drivetrain(ROBOT robot) {
        if (robot == JANKBOT) {
            leftMotors = new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.leftMotor1Port),
                    new WPI_VictorSPX(DriveConstants.leftMotor2Port));
            rightMotors = new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.rightMotor1Port),
                    new WPI_VictorSPX(DriveConstants.rightMotor2Port));
            leftMotors.setInverted(true);
            rightMotors.setInverted(true);
        } else if (robot == KITBOT) {
            leftMotors = new SpeedControllerGroup(new WPI_TalonSRX(DriveConstants.leftMotor1Port),
                    new WPI_TalonSRX(DriveConstants.leftMotor2Port));
            rightMotors = new SpeedControllerGroup(new WPI_TalonSRX(DriveConstants.rightMotor1Port),
                    new WPI_TalonSRX(DriveConstants.rightMotor2Port));
            leftMotors.setInverted(true);
            rightMotors.setInverted(true);

        } else if (robot == TESTBOT) {
            leftMotors = new SpeedControllerGroup(new WPI_TalonSRX(DriveConstants.leftMotor1Port),
                    new CANSparkMax(DriveConstants.leftMotor2Port, CANSparkMaxLowLevel.MotorType.kBrushless));
            rightMotors = new SpeedControllerGroup(new WPI_TalonSRX(DriveConstants.rightMotor1Port),
                    new CANSparkMax(DriveConstants.rightMotor2Port, CANSparkMaxLowLevel.MotorType.kBrushless));
        } else {
            throw new IllegalArgumentException("We will never get here");
        }
        drive = new DifferentialDrive(leftMotors, rightMotors);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * DriveConstants.speedScale, rightSpeed * DriveConstants.speedScale);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed * DriveConstants.speedScale, -zRotation * DriveConstants.speedScale);
    }

    @Override
    public void periodic() {
        System.out.println("Drivetrain subsystem");
    }
}
