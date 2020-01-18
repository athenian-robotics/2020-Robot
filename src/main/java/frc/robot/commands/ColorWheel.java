package frc.robot.commands;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class ColorWheel {

    /**
     * Declare ColorSensorV3 and I2C Port
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    /**
     * Declare grab color methods for all RGB values
     */
    public double currentColorRed() {
        Color currentColor = colorSensor.getColor();
        return currentColor.red;
    }

    public double currentColorBlue() {
        Color currentColor = colorSensor.getColor();
        return currentColor.blue;
    }

    public double currentColorGreen() {
        Color currentColor = colorSensor.getColor();
        return currentColor.green;
    }

}
