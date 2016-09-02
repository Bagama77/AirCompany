package DAO;


import model.CargoPlane;
import model.PassengerPlane;
import model.Plane;

import java.io.IOException;

public class PlaneDAO {
    public Plane createPassengerPlane(int id, int totalQuantityOfPassengers) throws IOException{
        return new PassengerPlane(id, totalQuantityOfPassengers);
    }

    public Plane createPassengerPlane(int id, int totalQuantityOfPassengers, int quantityOfPassengersOnBoard, int quantityOfEngines, int enginesPower, int distance) throws IOException{
        return new PassengerPlane(id, totalQuantityOfPassengers, quantityOfPassengersOnBoard, quantityOfEngines, enginesPower, distance);
    }

    public Plane createCargoPlane(int id, int totalCargoWeight) throws IOException{
        return new CargoPlane(id, totalCargoWeight);
    }

    public Plane createCargoPlane(int id, int loadCapacity, int quantityOfEngines, int enginesPower, int distance, int quantityOfCargoOnBoard) throws IOException{
        return new CargoPlane(id, loadCapacity, quantityOfEngines, enginesPower, distance, quantityOfCargoOnBoard);
    }
}
