package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// See http://docs.limelightvision.io/en/latest/networktables_api.html

public class LimeLightSubsystem extends SubsystemBase {

    final NetworkTable limelight;

    public LimeLightSubsystem(String tableName) {
        limelight = NetworkTableInstance.getDefault().getTable(tableName);
    }

    public void periodic() {
        final double tv = limelight.getEntry("tv").getDouble(-1.1);
        final double ta = limelight.getEntry("ta").getDouble(-1.1);
        final double ts = limelight.getEntry("ts").getDouble(-1.1);
        NetworkTableEntry camTran = limelight.getEntry("camtran");
        double[] helpme = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        //I'm really not sure if the names match with the values, we should check this later
        double targetX = camTran.getDoubleArray(helpme)[0];
        double targetY = camTran.getDoubleArray(helpme)[1];
        double targetZ = camTran.getDoubleArray(helpme)[2];
        double targetPitch = camTran.getDoubleArray(helpme)[3];
        double targetYaw = camTran.getDoubleArray(helpme)[4];
        double targetRoll = camTran.getDoubleArray(helpme)[5];

        SmartDashboard.putNumber("Valid Target", tv);
        SmartDashboard.putNumber("Target Area", ta);
        SmartDashboard.putNumber("Image Rotation", ts);
        SmartDashboard.putNumber("Target X", targetX);
        SmartDashboard.putNumber("Target Y", targetY);
        SmartDashboard.putNumber("Target Z", targetZ);
        SmartDashboard.putNumber("Target Pitch", targetPitch);
        SmartDashboard.putNumber("Target Yaw", targetYaw);
        SmartDashboard.putNumber("Target Roll", targetRoll);
    }
}
