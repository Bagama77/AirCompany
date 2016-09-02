package model;

import java.io.*;

public class Cargo {
    public int id;
    private int weight;
    private int amount;
    private String cargoIdDbFile = "E:\\AirCompany\\src\\DB\\cargoIdDb.txt";
    private String cargoIdDbFile2 = "E:\\AirCompany\\src\\DB\\Id.txt";
    public Cargo(int weight, int amount) {
        this.weight = weight;
        this.amount = amount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId() throws IOException{
        BufferedReader cargoIdReader = new BufferedReader(new FileReader(cargoIdDbFile));
        String temp;
        temp = cargoIdReader.readLine();
//        System.out.println("CargoIdDB reader:" + temp);
        cargoIdReader.close();
        BufferedWriter cargoIdWriter = new BufferedWriter(new FileWriter(cargoIdDbFile));
        if(temp.equalsIgnoreCase("") || temp == null) {//.equalsIgnoreCase(""))
            cargoIdWriter.write("1");
            cargoIdWriter.flush();
            this.id = 1;
            cargoIdWriter.close();
        } else {
//            System.out.println("CargoIdDb reader not 1:" + temp);
            int i = Integer.parseInt(temp) + 1;
//            System.out.println("Cargo Id parse:" + i);
            cargoIdWriter.write("" + i);
            this.id = i;
            cargoIdWriter.flush();
            cargoIdWriter.close();
        }
//        cargoIdReader.close();
//        cargoIdWriter.close();

    }


}
