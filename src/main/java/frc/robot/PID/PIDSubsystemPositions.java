/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Add your docs here.
 */
public class PIDSubsystemPositions {
    private List<PIDData> dataForPositions;
    private int pos = 0;

    public PIDSubsystemPositions(PIDData[] positionArray){
        dataForPositions = Arrays.asList(positionArray);
    }

    public PIDData nextPos(){
        if (dataForPositions.size() != pos + 1 ){
            return dataForPositions.get(pos + 1);
        } else {
            return dataForPositions.get(pos);
        }
    }

    public PIDData prevPos(){
        if (pos != 0) {
            return dataForPositions.get(pos - 1);
        } else {
            return dataForPositions.get(pos);
        }
    }

    public void setNextPos() {
        if (pos != dataForPositions.size()-1) {
            pos++;
        } 
    }

    public void setPrevPos() {
        if (pos != 0) {
            pos--;
        } 
    }

    public int getPos() {
        return pos;
    }




}
