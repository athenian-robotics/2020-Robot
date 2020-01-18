package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.colorwheel.Colors;

import static frc.robot.colorwheel.Colors.*;


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


    public static void updateColor(double red, double green, double blue) {

        //Put values on Shuffleboard
        SmartDashboard.putNumber("Red", red * 255);
        SmartDashboard.putNumber("Green", green * 255);
        SmartDashboard.putNumber("Blue", blue * 255);
    }

    public static void updateProximity(double proximity) {
        SmartDashboard.putNumber("Proximity", proximity);
    }

    public static void updateIR(double IR) {
        SmartDashboard.putNumber("Infrared", IR);
    }

    public void updateString(String colorString) {
        SmartDashboard.putString("COLOR", colorString);
    }

    public void checkColor() {

    }

    public void periodic() {

        /**
         * Gather color data from the ColorSensorV3
         */
        Color detectedColor = colorSensor.getColor();
        checkColorRed = detectedColor.red * 255;
        checkColorGreen = detectedColor.green * 255;
        checkColorBlue = detectedColor.blue * 255;

        /**
         * Put above data in the form of RGB values
         * And Proximity
         * And Infrared
         * And place on shuffleboard DriverStation application
         */

        ColorWheelSubsystem.updateColor(checkColorRed, checkColorGreen, checkColorBlue);
        ColorWheelSubsystem.updateProximity(colorSensor.getProximity());
        ColorWheelSubsystem.updateIR(colorSensor.getIR());

        checkColor();

    }


    /**
     * COLOR WATCHER METHODS
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

    /**
     * Color wheel declarations
     */
    public Colors currentColor() {
        if ((checkColorRed >= checkColorGreen) && (checkColorRed >= checkColorBlue)) {
            return RED;
        } else if ((checkColorRed >= checkColorGreen) && (checkColorRed - 30 <= checkColorGreen)
                || (checkColorRed <= checkColorGreen) && (checkColorRed + 30 >= checkColorGreen)) {
            return YELLOW;
        } else if ((checkColorBlue >= checkColorGreen) && (checkColorBlue - 30 <= checkColorGreen)
                || (checkColorBlue <= checkColorGreen) && (checkColorBlue + 30 >= checkColorGreen)) {
            return BLUE;
        } else if ((checkColorGreen >= checkColorRed + 20) && (checkColorGreen >= checkColorBlue + 20)) {
            return GREEN;
        } else {
            return null;
        }
    }

    public boolean isCurrentColorKnown() {
        Colors currentColor = currentColor();

        if (currentColor == null) {
            return false;
        } else {
            return true;
        }
    }

    public Colors leftColor(Colors currentColor) {
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

    public Colors rightColor(Colors currentColor) {
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

    public int nearest(Colors color, int direction) {
        int tilesToTravel = 0;
        Colors currentColor = currentColor();
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


