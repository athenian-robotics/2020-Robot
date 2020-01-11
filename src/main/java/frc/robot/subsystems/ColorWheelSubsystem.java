package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class ColorWheelSubsystem extends SubsystemBase {


    /**
     * Declare ports and products
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    //Create a class attribute
    RobotContainer robotContainer;

    //Create class constructor
    public ColorWheelSubsystem(RobotContainer robotContainer) {
        //Set initial value for class attribute robotContainer
        this.robotContainer = robotContainer;
    }


    public static void updateColor(double red, double green, double blue) {

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


    public void periodic() {

        /**
         * Gather color data from the ColorSensorV3
         */
        Color detectedColor = colorSensor.getColor();

        /**
         * Put above data in the form of RGB values
         * And Proximity
         * And Infrared
         * And place on shuffleboard DriverStation application
         */

        ColorWheelSubsystem.updateColor(detectedColor.red, detectedColor.green, detectedColor.blue);
        ColorWheelSubsystem.updateProximity(colorSensor.getProximity());
        ColorWheelSubsystem.updateIR(colorSensor.getIR());
    }


}
