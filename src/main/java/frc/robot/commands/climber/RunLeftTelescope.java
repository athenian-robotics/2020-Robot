package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LeftClimberSubsystem;

import static frc.robot.lib.controllers.FightStick.POVDown;
import static frc.robot.lib.controllers.FightStick.POVUp;

public class RunLeftTelescope extends CommandBase {
    LeftClimberSubsystem leftClimber;

    public RunLeftTelescope(LeftClimberSubsystem leftClimber) {
        this.leftClimber = leftClimber;
        addRequirements(leftClimber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        int pov = RobotContainer.xboxController.getPOV();
        if (pov == 0) {
            leftClimber.leftTelescopeUp();
        } else if (pov == 180) {
            leftClimber.leftTelescopeDown();
            ;
        } else {
            leftClimber.leftTelescopeStop();
            ;
        }
        if (POVUp.get()) {
            leftClimber.leftTelescopeUp();
        } else if (POVDown.get()) {
            leftClimber.leftTelescopeDown();
        } else {
            leftClimber.leftTelescopeStop();
        }

    }

    public boolean isFinished() {
        return leftClimber.getEncoderValue() < -272;
    }


    public void end() {
        leftClimber.leftTelescopeStop();
    }

}
