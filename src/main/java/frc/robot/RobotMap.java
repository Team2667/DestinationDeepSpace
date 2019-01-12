/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
	public static WPI_TalonSRX driveTrainLeftFront = new WPI_TalonSRX(1);
	public static WPI_TalonSRX driveTrainLeftRear = new WPI_TalonSRX(2);
	public static WPI_TalonSRX driveTrainRightFront = new WPI_TalonSRX(3);
	public static WPI_TalonSRX driveTrainRightRear = new WPI_TalonSRX(4);
	public static DifferentialDrive driveTrain;
	public static SpeedControllerGroup left;
	public static SpeedControllerGroup right;
	// Other Things
		public static void init() {
			// Assigning numbers to parts
			driveTrainLeftFront.set(ControlMode.PercentOutput, 1);
			driveTrainLeftRear.set(ControlMode.PercentOutput, 2);
			driveTrainRightFront.set(ControlMode.PercentOutput, 3);
			driveTrainRightRear.set(ControlMode.PercentOutput, 4);
			// Inverting Right Side
			driveTrainRightFront.setInverted(true);
			driveTrainRightRear.setInverted(true);
			// Putting them in groups because you have to do that
			left = new SpeedControllerGroup(driveTrainLeftFront, driveTrainLeftRear);
			right = new SpeedControllerGroup(driveTrainRightFront, driveTrainRightRear);
			// Making it a differential drive
			driveTrain = new DifferentialDrive(left, right);
		}
}
