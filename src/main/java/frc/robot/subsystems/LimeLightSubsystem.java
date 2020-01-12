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
        final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

        final boolean tv = limelight.getEntry("tv").getBoolean(false);
        final double ta = limelight.getEntry("ta").getDouble(-1.1);
        final double ts = limelight.getEntry("ts").getDouble(-1.1);

        SmartDashboard.putBoolean("Valid Target", tv);
        SmartDashboard.putNumber("Target Area", ta);
        SmartDashboard.putNumber("Image Rotation", ts);
    }
}
