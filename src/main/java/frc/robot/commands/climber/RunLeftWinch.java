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
        if (POVCenter.get()) { leftClimber.leftWinchStop(); }
        if (POVUp.get()) { leftClimber.leftWinchRetract(); }
        if (POVDown.get()) { leftClimber.leftWinchExtend(); }
    }

}
