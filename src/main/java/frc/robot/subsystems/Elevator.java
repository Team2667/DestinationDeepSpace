/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.PID.*;


/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  	public  CANSparkMax test = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    public  CANPIDController elevatorController = test.getPIDController();
    private PIDSubsystemPositions posData;

  public Elevator() {
    PIDData[] array = new PIDData[3];
    array[0] = new PIDData(0);
    array[1] = new PIDData(30); 
    array[2] = new PIDData (100);
    
    posData = new PIDSubsystemPositions(array);
    test.getEncoder().setPosition(0);
    test.setInverted(true);
  }

  public void raiseIndef() {
    test.set(1);
  }

  public void raiseIndef(double speed) {
    test.set(speed);
  }

  public void lowerIndef() {
    test.set(-1);
  }

  public void lowerIndef(double speed) {
    test.set(speed);
  }
 
  public void nextStage() {
    PIDData temp = posData.nextPos();
    elevatorController.setP(temp.getP());
    elevatorController.setI(temp.getI());
    elevatorController.setD(temp.getD());
    elevatorController.setOutputRange(temp.getMinPower(), temp.getMaxPower());
    elevatorController.setReference(temp.getPosition(), ControlType.kPosition);
  }

  public void prevStage() {
    PIDData temp = posData.prevPos();
    elevatorController.setP(temp.getP());
    elevatorController.setI(temp.getI());
    elevatorController.setD(temp.getD());
    elevatorController.setOutputRange(temp.getMinPower(), temp.getMaxPower());
    elevatorController.setReference(temp.getPosition(), ControlType.kPosition);
  }

  public boolean isAtNextStage() {
    double temp = posData.nextPos().getPosition();
    double pos = test.getEncoder().getPosition();
    double low = temp - .5;
    double high = temp + .5;
    return (low <= pos && pos <= high);
  }

  public boolean isAtPrevStage() {
    double temp = posData.prevPos().getPosition();
    double pos = test.getEncoder().getPosition();
    double low = temp - .5;
    double high = temp + .5;
    return (low <= pos && pos <= high);
  }

  public void setNextStage() {
    posData.setNextPos();
  }

  public void setPrevStage() {
    posData.setPrevPos();
  }


  public void stop(){
    test.set(0);
  }

  public double getPos() {
    return test.getEncoder().getPosition();
  }

  public void resetPos() {
    test.getEncoder().setPosition(0);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
