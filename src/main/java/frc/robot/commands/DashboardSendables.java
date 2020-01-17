package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DashboardSendables extends CommandBase {
    public final double[] testArray = {1, 2, 3};
    public final int testInt = 2;
    private final boolean testingDashboard = true;
    ShuffleboardTab testBooleanColors = Shuffleboard.getTab("testBooleanColors");
    private DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();

    public DashboardSendables() {

    }

    public void initialize() {
        //Putting final values onto SmartDashboard
        SmartDashboard.putBoolean("testingBooleans", testingDashboard);
        SmartDashboard.putNumberArray("testNumberArray", testArray);
        SmartDashboard.putNumber("testIntegers", testInt);


    }

    public void execute() {
        //Getting active values

        SmartDashboard.putData("testingDriveTrainCommandsDashboard", CommandScheduler.getInstance());


    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {

    }

}
