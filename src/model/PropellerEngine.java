package model;

public class PropellerEngine extends Engine {
    public PropellerEngine() {
        this.power = 3000;
        this.fuelConsumption = 300;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getFuelConsumption() {
        return this.fuelConsumption;
    }

    public void getPropellerEngineOn(){
        System.out.println("propeller engine is on..");
    }

    public void getPropellerEngineOff(){
        System.out.println("propeller engine off..");
    }
}
