package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

import static frc.robot.Constants.MechanismConstants.colorWheelPort;
import static frc.robot.Constants.MechanismConstants.intakeMotorPort;

public class RunColorWheel extends CommandBase {

    private WPI_TalonSRX colorWheelSpiner = new WPI_TalonSRX(colorWheelPort);
    private final DoubleSolenoid solenoid = new DoubleSolenoid(6,7);

    boolean isEnabled = false;
    public RunColorWheel(){

    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(!isEnabled){
            colorWheelSpiner.set(1);
            solenoid.set(DoubleSolenoid.Value.kReverse);
            isEnabled = true;
        } else {
            colorWheelSpiner.set(0);
            solenoid.set(DoubleSolenoid.Value.kForward);
            isEnabled = false;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
