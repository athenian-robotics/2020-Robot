package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.RobotType;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.lib.RobotType.JANKBOT;

public class DrivetrainSubsystem extends SubsystemBase {

    public final SpeedControllerGroup motorTest = new SpeedControllerGroup(new WPI_VictorSPX(0));

    private final DifferentialDrive drive;
    private final Encoder leftEncoder = new Encoder(encoderLeftA, encoderLeftB, false, Encoder.EncodingType.k2X);
    private final Encoder rightEncoder = new Encoder(encoderRightA, encoderRightB, false, Encoder.EncodingType.k2X);

    public DrivetrainSubsystem() {
        this(JANKBOT);
    }

    public DrivetrainSubsystem(RobotType robotType) {
        leftEncoder.setDistancePerPulse(6.0 * 0.0254 * Math.PI / 2048); // 6 inch wheel, to meters, 2048 ticks
        rightEncoder.setDistancePerPulse(6.0 * 0.0254 * Math.PI / 2048); // 6 inch wheel, to meters, 2048 ticks
        SpeedControllerGroup leftMotors;
        SpeedControllerGroup rightMotors;
        switch (robotType) {
            case JANKBOT:
                leftMotors = new SpeedControllerGroup(new WPI_VictorSPX(leftMotor1Port), new WPI_VictorSPX(leftMotor2Port));
                rightMotors = new SpeedControllerGroup(new WPI_VictorSPX(rightMotor1Port), new WPI_VictorSPX(rightMotor2Port));
                break;
            case KITBOT:
                leftMotors = new SpeedControllerGroup(new WPI_TalonSRX(leftMotor1Port), new WPI_TalonSRX(leftMotor2Port));
                rightMotors = new SpeedControllerGroup(new WPI_TalonSRX(rightMotor1Port), new WPI_TalonSRX(rightMotor2Port));
                break;
            case TESTBOT:
                leftMotors = new SpeedControllerGroup(new WPI_TalonSRX(leftMotor1Port), new CANSparkMax(leftMotor2Port, kBrushless));
                rightMotors = new SpeedControllerGroup(new WPI_TalonSRX(rightMotor1Port), new CANSparkMax(rightMotor2Port, kBrushless));
                break;
            case OFFICIAL:
                leftMotors = new SpeedControllerGroup(new CANSparkMax(leftMotor1Port, kBrushless), new CANSparkMax(leftMotor2Port, kBrushless));
                rightMotors = new SpeedControllerGroup(new CANSparkMax(rightMotor1Port, kBrushless), new CANSparkMax(rightMotor2Port, kBrushless));
            default:
                throw new IllegalStateException("We will never get here");
        }

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

    public double getLeftEncoderDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoderDistance() {
        return rightEncoder.getDistance();
    }

    public double getLeftEncoderRate() {
        return leftEncoder.getRate();
    }

    public double getRightEncoderRate() {
        return rightEncoder.getRate();
    }


    @Override
    public void periodic() {
        //System.out.println("Drivetrain subsystem");
        SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
        SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
        SmartDashboard.putNumber("Left Encoder Rate", getLeftEncoderRate());
        SmartDashboard.putNumber("Right Encoder Rate", getRightEncoderRate());

    }
}
