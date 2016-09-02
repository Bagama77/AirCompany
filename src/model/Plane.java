package model;

import model.Engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public abstract class Plane implements Serializable, Comparable<Plane> {
    protected int id;
    protected int distance;
    protected int totalLoadCapacity;
    protected List<Engine> engines = new ArrayList<Engine>();

    protected abstract void setEngines(int quantity, int type);
    public abstract int getTotalLoadCapacity();
    public int getFuelConsumption(){
        int totalFuelConsumption = 0;
        for(Engine engine: engines){
            totalFuelConsumption += engine.getFuelConsumption();
        }
        return totalFuelConsumption;
    }


    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    protected void setDistance() {
        if(engines.get(0).getClass() == PropellerEngine.class)
            distance = engines.size() * 800;//distance for 1 propeller engine = 800 km
        if(engines.get(0).getClass() == ReactiveEngine.class)
            distance = engines.size() * 1300;//distance for 1 reactive engine = 1300 km
    }

    protected void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Plane plane1){
        return this.distance - plane1.distance;
    }


}
