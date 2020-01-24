package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    //    private final double speed;
    private TalonSRX motor = new TalonSRX(4);
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
        motor.set(ControlMode.PercentOutput, speed);
    }
}