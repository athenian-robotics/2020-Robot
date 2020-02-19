package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ShuffleboardData;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends CommandBase {

    private LEDSubsystem ledSubsystem;
    private ShuffleboardData shuffleData;

    public LEDCommand(LEDSubsystem ledSubsystem, ShuffleboardData shuffleData) {
        addRequirements(ledSubsystem);
        this.ledSubsystem = ledSubsystem;
        this.shuffleData = shuffleData;
        //statusLEDs = RobotContainer.statusLEDs;
    }

    @Override
    public void execute() {
        if (shuffleData.getTeamColor()) {
            ledSubsystem.setColor(LEDSubsystem.LedColors.BLUE);
        } else {
            ledSubsystem.setColor(LEDSubsystem.LedColors.DARK_RED);
        }

        if(DrivetrainSubsystem.movingAverageUltrasonic < 7){
            ledSubsystem.setColor(LEDSubsystem.LedColors.RED_STROBE);
        }

    }

    @Override
    public void end(boolean interrupted) {
        ledSubsystem.setColor(LEDSubsystem.LedColors.RAINBOW);
    }

    @Override
    public boolean isFinished(){return false;}

}

