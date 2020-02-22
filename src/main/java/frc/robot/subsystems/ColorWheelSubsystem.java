
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.shooterMotorPort;
import static frc.robot.Constants.PneumaticsConstants.colorSensorPortIn;
import static frc.robot.Constants.PneumaticsConstants.colorSensorPortOut;

public class ColorWheelSubsystem extends SubsystemBase {

    public final CANSparkMax wheelSpinner = new CANSparkMax(shooterMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final DoubleSolenoid spinnerLift = new DoubleSolenoid(6, 7);

    private final DoubleSolenoid sensorLift = new DoubleSolenoid(colorSensorPortIn, colorSensorPortOut);
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