package frc.robot.subsystems;

import com.revrobotics.*;
import frc.robot.PID.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.PIDSubsystemPositions;

public class Pivot extends Subsystem {

    protected CANSparkMax pivot = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless);
    public CANPIDController pivotPID = pivot.getPIDController();
    public CANEncoder pivotEncoder = pivot.getEncoder();
    private PIDSubsystemPositions posData;

    public Pivot() {
        PIDData[] array = new PIDData[3];
        //array[0] = new PIDData(56.19102);
        array[0] = new PIDData(53.976524353027344); 
        array[1] = new PIDData(53.976524353027344); 
        array[2] = new PIDData(40);

        posData = new PIDSubsystemPositions(array);
        pivotEncoder.setPosition(0.0);
    }

    public void raiseIndef() {
        pivot.set(.5);
      }
    
      public void raiseIndef(double speed) {
        pivot.set(speed);
      }
    
      public void lowerIndef() {
        pivot.set(-.5);
      }
    
      public void lowerIndef(double speed) {
        pivot.set(-speed);
      }

      public void nextStage() {
        PIDData temp = posData.nextPos();
        pivotPID.setP(temp.getP());
        pivotPID.setI(temp.getI());
        pivotPID.setD(temp.getD());
        pivotPID.setOutputRange(temp.getMinPower(), temp.getMaxPower());
        pivotPID.setReference(temp.getPosition(), ControlType.kPosition);
      }

      public void currentStage() {
        PIDData temp = posData.currentPos();
        pivotPID.setP(temp.getP());
        pivotPID.setI(temp.getI());
        pivotPID.setD(temp.getD());
        pivotPID.setOutputRange(temp.getMinPower(), temp.getMaxPower());
        pivotPID.setReference(temp.getPosition(), ControlType.kPosition);
      }
    
      public void prevStage() {
        PIDData temp = posData.prevPos();
        pivotPID.setP(temp.getP());
        pivotPID.setI(temp.getI());
        pivotPID.setD(temp.getD());
        pivotPID.setOutputRange(temp.getMinPower(), temp.getMaxPower());
        pivotPID.setReference(temp.getPosition(), ControlType.kPosition);
      }
    
      public boolean isAtNextStage() {
        double temp = posData.nextPos().getPosition();
        double pos = pivotEncoder.getPosition();
        double low = temp - .5;
        double high = temp + .5;
        return (low <= pos && pos <= high);
      }
    
      public boolean isAtPrevStage() {
        double temp = posData.prevPos().getPosition();
        double pos = pivot.getEncoder().getPosition();
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
        pivot.set(0);
      }
    
      public double getPos() {
        return pivot.getEncoder().getPosition();
      }

      public void resetPos() {
        pivot.getEncoder().setPosition(0);
      }

      public void moveRotation(double rotations) {
        pivotPID.setOutputRange(-.5, .5);
        pivotPID.setReference(rotations, ControlType.kPosition);
      }

      public void setFirstStage() {
        posData.setFirstPos();
      }

      public double getVelocity() {
        return pivot.getEncoder().getVelocity();
      }
  

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
}