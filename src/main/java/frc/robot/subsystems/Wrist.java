/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Servo;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.PID.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  
 // Servo servo = RobotMap.wristServo;
 private WPI_TalonSRX wrist;
 private PIDSubsystemPositions posData;


 public Wrist() {
  wrist = new WPI_TalonSRX(9);
  wrist.configSelectedFeedbackSensor(FeedbackDevice.Analog);

  wrist.configForwardSoftLimitEnable(true);
  wrist.configForwardSoftLimitThreshold(4080);
  wrist.configReverseSoftLimitEnable(true);
  wrist.configReverseSoftLimitThreshold(10);

  PIDData[] array = new PIDData[3];
  array[0] = new PIDData(-16);
  array[1] = new PIDData(-80); 
  array[2] = new PIDData (420);
  
  posData = new PIDSubsystemPositions(array);
 }

  public void set(double speed) {
    wrist.set(speed);
  }

  public void nextStage() {

  }

  public void prevStage() {

  }
  
  public int getPos() {
    return wrist.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
