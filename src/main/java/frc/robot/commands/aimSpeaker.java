package frc.robot.commands;



import java.security.Key;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class aimSpeaker extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_subsystem;

  /* 
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    */

    private double xSpeed;
    private double ySpeed;
    private double zRotation;

    /* 
    private double Kp = -0.1;
    private double min_command = -0.05;
    private double heading_error = 0;
    private double steering_adjust = 0.0;
    */
    
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public aimSpeaker(DriveTrain subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    
 
    if (DriverStation.getAlliance() == Alliance.Red) {
      LimelightHelpers.setPipelineIndex("limelight", 2);

    } else if (DriverStation.getAlliance() == Alliance.Blue) {
      LimelightHelpers.setPipelineIndex("limelight", 3);

    } else {}
  
  
    
    double zRotation = LimelightHelpers.getTX("limelight");
    
    /*
    heading_error = -tx;
    
    if (Math.abs(heading_error) > 1.0) 
    {
        if (heading_error < 0) 
        {
            steering_adjust = Kp*heading_error + min_command;
        } 
        else 
        {
            steering_adjust = Kp*heading_error - min_command;
        }
    } 
      


    zRotation = steering_adjust;
    */

    m_subsystem.drive(xSpeed, ySpeed, zRotation, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.drive(0, 0, 0,false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
