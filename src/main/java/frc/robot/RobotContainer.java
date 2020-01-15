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
import frc.robot.commands.AutoDriveForwardTimer;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveTank;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import static frc.robot.Constants.ROBOT_TYPE;

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



  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(ROBOT_TYPE);
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
    xboxBack = new JoystickButton(xboxController, 7);
    xboxStart = new JoystickButton(xboxController, 8);
    xboxLS = new JoystickButton(xboxController, 9);
    xboxRS = new JoystickButton(xboxController, 10);
  }

  private void configureButtonBindings() {
    //MODE BUTTONS
    xboxLB.whenPressed(new DriveTank(drivetrain, xboxController));
    xboxRB.whenPressed(new DriveArcade(drivetrain, xboxController));

    /**
     * ButtonDriveTest xbox controller Mapping
     * Uncomment as needed, make sure ROBOT_TYPE is correctly assigned!
     */
    //xboxA.whenPressed(new ButtonDriveTest(drivetrain, 0.0, 0.0));
    //xboxX.whenPressed(new ButtonDriveTest(drivetrain, 0.4, 0.4));
    //xboxB.whenPressed(new ButtonDriveTest(drivetrain, -0.4, -0.4));



    /**
     * Test Buttons if you need to STOP, FORWARD OR REVERSE
     *
     * Comment out as needed, and change ROBT TYPE!
     */


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous


      //return new AutoDriveForwardForever(drivetrainSubsystem, this);
    return new AutoDriveForwardTimer(drivetrain, 3.0);

  }


}