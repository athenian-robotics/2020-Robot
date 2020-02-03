package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class RunColorWheel extends CommandBase {

    boolean isEnabled = false;
    ColorWheelSubsystem colorWheel;

    public RunColorWheel(ColorWheelSubsystem colorWheel) {
        addRequirements(colorWheel);
        this.colorWheel = colorWheel;
    }

    @Override
    public void initialize() {
        colorWheel.spin(1);
        colorWheel.toggleColorSensor();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        colorWheel.spin(0);
        colorWheel.toggleColorSensor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
