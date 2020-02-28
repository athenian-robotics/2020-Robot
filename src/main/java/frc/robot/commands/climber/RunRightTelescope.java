package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RightClimberSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunRightTelescope extends CommandBase {
    RightClimberSubsystem climber;

    public RunRightTelescope(RightClimberSubsystem rightClimber) {
        this.climber = rightClimber;
        addRequirements(rightClimber);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute() {
        if (POVCenter.get()) { climber.rightTelescopeStop(); }
        if (POVUp.get()) { climber.rightTelescopeUp(); }
        if (POVDown.get()) {climber.rightTelescopeDown(); }
    }

    public void end(){
        climber.rightTelescopeStop();
    }
}
