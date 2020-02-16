/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.lib.RobotType;
import frc.robot.lib.controllers.FightStick;
import frc.robot.subsystems.*;

import static frc.robot.lib.controllers.FightStick.*;

//import frc.robot.subsystems.AutonomousDrivetrainSubsystem;
//import frc.robot.subsystems.ColorWheelSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static JoystickButton xboxA;
  public static JoystickButton xboxB;
  public static JoystickButton xboxX;
  public static JoystickButton xboxY;
  public static JoystickButton xboxLB;
  public static JoystickButton xboxRB;
  public static JoystickButton xboxSquares;
  public static JoystickButton xboxBurger;
  public static JoystickButton xboxLS;
  public static JoystickButton xboxRS;

  private static final RobotType ROBOT_TYPE = RobotType.JANKBOT;

  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(ROBOT_TYPE);
  private final LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem("limelight");
  //private final AutonomousDrivetrainSubsystem autodrive = new AutonomousDrivetrainSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final ColorWheelSubsystem colorWheelSubsystem = new ColorWheelSubsystem();

  // Define all OI devices here
  public static XboxController xboxController = new XboxController(OIConstants.xboxControllerPort);
  public static FightStick fightStick = new FightStick();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    buttonSetup();
    configureButtonBindings();


      //  CommandScheduler.getInstance().registerSubsystem(colorWheelSubsystem, shooterSubsystem);
    //  We do not need to register subsystems, this is done automatically

    drivetrain.setDefaultCommand(new DriveArcade(drivetrain, xboxController));
    //TODO: Figure out how to change command of drivetrain, create a button for switching
    //TODO: Implement arcade drive
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void buttonSetup() {
    xboxA = new JoystickButton(xboxController, 1);
    xboxB = new JoystickButton(xboxController, 2);
    xboxX = new JoystickButton(xboxController, 3);
    xboxY = new JoystickButton(xboxController, 4);
    xboxLB = new JoystickButton(xboxController, 5);
    xboxRB = new JoystickButton(xboxController, 6);
    xboxSquares = new JoystickButton(xboxController, 7);
    xboxBurger = new JoystickButton(xboxController, 8);
    xboxLS = new JoystickButton(xboxController, 9);
    xboxRS = new JoystickButton(xboxController, 10);
  }

  private void configureButtonBindings() {

    //Xbox Controls
    xboxY.whenPressed(new FastTurnSpeed());
    xboxX.whenPressed(new SlowTurnSpeed());
    xboxRS.whenPressed(new RunIntake(intakeSubsystem));
    xboxLS.whenHeld(new FunctionalCommand(
            intakeSubsystem::invert,
            () -> {
            },
            interrupted -> intakeSubsystem.invert(),
            () -> false,
            intakeSubsystem));
    xboxRB.whenPressed(new SetIntakeForward());
    xboxLB.whenPressed(new SetShooterForward());
    xboxBurger.whenPressed(new TurnToBall(limeLightSubsystem, drivetrain));
    xboxSquares.whenPressed(new Abort(shooterSubsystem, drivetrain, intakeSubsystem, colorWheelSubsystem));
    xboxB.whenPressed(new AutoDriveForwardUltrasonic(drivetrain, 25));
    //xboxA.whenPressed(new TurnThenUltraSonicStop(drivetrain, 90, 25));
    xboxA.whenPressed(new AutoDriveForwardOdometry(drivetrain,3));

    //FIGHT STICK CONTROLS

    fightStickA.whenPressed(new ChangeIntakeMode(intakeSubsystem));
    fightStickB.whenPressed(new GateCommand(shooterSubsystem));
    fightStickX.whenPressed(new ShootLowGoal(shooterSubsystem));
    fightStickY.whenHeld(new RunColorWheel(colorWheelSubsystem));

    // When held, this command changes the intake to backward (note: it does not change the status of the intake [on/off], just the direction)
    fightStickOption.whenHeld(new FunctionalCommand(
            intakeSubsystem::invert,
            () -> {
            },
            interrupted -> intakeSubsystem.invert(),
            () -> false,
            intakeSubsystem));

    // When held, this command changes the intake to backward, but doesn't change the speed/status
    fightStickShare.whenHeld(new FunctionalCommand(
            shooterSubsystem::invert,
            () -> {
            },
            interrupted -> shooterSubsystem.invert(),
            () -> false,
            intakeSubsystem));

    //xboxB.whenPressed(new GateCommand());
    //xboxX.whenPressed(new ChangeIntakeMode(intakeSubsystem));
    //xboxLB.whenPressed(new ShootLowGoal(shooterSubsystem));
    //xboxY.whenPressed(new RunColorWheel(colorWheelSubsystem));

    //XBOX CONTROLS
    //Change drive mode
    //xboxLB.whenPressed(new DriveTank(drivetrain, xboxController));
    //xboxRB.whenPressed(new DriveArcade(drivetrain, xboxController));


    //Autonomous Controls
    //xboxA.whenPressed(new FollowTrajectory(drivetrain).ExampleAutonomousCommand());
    //xboxA.whenPressed(new PathWeaver());
    //xboxA.whenPressed(new AutoDriveForwardDistance(drivetrain, 1.05));
    //xboxB.whenPressed(new AutoDriveForwardDistanceTrapezoid(drivetrain, 1.05));
    //xboxY.whenPressed(new AutoTurnAngle(drivetrain, 90));

    /**
     * ButtonDriveTest xbox controller Mapping
     * Uncomment as needed, make sure ROBOT_TYPE is correctly assigned!
     */
    //xboxA.whenPressed(new ButtonDriveTest(drivetrain, 0.0, 0.0));
    //xboxX.whenPressed(new ButtonDriveTest(drivetrain, 0.4, 0.4));
    //xboxB.whenPressed(new ButtonDriveTest(drivetrain, -0.4, -0.4));


    /**
     * GearBoxTest xbox controller Mapping
     * Uncomment as needed, make sure ROBOT_TYPE is correctly assigned!
     */
    //xboxY.whenPressed(new GearBoxTest(drivetrain, xboxController, 1.0, 1.0));
    //xboxA.whenPressed(new GearBoxTest(drivetrain, xboxController, -1.0, -1.0));
    //xboxX.whenPressed(new GearBoxTest(drivetrain, xboxController, 0.0, 0.0));

    /**
     * Test Buttons if you need to STOP, FORWARD OR REVERSE
     *
     * Comment out as needed, and change ROBOT_TYPE!
     */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

      //Uncomment this if you'd like to drive forward, forever
      //Make sure ROBOT_TYPE is set!

      //return new AutoDriveForwardForever(drivetrain, this);

      //Edit the second argument for the amount of seconds you'd like to drive
      //Make sure ROBOT_TYPE is set!

      //return new AutoDriveForwardTimer(drivetrain, 7.0);

      //Autonomous Command that doesnt work
      return new FollowTrajectory(drivetrain).ExampleAutonomousCommand();
      //return null;
  }
}
