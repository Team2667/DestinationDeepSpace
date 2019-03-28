/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PivotInit extends Command {

  int temp = 0;

  public PivotInit() {
    requires(Robot.m_pivot);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    temp = 0;
    Robot.m_pivot.lowerIndef();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    temp++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.m_pivot.getVelocity() == 0 && temp > 10);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_pivot.resetPos();
    Robot.m_pivot.setFirstStage();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
