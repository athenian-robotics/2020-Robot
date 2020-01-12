package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LimeLightSubsystem extends SubsystemBase {

    // See http://docs.limelightvision.io/en/latest/networktables_api.html

    public LimeLightSubsystem() {
    }

    public void periodic() {
        final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-two");

        final boolean tv = limelight.getEntry("tv").getBoolean(false);
        SmartDashboard.putBoolean("tv", tv);
    }
}
