package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LeftClimberSubsystem;
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
        if (POVCenter.get()) { rightClimber.rightWinchStop(); }
        if (POVUp.get()) {
            System.out.println("Right Winch Up");
            rightClimber.rightWinchRetract(); }
        if (POVDown.get()) {
            System.out.println("Right Winch Down");
            rightClimber.rightWinchExtend();
        }
    }

    public void end(){
        rightClimber.rightTelescopeStop();
    }

}
