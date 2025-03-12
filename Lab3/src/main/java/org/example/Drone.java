package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drone extends Aircraft {
    private double batteryLife;

    public Drone(String model, String callSign, double batteryLife) {
        super(model,callSign);
        this.batteryLife = batteryLife;
    }

    @Override
    public String toString() {
        return "Model: " + this.getModel() + " CallSign: " + this.getCallSign() +
                " BatteryLife: " + this.batteryLife;
    }
}
