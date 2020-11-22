package com.poli.paginator.models;

public class ProcessModel {

    public ProcessModel(int id, String name, int sizePage, String state){
        this.id = id;
        this.name = name;
        this.state = state;
        this.sizePage = sizePage;
    }

    public ProcessModel(int id){
        this.id = id;
    }


    public int id;

    public String name;

    public int sizePage;

    public String state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
