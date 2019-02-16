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
import com.revrobotics.*;
import edu.wpi.first.wpilibj.Servo;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
	// These should be moved to the DriveTrain subsystem
	public static WPI_TalonSRX driveTrainLeftFront = new WPI_TalonSRX(1);
	public static WPI_TalonSRX driveTrainLeftRear = new WPI_TalonSRX(2);
	public static WPI_TalonSRX driveTrainRightFront = new WPI_TalonSRX(3);
	public static WPI_TalonSRX driveTrainRightRear = new WPI_TalonSRX(4);
	public static DifferentialDrive driveTrain;	
	public static SpeedControllerGroup left;
	public static SpeedControllerGroup right;

	// These should be moved to the elevator sysbsystem

//	public static Servo wristServo = new Servo(5);

	private static final boolean kDiscontinuityPresent = true;
	final static int kBookEnd_0 = 910;		/* 80 deg */
	final static int kBookEnd_1 = 1137;	/* 100 deg */
	final static int kTimeoutMs = 30;

	public static double pulseWidthWithoutOverflowsLF;
	public static double pulseWidthWithoutOverflowsLR;
	public static double pulseWidthWithoutOverflowsRF;
	public static double pulseWidthWithoutOverflowsRR;
	
	public static double rotLF = 0;
	public static double rotLR = 0;
	public static double rotRF = 0;
	public static double rotRR = 0;
	
	// Other Things
	public static void initQuadrature(WPI_TalonSRX talon) {
		/* get the absolute pulse width position */
		int pulseWidth = talon.getSensorCollection().getPulseWidthPosition();

		/**
		 * If there is a discontinuity in our measured range, subtract one half
		 * rotation to remove it
		 */
		if (kDiscontinuityPresent) {

			/* Calculate the center */
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;

			/**
			 * Apply the offset so the discontinuity is in the unused portion of
			 * the sensor
			 */
			pulseWidth -= newCenter; 
			
		/**
		 * Mask out the bottom 12 bits to normalize to [0,4095],
		 * or in other words, to stay within [0,360) degrees 
		 */
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
		talon.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}

		}

		public double ToDeg(int units) {
			double deg = units * 360.0 / 4096.0;
	
			/* truncate to 0.1 res */
			deg *= 10;
			deg = (int) deg;
			deg /= 10;
	
			return deg;
		}

		public static void EncoderCounter() {
			pulseWidthWithoutOverflowsLF = RobotMap.driveTrainLeftFront.getSensorCollection().getPulseWidthPosition() & 0xFFF;
    		pulseWidthWithoutOverflowsLR = RobotMap.driveTrainLeftRear.getSensorCollection().getPulseWidthPosition() & 0xFFF;
    		pulseWidthWithoutOverflowsRF = RobotMap.driveTrainRightFront.getSensorCollection().getPulseWidthPosition() & 0xFFF;
			pulseWidthWithoutOverflowsRR = RobotMap.driveTrainRightRear.getSensorCollection().getPulseWidthPosition() & 0xFFF;
		}

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
			// Encoder Stuff
			initQuadrature(driveTrainLeftFront);
			initQuadrature(driveTrainLeftRear);
			initQuadrature(driveTrainRightFront);
			initQuadrature(driveTrainRightRear);
		}
}
