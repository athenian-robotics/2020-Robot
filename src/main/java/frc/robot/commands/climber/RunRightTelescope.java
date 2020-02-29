package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RightClimberSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunRightTelescope extends CommandBase {
    RightClimberSubsystem rightClimber;

    public RunRightTelescope(RightClimberSubsystem rightClimber) {
        this.rightClimber = rightClimber;
        addRequirements(rightClimber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (POVCenter.get()) {
            rightClimber.rightTelescopeStop();
        }
        if (POVUp.get()) {
            rightClimber.rightTelescopeUp();
        }
        if (POVDown.get()) {
            rightClimber.rightTelescopeDown();
        }
    }

    public boolean isFinished() {
        return rightClimber.getEncoderValue() < -250;
    }


    public void end() {
        rightClimber.rightTelescopeStop();
    }
}
