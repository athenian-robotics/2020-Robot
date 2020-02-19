package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShuffleboardData {

    private ShuffleboardTab tab = Shuffleboard.getTab("Sendables");

    private NetworkTableEntry isBlueTeam = tab
            .add("Is Blue Team?", true)
            .getEntry();

    private NetworkTableEntry isSlowMode = tab
            .add("Is in slow mode?", false)
            .getEntry();

    public boolean getTeamColor() {
        return isBlueTeam.getBoolean(true);
    }

    public void setSpeedModeSlow() {
        isSlowMode.setBoolean(true);
    }

    public void setSpeedModeFast() {
        isSlowMode.setBoolean(false);
    }

    public boolean getSpeedMode() {
        return isSlowMode.getBoolean(false);
    }

}
