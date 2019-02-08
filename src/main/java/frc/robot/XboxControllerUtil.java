package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;

public class XboxControllerUtil {
	
	XboxController xbox = Robot.m_oi.xbox;
	
	public double get(String joy) {
		if (joy.equals("A"))
			return 1;
		if (joy.equals("B"))
			return 2;
		if (joy.equals("X"))
			return 3;
		if (joy.equals("Y"))
			return 4;
		if (joy.equals("LB"))
			return 5;
		if (joy.equals("RB"))
			return 6;
		if (joy.equals("Back"))
			return 7;
		if (joy.equals("Start"))
			return 8;
		if (joy.equals("L3"))
			return 9;
		if (joy.equals("R3"))
			return 10;
		return 0;
	}
	
	public double getTrigger(String hand) {
		if (hand.equals("left"))
			return xbox.getTriggerAxis(Hand.kLeft);
		if (hand.equals("right"))
			return xbox.getTriggerAxis(Hand.kRight);
		return 0;
	}
	
	public boolean isTriggerPressed(String hand) {
		double value = 0;
			if (hand.equals("left"))
				value = xbox.getTriggerAxis(Hand.kLeft);
			if (hand.equals("right"))
				value = xbox.getTriggerAxis(Hand.kRight);
		return (Math.abs(value) > 20);
	}
	
	public boolean isTriggerPressed(String hand, double threshold) {
		double value = 0;
			if (hand.equals("left"))
				value = xbox.getTriggerAxis(Hand.kLeft);
			if (hand.equals("right"))
				value = xbox.getTriggerAxis(Hand.kRight);
		return (Math.abs(value) > threshold);
	}
	
	
}
