package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Collections.*;

public class AirCompany {


    private int totalQuantityPlanes;
    private List<Plane> fleet = new ArrayList<Plane>();



    public void setTotalQuantityPlanes(int totalQuantityPlanes) {
        this.totalQuantityPlanes = totalQuantityPlanes;
    }

    public int getTotalQuantityPlanes() {
        return totalQuantityPlanes;
    }

    @Override
    public String toString(){
        return "AirCompany:" + this.hashCode() + ", total quantity of planes: " + totalQuantityPlanes;
    }

//    public List<Plane> sortByDistance(){
//        List<Plane> sortedByDistanceFleet = fleet;
//        Collections.sort(sortedByDistanceFleet);
//        return sortedByDistanceFleet;
//    }
//
//    public List<Plane> findPlane(int minFuelConsumption, int maxFuelConsumption){
//        System.out.println("inside find plane..");
//        List<Plane> tempList = new ArrayList<Plane>();
//        int fuelConsumption;
//        for(Plane plane: fleet){
//            fuelConsumption = plane.getFuelConsumption();
//            System.out.println("fuel consumption of current plane:" + fuelConsumption);
//            if(fuelConsumption < maxFuelConsumption && fuelConsumption > minFuelConsumption){
//                tempList.add(plane);
//                System.out.println("current plane added!");
//            }
//        }
//        return tempList;
//    }

    //    public void addPlane(Plane plane){
//        fleet.add(plane);
//    }
//
//    public int getTotalLoadCapacity(){
//        int capacity = 0;
//        for(Plane plane: fleet){
//            capacity += plane.getTotalLoadCapacity();
//        }
//        return capacity;
//    }
//
//    public int getTotalPassengers(){
//        int passengers = 0;
//        PassengerPlane passengerPlane;
//        for(Plane plane: fleet){
//            if(plane instanceof PassengerPlane){
//                passengerPlane = (PassengerPlane) plane;
//                passengers += passengerPlane.getListOfpassengers().size();
//            }
//        }
//        return passengers;
//    }

}
