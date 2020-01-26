package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.MechanismConstants.intakeMotorPort;

public class IntakeSubsystem extends SubsystemBase {

    //    private final double speed;
    private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(intakeMotorPort);
    public boolean isRunning = false;

    public IntakeSubsystem() {
        intakeMotor.setInverted(true);
//        this.speed = speed;
    }

    public void startIntake() {
        intakeMotor.set(1);
        System.out.println("start intake");
        isRunning = true;
    }

    public void stopIntake() {
        //closePistonGate();
        //turnOffMotor();
        intakeMotor.set(0);
        System.out.println("stop intake");
        isRunning = false;
    }

    public void toggleIntake() {
        if (isRunning) {
            stopIntake();
        } else {
            startIntake();
        }
    }
}