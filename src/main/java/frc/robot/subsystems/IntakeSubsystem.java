package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class IntakeSubsystem extends SubsystemBase {
    //    private final double speed;
    private TalonSRX intakeMotor = new TalonSRX(MechanismConstants.intakeMotorPort);
    private TalonSRX ballLifterMotor = new TalonSRX(MechanismConstants.ballLifterMotorPort);
    public boolean isRunning = false;

    public IntakeSubsystem() {
//        this.speed = speed;
    }

    public void setSpeed(double speed) {
        if (speed == 0) {
            isRunning = false;
        } else {
            isRunning = true;
        }
        intakeMotor.set(ControlMode.PercentOutput, speed);
        ballLifterMotor.set(ControlMode.PercentOutput, speed);
    }
}