package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.MechanismConstants.*;

//this is all just a placeholder for now

public class ClimberSubsystem extends SubsystemBase {

    //    private final double speed;
    private WPI_VictorSPX winchLeft = new WPI_VictorSPX(climberWinchLeftPort);
    private WPI_VictorSPX winchRight = new WPI_VictorSPX(climberWinchRightPort);
    private CANSparkMax telescopeLeft = new CANSparkMax(climberTelescopeLeftPort, kBrushless);
    private CANSparkMax telescopeRight = new CANSparkMax(climberTelescopeRightPort, kBrushless);

    public void setWinchLeft(int power) {
        winchLeft.set(power);
    }
    public void setWinchRight(int power) {
        winchRight.set(power);
    }
    public void setTelescopeLeft(int power) {
        telescopeLeft.set(power);
    }
    public void setTelescopeRight(int power) {
        telescopeRight.set(power);
    }
}