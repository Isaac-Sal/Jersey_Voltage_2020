/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

public class defaultDriveBase extends CommandBase {
  /**
   * Creates a new defaultDriveBase.
   */
  public defaultDriveBase() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.getDriveBase());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.getDriveBase().setSafetyEnabled(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = 0;
    double turn = 0;

    if (Math.abs(OI.getInstance().getDrive()) < 0.10) {
			forward = 0;
		}
		if (Math.abs(OI.getInstance().getTurn()) < 0.10) {
			turn = 0;
    }

    Robot.getDriveBase().arcadeDrive(forward, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.getDriveBase().setSafetyEnabled(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
