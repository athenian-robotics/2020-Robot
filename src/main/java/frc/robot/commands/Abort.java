package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Abort extends CommandBase {
    public Abort(ShooterSubsystem shooterSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem, ColorWheelSubsystem colorWheelSubsystem){
        addRequirements(shooterSubsystem, drivetrainSubsystem, intakeSubsystem, colorWheelSubsystem);
    }

    public void initialize() { }

    public boolean isFinished() { return true; }

    public void end() { };
}
