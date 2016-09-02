package model;

public class Passenger {
    String name;
    int weightOfLuggage;
    int planeID;

    public Passenger(String name, int weightOfLuggage, int planeID) {
        this.name = name;
        this.weightOfLuggage = weightOfLuggage;
        this.planeID = planeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeightOfLuggage() {
        return weightOfLuggage;
    }

    public void setWeightOfLuggage(int weightOfLuggage) {
        this.weightOfLuggage = weightOfLuggage;
    }
}
