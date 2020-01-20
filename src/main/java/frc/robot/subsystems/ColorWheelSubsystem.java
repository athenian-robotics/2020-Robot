package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.colorwheel.ColorWatcher;
import frc.robot.colorwheel.ColorWheelUtils;


public class ColorWheelSubsystem extends SubsystemBase {
    /**
     * Declare ports and products
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    double colorSensorIR;
    double colorSensorProximity;


    //Create class attributes
    RobotContainer robotContainer;

    ColorWheelUtils colorWheelUtils = new ColorWheelUtils();
    ColorWatcher colorWatcher = new ColorWatcher();

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

        colorSensorProximity = colorSensor.getProximity();
        colorSensorIR = colorSensor.getIR();


        //Put above data onto SmartDashboard using above methods
        updateColor(detectedColor.red, detectedColor.blue, detectedColor.green);
        updateProximity(colorSensorProximity);
        updateIR(colorSensorIR);

        //colorWatcher.getTilesPassed();
        //colorWatcher.getTilesPassed();

    }

}


