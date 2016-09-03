package DAO;
import controller.GetInteger;
import model.CargoPlane;
import model.PassengerPlane;
import model.Plane;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AirCompanyDAO {

    private String airCompanyDBFile = "src\\DB\\dbAirCompany.txt";//E:\\AirCompany\\
    private String planesDBFile = "src\\DB\\planesDb.txt";
    private String passengersDBFile = "src\\DB\\passengersDb.txt";
    private String cargoDBFile = "src\\DB\\cargoDb.txt";

    private BufferedReader airCompanyDBFileReader ;
    private BufferedReader planesDBFileReader ;
    private BufferedReader passengersDBFileReader ;
    private BufferedReader cargoDBFileReader ;

    public AirCompanyDAO() throws IOException{
    }

    public int getTotalLoadCapacity() throws IOException{
        planesDBFileReader = new BufferedReader(new FileReader(planesDBFile));
        String temp;
        String[] array = new String[40];
        int capacity = 0;
        while ((temp = planesDBFileReader.readLine()) != null){
            if(temp.contains("CargoPlane:")) {
                array = temp.split("-load Capacity:");
//              array = temp.split(":");
//                System.out.println("Get total load capacity , split:" + Arrays.toString(array));
                array = array[1].split(" ");
//                System.out.println("In string: " + array[0]);
                capacity += Integer.parseInt(array[0]);
//                System.out.println("Capacity: " + capacity);
            } else if(temp.contains("PassengerPlane:")){
                continue;
            } else break;
        }
        planesDBFileReader.close();
        return capacity;
    }

    public int getTotalPassengers() throws IOException{
        planesDBFileReader = new BufferedReader(new FileReader(planesDBFile));
        int passengers = 0;
        String temp;
        String[] array = new String[40];
        while ((temp = planesDBFileReader.readLine()) != null){
            if(temp.contains("PassengerPlane:")) {
//                System.out.println("find passenger plane!..");
//                array = temp.split("-quantity of passengers on board:");
                array = temp.split("-load Capacity:");

                array = array[1].split(" ");
//                System.out.println("In string: " + array[0]);
                passengers += (Integer.parseInt(array[0]))/100;
//                System.out.println("passengers: " + passengers);
            } else if(temp.contains("CargoPlane:")){
                continue;
            } else break;
        }
        planesDBFileReader.close();
        return passengers;
    }

    public List<Plane> sortByDistance() throws IOException{
        planesDBFileReader = new BufferedReader(new FileReader(planesDBFile));
        List<Plane> sortedByDistanceFleet = new ArrayList<Plane>();
        Plane tempPlane;
//        Collections.sort(sortedByDistanceFleet);
        int id, totalQuantityOfPassengers, quantityOfPassengersOnBoard = 0, distance, quantityOfEngines, enginesPower, loadCapacity = 0, quantityOfCargoOnBoard = 0;
        String temp;
        String[] array = new String[2];
        while ((temp = planesDBFileReader.readLine()) != null){
            //Getting id of plane
            id = new GetInteger().getInteger(temp, "-id:");

            //Getting total Quantity Of Passengers
            totalQuantityOfPassengers = new GetInteger().getInteger(temp, "-load Capacity:")/100;

            //Getting Quantity Of Passengers on board
            if(temp.contains("PassengerPlane:")) {
                quantityOfPassengersOnBoard = new GetInteger().getInteger(temp, "-quantity of passengers on board:");
            } else {
                loadCapacity = new GetInteger().getInteger(temp, "-load Capacity:");
                quantityOfCargoOnBoard = new GetInteger().getInteger(temp, "-quantity of cargo on board:");
            }
            //Getting distance
            distance = new GetInteger().getInteger(temp, "-distance:");

            //Getting engines
            quantityOfEngines = new GetInteger().getInteger(temp, "-quantity of engines:");

            //Getting engines power
            enginesPower = new GetInteger().getInteger(temp, "- engines power:");

            //CREATING PLANE
            if(temp.contains("PassengerPlane:")) {
                tempPlane = new PassengerPlane(id, totalQuantityOfPassengers, quantityOfPassengersOnBoard, quantityOfEngines, enginesPower, distance);
            } else {
                tempPlane = new CargoPlane(id, loadCapacity, quantityOfEngines, enginesPower, distance, quantityOfCargoOnBoard);
            }
            sortedByDistanceFleet.add(tempPlane);
//            System.out.println("Added new Plane:" + tempPlane);

        }
        Collections.sort(sortedByDistanceFleet);

        planesDBFileReader.close();
        return sortedByDistanceFleet;//.toString();
    }

    public List<String> findPlane (int minFuelConsumption, int maxFuelConsumption) throws IOException{
        if(minFuelConsumption > maxFuelConsumption) {
            System.out.println("min fuel consumption should be less than maximum fuel consumption");
            throw new IllegalArgumentException();
        }
        planesDBFileReader = new BufferedReader(new FileReader(planesDBFile));
        int fuelConsumption;
        List<String> planesList = new ArrayList<String>();
        String temp;
        String[] array = new String[2];
        while ((temp = planesDBFileReader.readLine()) != null){
            array = temp.split("-fuel cons.:");
            array = array[1].split(" ");
//            System.out.println("In string: " + array[0]);
            fuelConsumption = Integer.parseInt(array[0]);
//            System.out.println("fuel cons: " + fuelConsumption);
            if(fuelConsumption < maxFuelConsumption && fuelConsumption > minFuelConsumption){
//                System.out.println(temp);
                planesList.add(temp);
            }
        }
        planesDBFileReader.close();
        return planesList;
    }

    public void addPlane(Plane plane) throws IOException{
        BufferedWriter planeWriter = new BufferedWriter(new FileWriter(planesDBFile, true));
        planeWriter.write(plane.toString() + "\r\n");
        planeWriter.flush();
        planeWriter.close();
    }

    public int getTotalQuantityPlanes() throws IOException{
        int totalQuantityPlanes = 0;
        airCompanyDBFileReader = new BufferedReader(new FileReader(airCompanyDBFile));
        String temp;
        String[] array = new String[2];
        while ((temp = airCompanyDBFileReader.readLine()) != null){
            if(temp.contains("TotalQuantityPlanes:")){
                array = temp.split("TotalQuantityPlanes:");
                array = array[1].split(" ");
                totalQuantityPlanes = Integer.parseInt(array[0]);
                break;
            }
        }
        airCompanyDBFileReader.close();
        return totalQuantityPlanes;
    }

    public void setTotalQuantityPlanes(int idOfNewPlane) throws IOException{
        airCompanyDBFileReader = new BufferedReader(new FileReader(airCompanyDBFile));
        BufferedWriter airCompanyBDFileWriter = new BufferedWriter(new FileWriter(airCompanyDBFile));
        String[] arrayString = new String[2];
        String temp;
        int rowCounter = 0;
        while((temp = airCompanyDBFileReader.readLine()) != null){
            arrayString[rowCounter] = temp;
            rowCounter++;
        }
        arrayString[1] = "" + idOfNewPlane;
        for(int i = 0; i < 2; i++){
            if(i == 0)
                airCompanyBDFileWriter.write("AirCompany: " + arrayString[i] + "\r\n");
            else airCompanyBDFileWriter.write("TotalQuantityPlanes:" + arrayString[i] + "\r\n");
        }
        airCompanyBDFileWriter.flush();
        airCompanyBDFileWriter.close();
        airCompanyDBFileReader.close();
    }
}
