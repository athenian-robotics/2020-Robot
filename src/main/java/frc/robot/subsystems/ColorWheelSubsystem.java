package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.colorwheel.ColorWheelUtils;

import static frc.robot.colorwheel.WheelColors.RED;


public class ColorWheelSubsystem extends SubsystemBase {

    //Declare ports and variables
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorWheelUtils colorWheelUtils = new ColorWheelUtils();

    //Create class constructor
    public ColorWheelSubsystem() {
        //Set initial value for class attribute robotContainer
    }

    private static void updateColor(double red, double green, double blue) {
        SmartDashboard.putNumber("R Value", red * 255);
        SmartDashboard.putNumber("G Value", green * 255);
        SmartDashboard.putNumber("B Value", blue * 255);
    }

    private static void updateProximity(double proximity) {
        SmartDashboard.putNumber("Proximity", proximity);
    }

    private static void updateIR(double IR) {
        SmartDashboard.putNumber("Infrared", IR);
    }

    public void periodic() {

        //Gather wpilib Color data from color sensor
        Color detectedColor = colorSensor.getColor();

        //Update Color-sensor values onto SmartDashboard
        ColorWheelSubsystem.updateColor(detectedColor.red, detectedColor.green, detectedColor.blue);
        ColorWheelSubsystem.updateProximity(colorSensor.getProximity());
        ColorWheelSubsystem.updateIR(colorSensor.getIR());

        //Color Wheel Utils demo commands
        colorWheelUtils.currentColor();
        colorWheelUtils.distanceToColor(RED);
    }

}