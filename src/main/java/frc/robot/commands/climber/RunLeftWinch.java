package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LeftClimberSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunLeftWinch extends CommandBase {
    LeftClimberSubsystem leftClimber;

    public RunLeftWinch(LeftClimberSubsystem leftClimber) {
        this.leftClimber = leftClimber;
        addRequirements(leftClimber);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute() {
        if (POVCenter.get()) {
            leftClimber.leftWinchStop();
        }
        if (POVDown.get()) {
            leftClimber.leftWinchRetract();
            System.out.println("Left Winch Up");
        }
        if (POVUp.get()) {
            leftClimber.leftWinchExtend();
            System.out.println("Left Winch Down");
        }
    }

    public void end(boolean interrupted) {
        leftClimber.leftWinchStop();
        leftClimber.leftTelescopeStop();
    }
}
