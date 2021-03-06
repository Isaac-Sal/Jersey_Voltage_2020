/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.Robot;

public class AutoMoveFoward extends CommandBase {
  int leftEncoder = 0;
  int rightEncoder = 0;
  double leftInches = 0;
  double rightInches = 0;
  double averageInches = 0;
  /**
   * Creates a new AutoMoveFoward.
   */
  public AutoMoveFoward() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    leftEncoder = -1 * getDriveBase().m_lefttalon1.getSelectedSensorPosition(0);
    rightEncoder = -1 * getDriveBase().m_righttalon2.getSelectedSensorPosition(0);
    leftInches = leftEncoder / 4096.0 * Math.PI * 6;
    rightInches = rightEncoder / 4096.0 * Math.PI * 6;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    averageInches = (leftInches + rightInches) / 2;
    if (averageInches < 24) {
      Robot.getDriveBase().setLeftMotorLevel(0.75);
      Robot.getDriveBase().setRightMotorLevel(0.75);
      leftEncoder = -1 * getDriveBase().m_lefttalon1.getSelectedSensorPosition(0);
      rightEncoder = -1 * getDriveBase().m_righttalon2.getSelectedSensorPosition(0);
      leftInches = leftEncoder / 4096.0 * Math.PI * 6;
      rightInches = rightEncoder / 4096.0 * Math.PI * 6;
    }
  }

  public DriveBase getDriveBase() {
    Robot.getInstance();
    return Robot.getDriveBase();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.getDriveBase().setLeftMotorLevel(0);
    Robot.getDriveBase().setRightMotorLevel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (averageInches < 24) {
      return false;
    }

    else {
      return true;
    }
  }
}
