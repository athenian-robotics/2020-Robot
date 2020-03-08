package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RightClimberSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunRightWinch extends CommandBase {
    RightClimberSubsystem rightClimber;

    public RunRightWinch(RightClimberSubsystem rightClimber) {
        this.rightClimber = rightClimber;
        addRequirements(rightClimber);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute() {
        if (POVCenter.get()) {
            rightClimber.rightWinchStop();
        }
        if (POVDown.get()) {
            System.out.println("Right Winch Up");
            rightClimber.rightWinchRetract();
        }
        if (POVUp.get()) {
            System.out.println("Right Winch Down");
            rightClimber.rightWinchExtend();
        }
    }

    public void end(boolean interrupted) {
        rightClimber.rightWinchStop();
        rightClimber.rightTelescopeStop();
    }

}
