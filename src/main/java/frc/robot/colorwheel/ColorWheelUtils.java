package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import static frc.robot.colorwheel.DifferentColors.*;
import static frc.robot.colorwheel.DirectionTiles.*;

public class ColorWheelUtils {

    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    double checkColorRed;
    double checkColorGreen;
    double checkColorBlue;
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

    public DirectionTiles nearestColor(DifferentColors colorWanted) {
        //Grab current sensor values
        DifferentColors currentColor = currentColor();
        DirectionTiles directionTiles = NOWHERE;

        /**
         * DirectionTiles ENUM may be useful to look at
         */
        if (isCurrentColorKnown()) {
            if (currentColor == RED) {
                if (colorWanted == RED) {
                    directionTiles = NOWHERE;
                }
                if (colorWanted == BLUE) {
                    directionTiles = LEFT2;
                }
                if (colorWanted == GREEN) {
                    directionTiles = LEFT1;
                }
                if (colorWanted == YELLOW) {
                    directionTiles = RIGHT1;
                }
            }

            if (currentColor == BLUE) {
                if (colorWanted == RED) {
                    directionTiles = LEFT2;
                }
                if (colorWanted == BLUE) {
                    directionTiles = NOWHERE;
                }
                if (colorWanted == GREEN) {
                    directionTiles = RIGHT1;
                }
                if (colorWanted == YELLOW) {
                    directionTiles = LEFT1;
                }
            }

            if (currentColor == GREEN) {
                if (colorWanted == RED) {
                    directionTiles = RIGHT1;
                }
                if (colorWanted == BLUE) {
                    directionTiles = LEFT1;
                }
                if (colorWanted == GREEN) {
                    directionTiles = NOWHERE;
                }
                if (colorWanted == YELLOW) {
                    directionTiles = LEFT2;
                }
            }

            if (currentColor == YELLOW) {
                if (colorWanted == RED) {
                    directionTiles = LEFT1;
                }
                if (colorWanted == BLUE) {
                    directionTiles = RIGHT1;
                }
                if (colorWanted == GREEN) {
                    directionTiles = LEFT2;
                }
                if (colorWanted == YELLOW) {
                    directionTiles = NOWHERE;
                }
            }
        }
        return directionTiles;
    }
}
