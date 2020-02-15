package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.RobotType;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.DriveConstants.*;

public class DrivetrainSubsystem extends SubsystemBase {

    public final SpeedControllerGroup motorTest = new SpeedControllerGroup(new WPI_VictorSPX(0));

    private final DifferentialDrive drive;
    private final Encoder leftEncoder = new Encoder(encoderLeftA, encoderLeftB, true, Encoder.EncodingType.k2X);
    private final Encoder rightEncoder = new Encoder(encoderRightA, encoderRightB, false, Encoder.EncodingType.k2X);
    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    private final AnalogPotentiometer ultrasonic = new AnalogPotentiometer(0, 512);
    private final DifferentialDriveOdometry m_odometry;
    public static double maxDriverSpeed = speedScale;

    SpeedControllerGroup leftMotors;
    SpeedControllerGroup rightMotors;

    //Variables for moving average calculation
    Deque<Double> queue = new ArrayDeque<>();;
    double queueSize = 5;
    double sum = 0;
    double count = 1;
    double movingAverageUltrasonic = 0;

    public DrivetrainSubsystem(RobotType robotType) {
        leftEncoder.setDistancePerPulse(6.0 * 0.0254 * Math.PI / 2048); // 6 inch wheel, to meters, 2048 ticks
        rightEncoder.setDistancePerPulse(6.0 * 0.0254 * Math.PI / 2048); // 6 inch wheel, to meters, 2048 ticks
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

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
                break;
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
        //drive.arcadeDrive(xSpeed*maxDriverSpeed, -zRotation*maxDriverSpeed);
      
        //account for changes in turning when the forward direction changes, if it doesn't work use the one above
        drive.arcadeDrive(xSpeed*maxDriverSpeed, maxDriverSpeed < 0 ? zRotation*maxDriverSpeed : -zRotation*maxDriverSpeed);
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
        SmartDashboard.putNumber("PoseX", getPose().getTranslation().getX());
        SmartDashboard.putNumber("PoseY", getPose().getTranslation().getY());
        SmartDashboard.putNumber("Pose˚", getPose().getRotation().getDegrees());
        SmartDashboard.putNumber("Ultrasonic Distance", ultrasonic.get());
        SmartDashboard.putNumber("AverageUltraSonic", movingAverageUltrasonic);

        // Update the odometry in the periodic block
        m_odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getDistance(),
                rightEncoder.getDistance());

        //Moving Average of ultrasonic values
        if(count <= queueSize){
            queue.add(getUltrasonicDistance());
            sum += getUltrasonicDistance();
            movingAverageUltrasonic = sum/count;
            count++;
        }
        else{
            sum = sum - queue.pop();
            sum += getUltrasonicDistance();
            queue.add(getUltrasonicDistance());
            movingAverageUltrasonic = sum/queueSize;
        }
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftMotors.setVoltage(leftVolts);
        rightMotors.setVoltage(-rightVolts);
    }

    /**
     * Resets the drive encoders to currently read a position of 0.
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }

    /**
     * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(gyro.getAngle(), 360) * (false ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return gyro.getRate() * (false ? -1.0 : 1.0);
    }

    public double getUltrasonicDistance() { return ultrasonic.get(); }

    public double getAverageUltrasonicDistance() { return movingAverageUltrasonic; }

}
