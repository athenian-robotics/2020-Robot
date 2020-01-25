package frc.robot.commands;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

import static frc.robot.Constants.DriveConstants.speedScale;

public class EncoderValueRead extends CommandBase {

    RobotContainer robotContainer;
    Encoder ELeft = new Encoder(1,2, false, Encoder.EncodingType.k1X);
    Encoder ERight = new Encoder(4,5, true, Encoder.EncodingType.k1X);

    public void initialize() {
        //ELeft.setMaxPeriod(.1);
        //ERight.setMaxPeriod(.1);
    }

    public void execute() {
        System.out.println(ELeft.getRate());

       SmartDashboard.putNumber("Left Encoder", ELeft.getRate());
       SmartDashboard.putNumber("Right Encoder", ERight.getRate());

    }

    public boolean isFinished() {

        return false;
    }

    public void end(boolean interrupted) {

    }

}
