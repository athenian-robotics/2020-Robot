package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.rightTelescopeMotorPort;
import static frc.robot.Constants.MechanismConstants.rightWinchMotorPort;

public class RightClimberSubsystem extends SubsystemBase {

    private final CANSparkMax rightTelescope = new CANSparkMax(rightTelescopeMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightWinch = new CANSparkMax(rightWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);
    private final CANEncoder rightTelescopeEncoder = rightTelescope.getEncoder();

    public RightClimberSubsystem() {
    }

    public void rightTelescopeUp() {
        if (getEncoderValue() > -150) {
            rightTelescope.set(-0.4);
        } else {
            rightTelescope.set(0);
        }
    }

    public void rightTelescopeDown() {
        if (getEncoderValue() < 0) {
            rightTelescope.set(0.4);
        }
    }

    public void rightTelescopeStop() {
        rightTelescope.set(0);
    }

    public void rightWinchExtend() {
        rightWinch.set(- 0.3);
    }

    public void rightWinchRetract() {
        rightWinch.set(0.3);
    }

    public void rightWinchStop() {
        rightWinch.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Telescope Encoder", rightTelescopeEncoder.getPosition());
    }

    public double getEncoderValue() {
        return rightTelescopeEncoder.getPosition();
    }
}
