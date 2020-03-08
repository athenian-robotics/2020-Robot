package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.leftTelescopeMotorPort;
import static frc.robot.Constants.MechanismConstants.leftWinchMotorPort;

public class VideoControl extends SubsystemBase {

    private final Servo limelight = new Servo(1);

    public void ServoUp() {
        limelight.set(limelight.get()+5);
    }
    public void ServoDown() {
        limelight.set(limelight.get()-5);
    }
    public void ServoStop() {
        limelight.set(limelight.get());
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Limelight Angle",limelight.get());
    }

}
