package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.colorwheel.DifferentColors;

import static frc.robot.colorwheel.DifferentColors.*;


public class ColorWheelSubsystem extends SubsystemBase {
    /**
     * Declare ports and products
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public double checkColorGreen;
    public double checkColorBlue;
    /**
     * Init extra variables for the checkColor function
     */
    public double checkColorRed;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    //Create a class attribute
    RobotContainer robotContainer;

    //Create class constructor
    public ColorWheelSubsystem(RobotContainer robotContainer) {
        //Set initial value for class attribute robotContainer
        this.robotContainer = robotContainer;
    }


    /**
     * Define simple SmartDashboard methods for easier access to updating values
     */
    public static void updateColor(double red, double green, double blue) {

        //Put RGB values on SD
        SmartDashboard.putNumber("Red", red * 255);
        SmartDashboard.putNumber("Green", green * 255);
        SmartDashboard.putNumber("Blue", blue * 255);
    }

    public static void updateProximity(double proximity) {
        //Put proximity on SD
        SmartDashboard.putNumber("Proximity", proximity);
    }

    public static void updateIR(double IR) {
        //Put infrared on SD
        SmartDashboard.putNumber("Infrared", IR);
    }


    /**
     * PERIODIC
     * Gather RGB values, Proximity values and IR values
     * Update them to smartdashboard
     */
    public void periodic() {

        //Gather data from color sensor
        Color detectedColor = colorSensor.getColor();
        checkColorRed = detectedColor.red * 255;
        checkColorGreen = detectedColor.green * 255;
        checkColorBlue = detectedColor.blue * 255;

        //Put above data onto SmartDashboard using above methods
        ColorWheelSubsystem.updateColor(checkColorRed, checkColorGreen, checkColorBlue);
        ColorWheelSubsystem.updateProximity(colorSensor.getProximity());
        ColorWheelSubsystem.updateIR(colorSensor.getIR());

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

    /**
     * COLOR WHEEL METHODS
     */
    public DifferentColors currentColor() {
        //If RED values are larger than green or blue, return RED
        if ((checkColorRed >= checkColorGreen) && (checkColorRed >= checkColorBlue)) {
            return RED;
        }
        //If RED is within range 30 of GREEN, return YELLOW
        else if ((checkColorRed >= checkColorGreen) && (checkColorRed - 30 <= checkColorGreen)
                || (checkColorRed <= checkColorGreen) && (checkColorRed + 30 >= checkColorGreen)) {
            return YELLOW;
        }
        //If BLUE is within range 30 of GREEN, return BLUE
        else if ((checkColorBlue >= checkColorGreen) && (checkColorBlue - 30 <= checkColorGreen)
                || (checkColorBlue <= checkColorGreen) && (checkColorBlue + 30 >= checkColorGreen)) {
            return BLUE;
        }
        //If GREEN values are larger than red or blue, return GREEN
        else if ((checkColorGreen >= checkColorRed + 20) && (checkColorGreen >= checkColorBlue + 20)) {
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
        //TODO Change these to real ColorWheel values as these are made up
        if (currentColor == RED) {
            return YELLOW;
        } else if (currentColor == BLUE) {
            return RED;
        } else if (currentColor == GREEN) {
            return BLUE;
        } else if (currentColor == YELLOW) {
            return GREEN;
        } else {
            return null;
        }
    }

    public DifferentColors getRightColor(DifferentColors currentColor) {
        //Take the current color and return 1 color to the right
        //TODO Change these to real ColorWheel values as these are made up
        if (currentColor == RED) {
            return BLUE;
        } else if (currentColor == BLUE) {
            return GREEN;
        } else if (currentColor == GREEN) {
            return YELLOW;
        } else if (currentColor == YELLOW) {
            return RED;
        } else {
            return null;
        }
    }

    public int nearestColor(DifferentColors color, int direction) {
        int tilesToTravel = 0;
        DifferentColors currentColor = currentColor();
        //Return negative if left
        //Return positive if right
        //Return int(x amount of tiles away)
        //Return 0 if already there.
        if (isCurrentColorKnown()) {


            /**
             * GOING LEFT
             */
            if (direction < 0) {
                if (currentColor == RED) {
                    if (color == RED) {
                        tilesToTravel = 0;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 3;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 2;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 1;
                    }
                }

                if (currentColor == BLUE) {
                    if (color == RED) {
                        tilesToTravel = 1;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 0;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 3;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 2;
                    }
                }


                if (currentColor == GREEN) {
                    if (color == RED) {
                        tilesToTravel = 2;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 1;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 0;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 3;
                    }
                }

                if (currentColor == YELLOW) {
                    if (color == RED) {
                        tilesToTravel = 3;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 2;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 1;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 0;
                    }
                }
            }


            /**
             * GOING RIGHT
             */
            if (direction > 0) {
                if (currentColor == RED) {
                    if (color == RED) {
                        tilesToTravel = 0;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 1;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 2;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 3;
                    }
                }

                if (currentColor == BLUE) {
                    if (color == RED) {
                        tilesToTravel = 3;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 0;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 1;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 2;
                    }
                }

                if (currentColor == GREEN) {
                    if (color == RED) {
                        tilesToTravel = 2;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 3;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 0;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 1;
                    }
                }

                if (currentColor == YELLOW) {
                    if (color == RED) {
                        tilesToTravel = 1;
                    }
                    if (color == BLUE) {
                        tilesToTravel = 2;
                    }
                    if (color == GREEN) {
                        tilesToTravel = 3;
                    }
                    if (color == YELLOW) {
                        tilesToTravel = 0;
                    }
                }
            }


            /**
             * STOPPED
             */
            if (direction == 0) {
                return 0;
            }
        }

        return tilesToTravel;

    }
}


