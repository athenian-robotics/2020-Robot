package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.*;
import frc.robot.commands.vision.TurnToBall;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;


public class AutoRoutineGalacticSearch extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;
    private final LimeLightSubsystem limelight;

    private int ballCount;
    private final double areaWeight = 1; //TODO determine what this weight should be so that the robot always gets the balls -- the higher this is, the farther the robot will go
    private final int tolerance = 1; //TODO determine how much farther than 9 meters we should go to clear the field

    public AutoRoutineGalacticSearch(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, LimeLightSubsystem limelight, int ballCount) {
        this.drivetrain = drivetrain;
        this.intake = intake;
        this.limelight = limelight;
        this.ballCount = ballCount;
        addRequirements(this.drivetrain, this.intake, this.limelight);
    }


    @Override
    public void initialize() {
        limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        new AutoIntakeOn(intake).schedule();
    }

    @Override
    public void execute() {
        if (ballCount > 0) {
            double[] values = limelight.grabValues();
            if (values[0] == 1) {
                new SequentialCommandGroup(
                        new TurnToBall(limelight, drivetrain),
                        new AutoForwardDistance(drivetrain, areaWeight/Math.sqrt(values[1])),             //Math.sqrt() is necessary because the robot's distance from the ball, how far we want to drive, is proportional to its radius, which is the square root of the area, or values[1]
                        new AutoRoutineGalacticSearch(drivetrain, intake, limelight, ballCount-1)
                ).schedule(); // We got a ball! Look for more.
            } else {
                new SequentialCommandGroup(
                        new AutoAngleTurn(drivetrain, -45),
                        new AutoRoutineGalacticSearch(drivetrain, intake, limelight, ballCount)
                ).schedule(); //No ball in sight, turn around and look again.
            }
        } else {
            limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
            new SequentialCommandGroup(
                    new AutoIntakeOff(intake),
                    new AutoAngleTurn(drivetrain, -drivetrain.getGyroAngle()),
                    new AutoForwardDistance(drivetrain, 9-drivetrain.getOdometryPosX()+tolerance)
            ).schedule(); //We got all the balls, turn everything off and run for the exit. :p
        }
    }

    @Override
    public boolean isFinished() { return true; }    //This command ends instantly, but lives on in new instances until we get all of the balls we need
}