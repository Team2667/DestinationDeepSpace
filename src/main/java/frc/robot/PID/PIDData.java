package frc.robot.PID;

public class PIDData{
    private double pValue = .1;
    private double iValue = 0;
    private double dValue = 1;
    private double minPower = -1;
    private double maxPower = 1;
    private double position;

    public PIDData(double position){
        this.position = position;
    }

    public double getPosition(){
        return position;
    }

    public double getP(){
        return pValue;
    }

    public double getI(){
        return iValue;
    }

    public double getD(){
        return dValue;
    }

    public double getMinPower(){
        return minPower;
    }

    public double getMaxPower(){
        return maxPower;
    }

    public void setP(double p){
        this.pValue = p;
    }

    public void setI(double i){
        this.iValue = i;
    }

    public void setD(double d){
        this.dValue = d;
    }

    public void setMinPower(double power){
        this.minPower = power;
    }

    public void setMaxPower(double power){
        this.maxPower = power;
    }

}

