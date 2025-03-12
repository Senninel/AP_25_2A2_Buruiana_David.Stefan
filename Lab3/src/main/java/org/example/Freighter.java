package org.example;

public class Freighter extends Aircraft implements CargoCapable{
    private double maxPayLoad;

    public Freighter(String model, String callSign, double maxPayLoad) {
        super(model, callSign);
        this.maxPayLoad = maxPayLoad;
    }
    @Override
    public double getMaxPayLoad() {
        return maxPayLoad;
    }
    @Override
    public String toString() {
        return "Model: " + this.getModel() + " callSign: " + this.getCallSign() +
                " PayLoad: " + this.getMaxPayLoad();
    }

}
