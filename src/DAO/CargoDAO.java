package DAO;

import model.Cargo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CargoDAO {
    private String cargoDBFile = "E:\\AirCompany\\src\\DB\\cargoDb.txt";
    public Cargo createCargo(int weight, int amount, int planeId) throws IOException{
        Cargo cargo = new Cargo(weight, amount);
        cargo.setId();
        BufferedWriter cargoWriter = new BufferedWriter(new FileWriter(cargoDBFile, true));
        cargoWriter.write("Cargo: -weight:" + weight + " -amount:" + amount + " -planeId:" + planeId + " -cargoId:" + cargo.getId() + "\r\n");
        cargoWriter.flush();
        cargoWriter.close();
        return cargo;
    }
}
