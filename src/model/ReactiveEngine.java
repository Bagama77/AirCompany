package model;

import model.Engine;


public class ReactiveEngine extends Engine {

    public ReactiveEngine() {
        this.power = 10000;
        this.fuelConsumption = 1000;
    }

    public ReactiveEngine(int power, int fuelConsumption) {
        this.power = power;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getFuelConsumption() {
        return this.fuelConsumption;
    }

    public void getReactivePower(){
        System.out.println("reactive power is on..");
    }

    public void throwReactivepower(){
        System.out.println("reactive power throwed..");
    }
}
