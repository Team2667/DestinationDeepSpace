/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class InitialStageAll extends CommandGroup {

  protected int stage;

  public InitialStageAll() {
    stage = Robot.m_lift.getStage();
    for (int x = stage; x == 0; x--) {
      addSequential(new PrevStageAll());
    }
  }
}
