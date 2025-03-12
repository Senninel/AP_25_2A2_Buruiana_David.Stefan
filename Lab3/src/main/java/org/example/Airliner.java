package org.example;

import lombok.AllArgsConstructor;

public class Airliner extends Aircraft implements PassengerCapable{
    private int passengerCapacity;

    Airliner(String model, String callSign, int passengerCapacity) {
        super(model, callSign);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
        public int getPassengerCapacity() {
        return passengerCapacity;
        }
    @Override
    public String toString() {
       return "Model:" + this.getModel() + " CallSign: " + this.getCallSign() +
                " PassengerCapacity: " + this.getPassengerCapacity();
    }
}
