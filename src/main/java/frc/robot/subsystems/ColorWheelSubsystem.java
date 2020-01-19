package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.colorwheel.ColorWheelUtils;



public class ColorWheelSubsystem extends SubsystemBase {
    /**
     * Declare ports and products
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    double checkColorRed;
    double checkColorGreen;
    double checkColorBlue;



    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    //Create a class attribute
    RobotContainer robotContainer;
    ColorWheelUtils colorWheelUtils;

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

        checkColorRed = colorSensor.getRed();
        checkColorGreen = colorSensor.getGreen();
        checkColorBlue = colorSensor.getBlue();


        //Put above data onto SmartDashboard using above methods
        ColorWheelSubsystem.updateColor(checkColorRed, checkColorGreen, checkColorBlue);
        ColorWheelSubsystem.updateProximity(colorSensor.getProximity());
        ColorWheelSubsystem.updateIR(colorSensor.getIR());


        //COLOR WHEEL UTILITY METHODS
        colorWheelUtils.currentColor();
    }

}


