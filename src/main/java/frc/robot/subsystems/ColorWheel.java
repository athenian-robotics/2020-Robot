package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class ColorWheel extends SubsystemBase {


    /**
     * Declare ports and products
     */
    public final I2C.Port i2cPort = I2C.Port.kOnboard;
    public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    RobotContainer robotContainer;

    public ColorWheel(RobotContainer robotContainer) {

        this.robotContainer = robotContainer;

    }

    public void periodic() {
        /**
         * Gather color data from the detected color
         */

        Color detectedColor = colorSensor.getColor();


        /**
         * Put above data in the form of RGB values
         * On shuffleboard DriverStation application
         */

        robotContainer.setColor(detectedColor.red, detectedColor.green, detectedColor.blue);
        robotContainer.setProximity(colorSensor.getProximity());
        robotContainer.setIR(colorSensor.getIR());


    }


}
