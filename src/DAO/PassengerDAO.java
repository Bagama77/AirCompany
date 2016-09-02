package DAO;


import model.Passenger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PassengerDAO {
    private String passengersDBFile = "E:\\AirCompany\\src\\DB\\passengersDb.txt";

    public Passenger createPassenger(String name, int weightOfLuggage, int planeId) throws IOException{
        BufferedWriter passengerWriter = new BufferedWriter(new FileWriter(passengersDBFile, true));
        passengerWriter.write("Passenger: -name:" + name + " -weight of luggage:" + weightOfLuggage + " -plane id:" + planeId + "\r\n");
        passengerWriter.flush();
        passengerWriter.close();
        return new Passenger(name, weightOfLuggage, planeId);
    }
}
