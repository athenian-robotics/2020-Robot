package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import static frc.robot.colorwheel.DifferentColors.*;

public class ColorWheelUtils {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    private int direction = 0;
    private int tilesToGo = 0;
    private int[] nearestValues = {direction, tilesToGo};

    private double currentColorRed = 0;
    private double currentColorGreen = 0;
    private double currentColorBlue = 0;
    //Create a class attribute

    //Create class constructor
    public ColorWheelUtils() {
        //Set initial value for class attribute robotContainer
    }

    //A method that puts Strings onto SmartDashboard for organization purposes
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
        currentColorRed = detectedColor.red * 255;
        currentColorGreen = detectedColor.green * 255;
        currentColorBlue = detectedColor.blue * 255;

        if ((currentColorRed >= currentColorGreen + 30) && (currentColorRed >= currentColorBlue + 30)) {
            updateString("RED");
            return RED;
        }
        //If BLUE is within range 30 of GREEN, return BLUE
        else if ((currentColorBlue >= currentColorGreen) && (currentColorBlue - 60 <= currentColorGreen)
                || (currentColorBlue <= currentColorGreen) && (currentColorBlue + 60 >= currentColorGreen)) {
            updateString("BLUE");
            return BLUE;
        }
        //If RED is within range 30 of GREEN, return YELLOW
        else if ((currentColorRed >= currentColorGreen) && (currentColorRed - 20 <= currentColorGreen)
                || (currentColorRed <= currentColorGreen) && (currentColorRed + 20 >= currentColorGreen)) {
            updateString("YELLOW");
            return YELLOW;
        }
        //If GREEN values are larger than red or blue, return GREEN
        else if ((currentColorGreen >= currentColorRed + 60) && (currentColorGreen >= currentColorBlue + 60)) {
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
