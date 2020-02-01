package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class GateCommand extends CommandBase {

    boolean isEnabled = true;
    private final DoubleSolenoid solenoid = new DoubleSolenoid(0,1);

    public GateCommand(){

    }

    @Override
    public void initialize() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(!isEnabled){
            System.out.println("Forward");
            solenoid.set(DoubleSolenoid.Value.kReverse);
            isEnabled = true;
        } else {
            System.out.println("Backward");
            isEnabled = false;
            solenoid.set(DoubleSolenoid.Value.kForward);
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
