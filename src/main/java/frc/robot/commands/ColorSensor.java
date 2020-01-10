package frc.robot.commands;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ColorSensor extends CommandBase {

    /**
     * Declare I2C port on RoboRIO
     * Declare ColorSensorV3 and say which port it is connected to
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    public void initialize() {
        //hello there :)
    }

    public void execute() {

        /**
         * Gather color data from the detected color
         * Gather infrared data
         * Gather proximity data
         */
        Color detectedColor = colorSensor.getColor();
        double IR = colorSensor.getIR();
        int proximity = colorSensor.getProximity();


        /**
         * Put above data in the form of RGB values
         * On shuffleboard DriverStation application
         */
        SmartDashboard.putNumber("Red", detectedColor.red * 255);
        SmartDashboard.putNumber("Green", detectedColor.green * 255);
        SmartDashboard.putNumber("Blue", detectedColor.blue * 255);
        SmartDashboard.putNumber("IR", IR);
        SmartDashboard.putNumber("Proximity", proximity);

        /**
         * An RGB array with all above data combined into one tab
         */

        //TODO This has yet to work, so fix it!
        double rgb[] = {detectedColor.red, detectedColor.green, detectedColor.blue};
        SmartDashboard.putNumberArray("RGB Array", rgb);

    }

    public void end(boolean interrupted) {

    }

    public boolean isFinished() {
        return false;
    }
}
