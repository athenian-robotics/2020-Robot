package frc.robot.lib;

public enum RobotType {
    JANKBOT(true), KITBOT(true), TESTBOT(false);

    private final boolean inverted;

    RobotType(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean isInverted() {
        return inverted;
    }
}


