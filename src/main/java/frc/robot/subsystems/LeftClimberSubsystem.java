package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.leftTelescopeMotorPort;
import static frc.robot.Constants.MechanismConstants.leftWinchMotorPort;

public class LeftClimberSubsystem extends SubsystemBase {

    private final CANSparkMax leftTelescope = new CANSparkMax(leftTelescopeMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftWinch = new CANSparkMax(leftWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);
    private final CANEncoder leftTelescopeEncoder = leftTelescope.getEncoder();

    public LeftClimberSubsystem() {
    }

    public void leftTelescopeUp() {
        if (getEncoderValue() > -260) {
            leftTelescope.set(-0.4);
        }
    }

    public void leftTelescopeDown() {
//        if(getEncoderValue() < 0) {
        leftTelescope.set(0.4);
//        }
    }

    public void leftTelescopeStop() {
        leftTelescope.set(0);
    }

    public void leftWinchExtend() {
        leftWinch.set(-0.3);
    }

    public void leftWinchRetract() {
        leftWinch.set(0.3);
    }

    public void leftWinchStop() {
        leftWinch.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Telescope Encoder", leftTelescopeEncoder.getPosition());
    }

    public double getEncoderValue() {
        return leftTelescopeEncoder.getPosition();
    }
}
