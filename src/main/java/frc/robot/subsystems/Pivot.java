import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pivot extends Subsystem {

    protected CANSparkMax pivotController;
    private double positions[] = {0, 50, 100};
    private double pValues[] = {1,1,1};
    private double iValues[] = {.0001,.0001,.0001};

    public final int HOME = 0;
    public final int NOMINAL = 1;
    public final int HIGH = 2;

    private final String HOME_POSITION = "HOME Position";
    private final String NOMINAL_POSITION = "NOMINAL Position";
    private final String HIGH_POSITION = "HIGH Position";

    private final String HOME_P_VALUE = "HOME p";
    private final String NOMINAL_P_VALUE = "NOMINAL p";
    private final String HIGH_P_VALUE = "HIGH p";

    private final String HOME_I_VALUE = "HOME i";
    private final String NOMINAL_I_VALUE = "NOMINAL i";
    private final String HIGH_I_VALUE = "HIGH i";
  
    public Pivot(){
        pivotController = new CANSparkMax(6,MotorType.kBrushless);
        SmartDashboard.putNumber(HOME_POSITION, positions[HOME]);
        SmartDashboard.putNumber(NOMINAL_POSITION, positions[NOMINAL]);
        SmartDashboard.putNumber(HIGH_POSITION, positions[HIGH]);
        SmartDashboard.putNumber(HOME_P_VALUE, pValues[HOME]);
        SmartDashboard.putNumber(NOMINAL_P_VALUE, pValues[NOMINAL]);
        SmartDashboard.putNumber(HIGH_P_VALUE, pValues[HIGH]);
        SmartDashboard.putNumber(HOME_I_VALUE, iValues[HOME]);
        SmartDashboard.putNumber(NOMINAL_I_VALUE, iValues[NOMINAL]);
        SmartDashboard.putNumber(HIGH_I_VALUE, iValues[HIGH]);
       
    }

    public void setPosition(int pos){
        
        pivotController.getPIDController().setP(pValues[pos]);
        pivotController.getPIDController().setI(iValues[pos]);
        pivotController.getPIDController().setReference(
            positions[pos], ControlType.kPosition);
    }

    public void setValuesFromSmartDashboard(){
        positions[HOME] = SmartDashboard.getNumber("HOME Position",0);
        positions[NOMINAL] = SmartDashboard.getNumber("NOMINAL Position",50);
        positions[HIGH] = SmartDashboard.getNumber("HIGH Position",100);
        pValues[HOME] = SmartDashboard.getNumber("HOME pValues",1);
        pValues[NOMINAL] = SmartDashboard.getNumber("NOMINAL pValues",1);
        pValues[HIGH] = SmartDashboard.getNumber("HIGH pValues",1);
        iValues[HOME] = SmartDashboard.getNumber("HOME iValues",.0001);
        iValues[NOMINAL] = SmartDashboard.getNumber("NOMINAL iValues",.0001);
        iValues[HIGH] = SmartDashboard.getNumber("HIGH iValues",.0001);

    }

  

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
}