/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.*;
import frc.robot.PID.*;

/**
 * Add your docs here.
 */
public class Stabby extends Subsystem {
  protected CANSparkMax stabby = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANPIDController stabbyPID = stabby.getPIDController();
    public CANEncoder stabbyEncoder = stabby.getEncoder();
    private PIDSubsystemPositions posData;

    public Stabby() {
        PIDData[] array = new PIDData[3];
        array[0] = new PIDData(0);
        array[1] = new PIDData(30); 
        array[2] = new PIDData (100);

        posData = new PIDSubsystemPositions(array);
        //stabbyEncoder.setPosition(0);
    }

    public void extendIndef() {
        stabby.set(1);
      }
    
      public void extendIndef(double speed) {
        stabby.set(speed);
      }
    
      public void retractIndef() {
        stabby.set(-1);
      }
    
      public void retractIndef(double speed) {
        stabby.set(-speed);
      }

      public void nextStage() {
        PIDData temp = posData.nextPos();
        stabbyPID.setP(temp.getP());
        stabbyPID.setI(temp.getI());
        stabbyPID.setD(temp.getD());
        stabbyPID.setOutputRange(temp.getMinPower(), temp.getMaxPower());
        stabbyPID.setReference(temp.getPosition(), ControlType.kPosition);
      }
    
      public void prevStage() {
        PIDData temp = posData.prevPos();
        stabbyPID.setP(temp.getP());
        stabbyPID.setI(temp.getI());
        stabbyPID.setD(temp.getD());
        stabbyPID.setOutputRange(temp.getMinPower(), temp.getMaxPower());
        stabbyPID.setReference(temp.getPosition(), ControlType.kPosition);
      }
    
      public boolean isAtNextStage() {
        double temp = posData.nextPos().getPosition();
        double pos = stabbyEncoder.getPosition();
        double low = temp - .5;
        double high = temp + .5;
        return (low <= pos && pos <= high);
      }
    
      public boolean isAtPrevStage() {
        double temp = posData.prevPos().getPosition();
        double pos = stabby.getEncoder().getPosition();
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
        stabby.set(0);
      }
    
      public double getPos() {
        return stabby.getEncoder().getPosition();
      }

      public void resetPos() {
        stabby.getEncoder().setPosition(0);
      }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
