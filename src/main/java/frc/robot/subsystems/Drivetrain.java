package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.RobotType;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.lib.RobotType.*;

public class Drivetrain extends SubsystemBase {

    public final SpeedControllerGroup motorTest = new SpeedControllerGroup(new WPI_VictorSPX(0));

    private final DifferentialDrive drive;

    public Drivetrain() {
        this(JANKBOT);
    }

    public Drivetrain(RobotType robotType) {
        final SpeedController[] leftCtrls;
        final SpeedController[] rightCtrls;
        if (robotType == JANKBOT) {
            leftCtrls = new SpeedController[]{new WPI_VictorSPX(leftMotor1Port), new WPI_VictorSPX(leftMotor2Port)};
            rightCtrls = new SpeedController[]{new WPI_VictorSPX(rightMotor1Port), new WPI_VictorSPX(rightMotor2Port)};
        } else if (robotType == KITBOT) {
            leftCtrls = new SpeedController[]{new WPI_TalonSRX(leftMotor1Port), new WPI_TalonSRX(leftMotor2Port)};
            rightCtrls = new SpeedController[]{new WPI_TalonSRX(rightMotor1Port), new WPI_TalonSRX(rightMotor2Port)};
        } else if (robotType == TESTBOT) {
            leftCtrls = new SpeedController[]{new WPI_TalonSRX(leftMotor1Port), new CANSparkMax(leftMotor2Port, kBrushless)};
            rightCtrls = new SpeedController[]{new WPI_TalonSRX(rightMotor1Port), new CANSparkMax(rightMotor2Port, kBrushless)};
        } else {
            throw new IllegalArgumentException("We will never get here");
        }

        SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftCtrls[0], leftCtrls[1]);
        SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightCtrls[0], rightCtrls[1]);

        leftMotors.setInverted(robotType.isInverted());
        rightMotors.setInverted(robotType.isInverted());

        drive = new DifferentialDrive(leftMotors, rightMotors);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * speedScale, rightSpeed * speedScale);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed * speedScale, -zRotation * speedScale);
    }

    @Override
    public void periodic() {
        System.out.println("Drivetrain subsystem");
    }
}
