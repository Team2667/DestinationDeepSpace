/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public XboxController xbox;

  public JoystickButton raiseElevator;
  public JoystickButton lowerElevator;
  public JoystickButton stab;
  public JoystickButton unStab;
  public JoystickButton pivotHigh;
  public JoystickButton pivotLow;

	
	public OI() {
		xbox = new XboxController(0);

		raiseElevator = new JoystickButton(xbox, 5);
		raiseElevator.whileHeld(new RaiseElevatorManual());
		lowerElevator = new JoystickButton(xbox, 6);
		lowerElevator.whileHeld(new LowerElevatorManual());

		stab = new JoystickButton(xbox, 1);
		stab.whileHeld(new StabbyManualStab());
		unStab = new JoystickButton(xbox, 2);
		unStab.whileHeld(new StabbyManualUnstab());

		pivotHigh = new JoystickButton(xbox, 3);
		pivotHigh.whileHeld(new PivotForwardManual());
		pivotLow = new JoystickButton(xbox, 4);
		pivotLow.whileHeld(new PivotReverseManual());


		SmartDashboard.putData("Test Encoder", new TestEncoder());
		SmartDashboard.putData("ElevatorNext", new ElevatorNext());
		SmartDashboard.putData("ElevatorPrev", new ElevatorPrev());
		SmartDashboard.putData("Reset Spark Max Encoders", new ResetSparkEncoders());
		


	}
}
