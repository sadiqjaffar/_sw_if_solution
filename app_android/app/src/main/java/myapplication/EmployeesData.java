package com.googleapi.myapplication;

import android.view.View;
import android.widget.TextView;

public class EmployeesData {

    int Heart_rate,Oxygen_saturation,temperature;
    String Blood_pressure;

    public EmployeesData(){}

    
    public EmployeesData(int Heart_rate,int Oxygen_saturation,int temperature,String Blood_pressure){

        this.Heart_rate=Heart_rate;
        this.Oxygen_saturation=Oxygen_saturation;
        this.temperature=temperature;
        this.Blood_pressure=Blood_pressure;

    }

    public int getHeart_rate() {
        return Heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        Heart_rate = heart_rate;
    }

    public int getOxygen_saturation() {
        return Oxygen_saturation;
    }

    public void setOxygen_saturation(int oxygen_saturation) {
        Oxygen_saturation = oxygen_saturation;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getBlood_pressure() {
        return Blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        Blood_pressure = blood_pressure;
    }
}

