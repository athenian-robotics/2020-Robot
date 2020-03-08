package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LeftClimberSubsystem;
import frc.robot.subsystems.VideoControl;

import static frc.robot.lib.controllers.FightStick.POVDown;
import static frc.robot.lib.controllers.FightStick.POVUp;

public class LimelightServoControl extends CommandBase {
    VideoControl limelight;

    public LimelightServoControl(VideoControl limelight) {
        this.limelight = limelight;
        addRequirements(limelight);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        int pov = RobotContainer.xboxController.getPOV();
        if (pov == 0) {
            limelight.ServoUp();
        } else if (pov == 180) {
            limelight.ServoDown();
        } else {
            limelight.ServoStop();
        }
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {

    }

}
