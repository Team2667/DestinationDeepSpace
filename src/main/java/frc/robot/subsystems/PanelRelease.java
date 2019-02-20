/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PanelRelease extends Subsystem {
  
  public DoubleSolenoid topSol = new DoubleSolenoid(1, 2);
  public DoubleSolenoid botSol = new DoubleSolenoid(3, 4);
  public Compressor control = new Compressor(8);

  public PanelRelease() {
    control.setClosedLoopControl(true);
  }
  
  public void setClosedLoopControl(boolean val)  {
    control.setClosedLoopControl(val);
  }

  public void forwardTop() {
    topSol.set(DoubleSolenoid.Value.kForward);
  }

  public void backTop() {
    topSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void forwardBot() {
    botSol.set(DoubleSolenoid.Value.kForward);
  }

  public void backBot() {
    botSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void stop() {
    topSol.set(DoubleSolenoid.Value.kOff);
    botSol.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }



}
