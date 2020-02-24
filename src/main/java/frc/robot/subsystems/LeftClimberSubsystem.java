package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.*;

public class LeftClimberSubsystem extends SubsystemBase {

    private final CANSparkMax leftTelescope = new CANSparkMax(leftTelescopeMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftWinch = new CANSparkMax(leftWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);

    public LeftClimberSubsystem() { }

    public void leftTelescopeUp() { leftTelescope.set(1); }
    public void leftTelescopeDown() { leftTelescope.set(-1); }
    public void leftTelescopeStop() { leftTelescope.set(0); }

    public void leftWinchExtend() { leftWinch.set(1); }
    public void leftWinchRetract() { leftWinch.set(-1); }
    public void leftWinchStop() { leftWinch.set(0); }

}
