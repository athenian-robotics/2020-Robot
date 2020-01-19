package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystems.ColorWheelSubsystem;

import static frc.robot.colorwheel.DifferentColors.*;

public class ColorWheelUtils {

    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    public int direction;
    public int tilesToGo;
    int[] nearestValues = {direction, tilesToGo};
    double checkColorRed;
    double checkColorGreen;
    double checkColorBlue;
    //Create a class attribute
    ColorWheelSubsystem colorWheelSubsystem;

    //Create class constructor
    public ColorWheelUtils(ColorWheelSubsystem colorWheelSubsystem) {
        //Set initial value for class attribute robotContainer
        this.colorWheelSubsystem = colorWheelSubsystem;
    }


    /**
     * Extra methods for use later if needed.
     */
    public double currentColorRed() {
        //Get the current RED value from the color sensor
        Color currentColor = colorSensor.getColor();
        return currentColor.red;
    }

    public double currentColorBlue() {
        //Get the current BLUE value from the color sensor
        Color currentColor = colorSensor.getColor();
        return currentColor.blue;
    }

    public double currentColorGreen() {
        //Get the current GREEN value from the color sensor
        Color currentColor = colorSensor.getColor();
        return currentColor.green;
    }

    public void updateString(String currentColor) {
        SmartDashboard.putString("Current Color", currentColor);
    }


    /**
     * =-=-=-=-=-=-=-=-=-=
     * COLOR WHEEL METHODS
     * =-=-=-=-=-=-=-=-=-=
     */
    public DifferentColors currentColor() {
        //If RED values are larger than green or blue, return RED
        Color detectedColor = colorSensor.getColor();
        checkColorRed = detectedColor.red * 255;
        checkColorGreen = detectedColor.green * 255;
        checkColorBlue = detectedColor.blue * 255;

        if ((checkColorRed >= checkColorGreen + 30) && (checkColorRed >= checkColorBlue + 30)) {
            updateString("RED");
            return RED;
        }
        //If BLUE is within range 30 of GREEN, return BLUE
        else if ((checkColorBlue >= checkColorGreen) && (checkColorBlue - 60 <= checkColorGreen)
                || (checkColorBlue <= checkColorGreen) && (checkColorBlue + 60 >= checkColorGreen)) {
            updateString("BLUE");
            return BLUE;
        }
        //If RED is within range 30 of GREEN, return YELLOW
        else if ((checkColorRed >= checkColorGreen) && (checkColorRed - 20 <= checkColorGreen)
                || (checkColorRed <= checkColorGreen) && (checkColorRed + 20 >= checkColorGreen)) {
            updateString("YELLOW");
            return YELLOW;
        }
        //If GREEN values are larger than red or blue, return GREEN
        else if ((checkColorGreen >= checkColorRed + 60) && (checkColorGreen >= checkColorBlue + 60)) {
            updateString("GREEN");
            return GREEN;
        }
        //If NO COLOR is known, give a null value
        else {
            return null;
        }
    }

    public boolean isCurrentColorKnown() {
        DifferentColors currentColor = currentColor();
        //If no color is known, return false
        if (currentColor == null) {
            return false;
        }
        //If we have any color values, no matter what values, return true
        else {
            return true;
        }
    }

    public DifferentColors getLeftColor(DifferentColors currentColor) {
        //Take the current color, and return ONE color to the left
        if (currentColor == RED) {
            return GREEN;
        } else if (currentColor == BLUE) {
            return YELLOW;
        } else if (currentColor == GREEN) {
            return BLUE;
        } else if (currentColor == YELLOW) {
            return RED;
        } else {
            return null;
        }
    }

    public DifferentColors getRightColor(DifferentColors currentColor) {
        //Take the current color and return 1 color to the right
        if (currentColor == RED) {
            return YELLOW;
        } else if (currentColor == BLUE) {
            return GREEN;
        } else if (currentColor == GREEN) {
            return RED;
        } else if (currentColor == YELLOW) {
            return BLUE;
        } else {
            return null;
        }
    }

    public int[] nearestColor(DifferentColors color) {
        //Grab current sensor values
        DifferentColors currentColor = currentColor();

        /**
         * nearestValues [0]   = DIRECTION TO GO
         *  0 if to stop
         *  1 if to go right
         *  -1 if to go left
         *
         *  nearestValues [1]  = TILES TO GO
         *   Yes, it has taken into account the direction it is returning
         */
        if (isCurrentColorKnown()) {
            if (currentColor == RED) {
                if (color == RED) {
                    nearestValues[0] = 0;
                    nearestValues[1] = 0;
                }
                if (color == BLUE) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 2;
                }
                if (color == GREEN) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 1;
                }
                if (color == YELLOW) {
                    nearestValues[0] = 1;
                    nearestValues[1] = 1;
                }
            }

            if (currentColor == BLUE) {
                if (color == RED) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 2;
                }
                if (color == BLUE) {
                    nearestValues[0] = 0;
                    nearestValues[1] = 0;
                }
                if (color == GREEN) {
                    nearestValues[0] = 1;
                    nearestValues[1] = 1;
                }
                if (color == YELLOW) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 1;
                }
            }

            if (currentColor == GREEN) {
                if (color == RED) {
                    nearestValues[0] = 1;
                    nearestValues[1] = 1;
                }
                if (color == BLUE) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 1;
                }
                if (color == GREEN) {
                    nearestValues[0] = 0;
                    nearestValues[1] = 0;
                }
                if (color == YELLOW) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 2;
                }
            }

            if (currentColor == YELLOW) {
                if (color == RED) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 1;
                }
                if (color == BLUE) {
                    nearestValues[0] = 1;
                    nearestValues[1] = 1;
                }
                if (color == GREEN) {
                    nearestValues[0] = -1;
                    nearestValues[1] = 2;
                }
                if (color == YELLOW) {
                    nearestValues[0] = 0;
                    nearestValues[1] = 0;
                }
            }
        }
        return nearestValues;
    }
}
