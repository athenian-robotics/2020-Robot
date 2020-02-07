package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class WheelSpinnerLiftDown extends CommandBase {
    private final ColorWheelSubsystem colorWheel;

    public WheelSpinnerLiftDown(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;
    }

    public void initialize() {
        colorWheel.spinnerLiftDown();
    }

    public boolean isFinished() {
        return true;
    }
}
