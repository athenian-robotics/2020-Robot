
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.shooterMotorPort;

public class ShooterSubsystem extends SubsystemBase {
    public boolean isRunning = false;
    private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(shooterMotorPort);


    public void FlatShooterSubsystem() {

        //final motor_placeholder shooterMotor;
        //final pneumatic_placeholder shooterPiston;

    }

    public void startShooter() {
        shooterMotor.set(1);
        System.out.println("Low goal shooter now shooting!");
        isRunning = true;
    }

    public void stopShooter() {
        //closePistonGate();
        //turnOffMotor();
        shooterMotor.set(0);
        System.out.println("Low goal shooter now not shooting!");
        isRunning = false;
    }

    public void toggleShooter() {
        if (isRunning) {
            stopShooter();
        } else {
            startShooter();
        }
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}