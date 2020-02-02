package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

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
        WheelColors currentColor;
        Color detectedColor = colorSensor.getColor();
        double checkColorRed = detectedColor.red * 255;
        double checkColorGreen = detectedColor.green * 255;
        double checkColorBlue = detectedColor.blue * 255;

        if ((checkColorRed >= checkColorGreen + 30) && (checkColorRed >= checkColorBlue + 30)) {
            updateString("RED");
            currentColor = RED;
            return currentColor;
        }
        //If BLUE is within range 30 of GREEN, return BLUE
        else if ((checkColorBlue >= checkColorGreen) && (checkColorBlue - 60 <= checkColorGreen)
                || (checkColorBlue <= checkColorGreen) && (checkColorBlue + 60 >= checkColorGreen)) {
            updateString("BLUE");
            currentColor = BLUE;
            return currentColor;
        }
        //If RED is within range 30 of GREEN, return YELLOW
        else if ((checkColorRed >= checkColorGreen) && (checkColorRed - 20 <= checkColorGreen)
                || (checkColorRed <= checkColorGreen) && (checkColorRed + 20 >= checkColorGreen)) {
            updateString("YELLOW");
            currentColor = YELLOW;
            return currentColor;
        }
        //If GREEN values are larger than red or blue, return GREEN
        else {
            updateString("GREEN");
            currentColor = GREEN;
            return currentColor;
        }
        //If NO COLOR is known, give a null value
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


    //TODO Test this
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


    //TODO Test this
    public void moveTiles(int tilesToMove) {
        WheelColors currentColor1 = currentColor();
        Timer.delay(0.2);
        WheelColors currentColor2 = currentColor();

        for (int i = 0; i < tilesToMove; i++) {
            if (currentColor1 != currentColor2) {
                tilesToMove--;
            }
            if (tilesToMove != 0) {
                //TURN MOTORS ON
            } else {
                //TURN MOTORS OFF
            }
        }
    }


    //TODO Test this piece of shit!
    public void nearestColorTiles(WheelColors colorWanted) {
        int tilesToGo;
        WheelColors currentColor = currentColor();
        if (currentColor == getLeftColor(colorWanted)) {
            tilesToGo = -1;
        } else {
            tilesToGo = (32 + colorWanted.i - currentColor.i) % 4;
        }

        Timer.delay(0.2);
        System.out.println(tilesToGo);
    }
}
