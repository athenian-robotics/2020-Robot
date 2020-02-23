package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.miscellaneous.LEDCommand;
import frc.robot.RobotContainer.*;


public class LEDSubsystem extends SubsystemBase {

    private Spark statusLeds = RobotContainer.statusLEDs;

    public enum LedColors {
        BLACK,
        WHITE,
        GREEN,
        ORANGE,
        BLUE,
        DARK_RED,
        RAINBOW,
        RED_STROBE,
        BLUE_STROBE,
        WHITE_STROBE,
        GOLD_STROBE,
        WAVE_LAVA,
        RED_SHOT,
        LARGE_FIRE,
        WAVE_FOREST,
        RAINBOW_FOREST;

        public double getColorValue() {
            switch (this) {
                case BLACK:
                    return 0.99;
                case WHITE:
                    return 0.93;
                case GREEN:
                    return 0.77;
                case ORANGE:
                    return 0.65;
                case BLUE:
                    return 0.85;
                case DARK_RED:
                    return 0.59;
                case RAINBOW:
                    return -0.99;
                case RED_STROBE:
                    return -0.11;
                case BLUE_STROBE:
                    return -0.09;
                case WHITE_STROBE:
                    return -0.05;
                case GOLD_STROBE:
                    return -0.07;
                case WAVE_LAVA:
                    return -0.39;
                case RED_SHOT:
                    return -0.85;
                case LARGE_FIRE:
                    return -0.57;
                case WAVE_FOREST:
                    return -0.37;
                case RAINBOW_FOREST:
                    return -0.91;
                default:
                    return 0.99;
            }
        }
    }

    public LEDSubsystem() {
        //set Led strip to be black
        statusLeds.setSpeed(LedColors.BLACK.getColorValue());
    }

    public void setColor(LedColors color) {
        statusLeds.setSpeed(color.getColorValue());
        //System.out.println("Arm Led's set to " + color.name());
        SmartDashboard.putString("color", color.name());
    }

    public void resetLeds() {
        statusLeds.setSpeed(LedColors.BLACK.getColorValue()); //turn led's off
    }
}

