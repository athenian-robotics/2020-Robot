package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.*;
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
    private final Encoder leftEncoder = new Encoder(encoderLeftA, encoderLeftB, true, Encoder.EncodingType.k2X);
    private final Encoder rightEncoder = new Encoder(encoderRightA, encoderRightB, false, Encoder.EncodingType.k2X);
    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    private final ADXRS450_Gyro gyro1 = new ADXRS450_Gyro(SPI.Port.kOnboardCS1);
    private final ADXRS450_Gyro gyro2 = new ADXRS450_Gyro(SPI.Port.kOnboardCS2);
    private final ADXRS450_Gyro gyro3 = new ADXRS450_Gyro(SPI.Port.kOnboardCS3);
    private final ADXRS450_Gyro gyro4 = new ADXRS450_Gyro(SPI.Port.kMXP);


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
        drive.setDeadband(0.02);
        drive.setMaxOutput(speedScale);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        int leftSign = leftSpeed >= 0 ? 1 : -1;
        int rightSign = rightSpeed >= 0 ? 1 : -1;

        double leftPower = ((speedScale - minDrivePower) * Math.abs(leftSpeed) + minDrivePower) * leftSign;
        double rightPower = ((speedScale - minDrivePower) * Math.abs(rightSpeed) + minDrivePower) * rightSign;

//        double leftPower = leftSpeed;
//        double rightPower = rightSpeed;
        drive.tankDrive(leftPower, rightPower);

        SmartDashboard.putNumber("Left Power:", leftPower);
        SmartDashboard.putNumber("Right Power:", rightPower);
//        System.out.println(leftPower + " " + rightPower);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, -zRotation);
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

    public double getGyroAngle() {
//        System.out.println(gyro.getAngle());
        return gyro.getAngle();
    }


    @Override
    public void periodic() {
        //System.out.println("Drivetrain subsystem");
        SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
        SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
        SmartDashboard.putNumber("Left Encoder Rate", getLeftEncoderRate());
        SmartDashboard.putNumber("Right Encoder Rate", getRightEncoderRate());
        SmartDashboard.putNumber("Gyro0", gyro.getAngle());

    }
}
