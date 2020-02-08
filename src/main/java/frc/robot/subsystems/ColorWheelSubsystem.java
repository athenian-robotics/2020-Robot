
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.shooterMotorPort;

public class ColorWheelSubsystem extends SubsystemBase {

    private final WPI_TalonSRX wheelSpinner = new WPI_TalonSRX(shooterMotorPort);
    private final DoubleSolenoid spinnerLift = new DoubleSolenoid(6, 7);

    private final DoubleSolenoid sensorLift = new DoubleSolenoid(4, 5);
    private boolean isSpinning = false;
    private boolean sensorIsUp = false;
    private boolean spinnerIsUp = false;


    public ColorWheelSubsystem() {

    }

    public void spinnerLiftUp() {
        spinnerLift.set(DoubleSolenoid.Value.kReverse);
        spinnerIsUp = true;
    }

    public void spinnerLiftDown() {
        spinnerLift.set(DoubleSolenoid.Value.kForward);
        spinnerIsUp = false;
    }

    public void sensorLiftUp() {
        sensorLift.set(DoubleSolenoid.Value.kReverse);
        sensorIsUp = true;
    }

    public void sensorLiftDown() {
        sensorLift.set(DoubleSolenoid.Value.kForward);
        sensorIsUp = false;
    }

    public void spin(double power) {
        wheelSpinner.set(power);
        isSpinning = true;
    }

    public void stop() {
        wheelSpinner.set(0);
        isSpinning = false;
    }

    public void toggleSpinnerLift() {
        if (spinnerIsUp) {
            spinnerLiftDown();
        } else {
            spinnerLiftUp();
        }
    }

    public void toggleColorSensor() {
        if (sensorIsUp) {
            sensorLiftDown();
        } else {
            sensorLiftUp();
        }

    }

    public void toggleSpinner() {
        if (isSpinning) {
            stop();
        } else {
            spin(1);
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}