package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

import static frc.robot.lib.controllers.FightStick.*;


public class RunColorWheel extends CommandBase {

    ColorWheelSubsystem colorWheel;

    public RunColorWheel(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;
        addRequirements(colorWheel);
    }

    @Override
    public void initialize() {
        //colorWheel.sensorLiftUp();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (POVUp.get()) { colorWheel.spinnerLiftUp(); }
        if (POVDown.get()) { colorWheel.spinnerLiftDown(); }
        if (POVRight.get()) { colorWheel.spin(1); }
        if (POVLeft.get()) { colorWheel.spin(-1); }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        colorWheel.spin(0);
        //colorWheel.sensorLiftDown();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
