package com.poli.paginator.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskModel {

    protected List<ProcessModel> listProcess;

    protected List<Integer> positions;

    protected int limitMemory;

    protected int usedMemory;

    public TaskModel(int limitMemory) {
        this.limitMemory = limitMemory;
        this.positions = new ArrayList<Integer>(Collections.nCopies(limitMemory, 0));
    }

    public List<ProcessModel> getListProcess() {
        return listProcess;
    }

    public void setListProcess(List<ProcessModel> listProcess) {
        this.listProcess = listProcess;
    }

    public int getLimitMemory(){
        return limitMemory;
    }

    public void setLimitMemory(int limitMemory) {
        this.limitMemory = limitMemory;
    }

    public int getUsedMemory() {
        return this.usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }
    
    public int getAvailibleMemory() {
        return this.limitMemory - this.usedMemory;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }


}
