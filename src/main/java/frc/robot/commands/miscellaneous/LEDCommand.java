package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ShuffleboardData;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends CommandBase {

    private LEDSubsystem ledSubsystem;
    private ShuffleboardData shuffle;

    public LEDCommand(LEDSubsystem ledSubsystem, ShuffleboardData shuffle) {
        addRequirements(ledSubsystem);
        this.ledSubsystem = ledSubsystem;
        this.shuffle = shuffle;
        //statusLEDs = RobotContainer.statusLEDs;
    }

    @Override
    public void execute() {
        if (shuffle.getTeamColor()) {
            ledSubsystem.setColor(LEDSubsystem.LedColors.BLUE);
        } else {
            ledSubsystem.setColor(LEDSubsystem.LedColors.DARK_RED);
        }

        if(DrivetrainSubsystem.movingAverageUltrasonic < 7){
            ledSubsystem.setColor(LEDSubsystem.LedColors.RED_STROBE);
        }
        /*if (RobotMap.ledError) {
            statusLEDs.setColor(LEDSubsystem.LedColors.RED_STROBE);
        } else if(RobotMap.isInOverride){
            statusLEDs.setColor(LEDSubsystem.LedColors.WHITE_STROBE);
        } else if (inHabRange.get()) {
            statusLEDs.setColor(LEDSubsystem.LedColors.GOLD_STROBE);
        } else if (inStationRange.get()) {
            statusLEDs.setColor(LEDSubsystem.LedColors.GREEN);
        } else if (!Ball.ballIn) {
            statusLEDs.setColor(LEDSubsystem.LedColors.RAINBOW);
        } else {
            if (sIsBlueTeam.getB())
                statusLEDs.setColor(LEDSubsystem.LedColors.BLUE);
            else
                statusLEds.setColor(LEDSubsystem.LedColors.DARK_RED);
        }*/

    }

    @Override
    public void end(boolean interrupted) {
        ledSubsystem.setColor(LEDSubsystem.LedColors.RAINBOW);
    }

    @Override
    public boolean isFinished(){return false;}

}

