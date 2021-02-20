package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.autonomous.components.*;
import frc.robot.commands.vision.TurnToBall;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;


public class AutoRoutineGalacticSearch extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;
    private final LimeLightSubsystem limelight;

    private int ballCount = 0;
    private double areaWeight = 1; //@TODO determine what this weight should be so that the robot always gets the balls -- the higher this is, the farther the robot will go

    public AutoRoutineGalacticSearch(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, LimeLightSubsystem limelight) {
        this.drivetrain = drivetrain;
        this.intake = intake;
        this.limelight = limelight;
        addRequirements(this.drivetrain, this.intake, this.limelight);
    }


    @Override
    public void initialize() {
        limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        new AutoIntakeOn(intake).schedule();
    }

    @Override
    public void execute() {
        double[] values = limelight.grabValues();
        if (values[0] == 1) {
            new TurnToBall(limelight, drivetrain).schedule();
            new AutoForwardDistance(drivetrain, values[1]*areaWeight).schedule();
            ballCount++;
        } else {
            new AutoAngleTurn(drivetrain, -45).schedule();
        }
    }


    @Override
    public boolean isFinished() {
        return ballCount > 2;
    }


    @Override
    public void end(boolean interrupted) {
        limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
        new AutoIntakeOff(intake).schedule();
        new AutoAngleTurn(drivetrain, -drivetrain.getGyroAngle()).schedule();
        new AutoForwardDistance(drivetrain, 4).schedule();
    }
}
