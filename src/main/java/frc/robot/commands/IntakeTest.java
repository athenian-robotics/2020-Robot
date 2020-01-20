package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class IntakeTest extends CommandBase {
    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(4);
    double speed;
    public IntakeTest(double speed) {
        this.speed = speed;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intakeMotor.set(speed);
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intakeMotor.set(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}