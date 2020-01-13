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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoDriveForwardForever;
import frc.robot.commands.ButtonDriveTestCommand;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveTank;
import frc.robot.lib.Direction;
import frc.robot.lib.RobotType;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

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
  public static JoystickButton xboxBack;
  public static JoystickButton xboxStart;
  public static JoystickButton xboxLS;
  public static JoystickButton xboxRS;
  public static Command currentCommand = null;



  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem(RobotType.TESTBOT);
  private final ColorWheelSubsystem colorWheelSubsystem = new ColorWheelSubsystem(this);
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem("limelight");


  // Define all OI devices here
  XboxController xboxController = new XboxController(OIConstants.xboxControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    buttonSetup();
    configureButtonBindings();


//    CommandScheduler.getInstance().registerSubsystem(colorWheelSubsystem, shooterSubsystem);
//  We do not need to register subsystems, this is done automatically

    drivetrainSubsystem.setDefaultCommand(new DriveArcade(drivetrainSubsystem, xboxController));
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
    xboxBack = new JoystickButton(xboxController, 7);
    xboxStart = new JoystickButton(xboxController, 8);
    xboxLS = new JoystickButton(xboxController, 9);
    xboxRS = new JoystickButton(xboxController, 10);
  }

  private void configureButtonBindings() {

    //MODE BUTTONS
    xboxLB.whenPressed(new DriveTank(drivetrainSubsystem, xboxController));
    xboxRB.whenPressed(new DriveArcade(drivetrainSubsystem, xboxController));
    xboxX.whenPressed(new ButtonDriveTestCommand(Direction.FORWARD, drivetrainSubsystem, this));
    xboxB.whenPressed(new ButtonDriveTestCommand(Direction.REVERSE, drivetrainSubsystem, this));
    xboxA.whenPressed(new ButtonDriveTestCommand(Direction.STOP, drivetrainSubsystem, this));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoDriveForwardForever(drivetrainSubsystem);
  }


  public void setNewCommand(Command command) {

    if (currentCommand != null) {
      currentCommand.cancel();
      currentCommand = command;
    } else {

      currentCommand = command;

    }

  }
}