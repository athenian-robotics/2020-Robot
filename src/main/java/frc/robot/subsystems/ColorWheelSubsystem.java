
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.shooterMotorPort;

public class ColorWheelSubsystem extends SubsystemBase {

    private final WPI_TalonSRX wheelSpinner = new WPI_TalonSRX(shooterMotorPort);
    private final DoubleSolenoid spinnerLift = new DoubleSolenoid(6, 7);
    private final DoubleSolenoid colorSensorLift = new DoubleSolenoid(4, 5);
    public boolean isRunning = false;


    public ColorWheelSubsystem() {
        //final pneumatic_placeholder shooterPiston;

    }

    public void toggleSpinnerLift() {
        DoubleSolenoid.Value currentStatus = spinnerLift.get();
        if (currentStatus.equals(DoubleSolenoid.Value.kForward)) {
            spinnerLift.set(DoubleSolenoid.Value.kReverse);
        } else {
            spinnerLift.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void toggleColorSensor() {
        DoubleSolenoid.Value currentStatus = colorSensorLift.get();
        if (currentStatus.equals(DoubleSolenoid.Value.kForward)) {
            colorSensorLift.set(DoubleSolenoid.Value.kReverse);
        } else {
            colorSensorLift.set(DoubleSolenoid.Value.kForward);
        }

    }


    public void spin(double power) {
        wheelSpinner.set(power);
        isRunning = true;
//        solenoidlift.set(DoubleSolenoid.Value.kForward);
    }

    public void stop() {
        wheelSpinner.set(0);
        isRunning = false;
    }


    public void toggleSpinner() {
        if (isRunning) {
            spin(1);
        } else {
            stop();
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
