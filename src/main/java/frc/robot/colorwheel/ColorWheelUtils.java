package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import static frc.robot.colorwheel.DirectionTiles.*;
import static frc.robot.colorwheel.WheelColors.*;

public class ColorWheelUtils {

    //Init basic variables
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    //Create class constructor
    public ColorWheelUtils() {

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
    public WheelColors currentColor() {
        //If RED values are larger than green or blue, return RED
        Color detectedColor = colorSensor.getColor();
        double checkColorRed = detectedColor.red * 255;
        double checkColorGreen = detectedColor.green * 255;
        double checkColorBlue = detectedColor.blue * 255;

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
        WheelColors currentColor = currentColor();
        //If no color is known, return false
        return currentColor != null;
    }


    public WheelColors getLeftColor(WheelColors currentColor) {
        //Take the current color, and return ONE color to the left
        switch (currentColor) {
            case RED:
                return GREEN;
            case BLUE:
                return YELLOW;
            case GREEN:
                return BLUE;
            case YELLOW:
                return RED;
            default:
                return null;
        }
    }


    public WheelColors getRightColor(WheelColors currentColor) {
        //Take the current color and return 1 color to the right
        switch (currentColor) {
            case RED:
                return YELLOW;
            case BLUE:
                return GREEN;
            case GREEN:
                return RED;
            case YELLOW:
                return BLUE;
            default:
                return null;
        }
    }


    public DirectionTiles nearestColor(WheelColors colorWanted) {
        //Grab current sensor values
        WheelColors currentColor = currentColor();

        //Give default value to directionTiles
        DirectionTiles directionTiles = NOWHERE;

        //Format
        //[DIRECTION][NUMBER OF TILES TO GO]
        //Example: Direction = LEFT, Tiles to go = 1
        //LEFT1
        if (isCurrentColorKnown()) {
            switch (currentColor) {
                case RED:
                    if (colorWanted == RED) {
                        directionTiles = NOWHERE;
                    }
                    if (colorWanted == BLUE) {
                        directionTiles = LEFT_2TILES;
                    }
                    if (colorWanted == GREEN) {
                        directionTiles = LEFT_1TILE;
                    }
                    if (colorWanted == YELLOW) {
                        directionTiles = RIGHT_1TILE;
                    }
                    break;

                case BLUE:
                    if (colorWanted == RED) {
                        directionTiles = LEFT_2TILES;
                    }
                    if (colorWanted == BLUE) {
                        directionTiles = NOWHERE;
                    }
                    if (colorWanted == GREEN) {
                        directionTiles = RIGHT_1TILE;
                    }
                    if (colorWanted == YELLOW) {
                        directionTiles = LEFT_1TILE;
                    }
                    break;

                case GREEN:
                    if (colorWanted == RED) {
                        directionTiles = RIGHT_1TILE;
                    }
                    if (colorWanted == BLUE) {
                        directionTiles = LEFT_1TILE;
                    }
                    if (colorWanted == GREEN) {
                        directionTiles = NOWHERE;
                    }
                    if (colorWanted == YELLOW) {
                        directionTiles = LEFT_2TILES;
                    }
                    break;

                case YELLOW:
                    if (colorWanted == RED) {
                        directionTiles = LEFT_1TILE;
                    }
                    if (colorWanted == BLUE) {
                        directionTiles = RIGHT_1TILE;
                    }
                    if (colorWanted == GREEN) {
                        directionTiles = LEFT_2TILES;
                    }
                    if (colorWanted == YELLOW) {
                        directionTiles = NOWHERE;
                    }
                    break;
            }
        }
        return directionTiles;
    }

    public int getTilesPassed() {
        int tilesPassed = 0;
        WheelColors oldColor = currentColor();
        //Grab the current color
        //Wait .3 seconds before grabbing the detected color again
        Timer.delay(0.3);
        WheelColors newColor = currentColor();

        if (newColor != oldColor) {
            tilesPassed++;
            System.out.println(tilesPassed);
        }
        return tilesPassed;
    }
}
