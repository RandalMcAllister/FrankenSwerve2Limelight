// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveForward;
import frc.robot.commands.TurnBot;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveModule;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ControlSystem;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_robotDrive = new DriveTrain();
  private final ExampleSubsystem m_example = new ExampleSubsystem(); 

  private final DriveForward m_driveForward = new DriveForward(m_robotDrive);
  private final TurnBot m_turnBot = new TurnBot(m_robotDrive);
  //private final aimNote m_aimNote = new aimNote(m_robotDrive);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverJoystick =
      new Joystick(OperatorConstants.kDriverControllerPort);

  private final JoystickButton driveForward = new JoystickButton(m_driverJoystick, 11);
  private final JoystickButton turnBot = new JoystickButton(m_driverJoystick, 9
  );
  private final JoystickButton aimNote = new JoystickButton(m_driverJoystick, 7
  );

  
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure data on the SmartDashboard
    double counter = 0.0; SmartDashboard.putNumber("Counter", counter++);

    
    //SmartDashboard.putData(CommandScheduler.getInstance());
        

    // Starts recording to data log
    DataLogManager.start();

    // Record both DS control and joystick data
    DriverStation.startDataLog(DataLogManager.getLog());


    // Configure the trigger bindings
    configureBindings();

    driveForward.whileTrue(m_driveForward);
    turnBot.whileTrue(m_turnBot);
    //aimNote.whileTrue(m_aimNote);

    //Configure driving default
    m_robotDrive.setDefaultCommand(
      // Forward motion controls x speed (forward), sideways motion controls y speed (sideways).
        new RunCommand(
          () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverJoystick.getY(), DriveConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverJoystick.getX(), DriveConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverJoystick.getZ(), DriveConstants.kDriveDeadband),
                true), m_robotDrive)
        );
  
  }


  


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //    .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_buttons.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_example);
  }
}
