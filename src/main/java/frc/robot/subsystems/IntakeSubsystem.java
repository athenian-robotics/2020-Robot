package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.intakeMotorPort;

public class IntakeSubsystem extends SubsystemBase {


    //    private final double speed;

    private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(intakeMotorPort);
    public boolean isRunning = false;
    private boolean isForward;

    public IntakeSubsystem() {
        intakeMotor.setInverted(isForward);
//      this.speed = speed;
    }

    public void startIntake() {
        intakeMotor.set(1);
        System.out.println("start intake");
        isRunning = true;
    }

    public void invert() {
        intakeMotor.setInverted(!intakeMotor.getInverted());
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