package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;


public class ColorWatcher {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private int tilesPassed = 0;
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    public ColorWatcher() {

    }


    public void getTilesPassed(DifferentColors colorWanted, DifferentColors currentColor) {
        Color colorOG = colorSensor.getColor();
        Timer.delay(0.5);
        Color colorNew = colorSensor.getColor();

        if ((colorNew.red != colorOG.red && (colorNew.red >= colorOG.red + 30 || colorNew.red <= colorOG.red - 30))
                || ((colorNew.green != colorOG.green) && (colorNew.green >= colorOG.green + 30 || colorNew.green <= colorOG.green - 30))
                || ((colorNew.blue != colorOG.blue) && colorNew.blue >= colorOG.blue + 30 || colorNew.blue <= colorOG.blue - 30)) {

            tilesPassed++;
            System.out.println(tilesPassed);

        }
        System.out.println(tilesPassed);
    }


}