/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TestLimitSwitchStabby extends Command {
  private double temp = 0;

  public TestLimitSwitchStabby() {
    requires(Robot.m_stabby);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_stabby.extendIndef(.3);
    temp = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    temp++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.m_stabby.getVelocity() == 0 && temp > 10);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_stabby.stop();
    Robot.m_stabby.resetPos();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
