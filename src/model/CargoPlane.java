package model;
import DAO.CargoDAO;
import controller.MyRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CargoPlane extends Plane{
    private int totalCargoWeight;
    private int totalCargoAmount;
    private List<Cargo> cargoOnBoard = new ArrayList<Cargo>();
    CargoDAO cargoDAO = new CargoDAO();

    public CargoPlane(int id, int totalCargoWeight) throws IOException{
        this.setId(id);
        this.totalCargoWeight = totalCargoWeight;
        this.totalCargoAmount = totalCargoWeight/100;//
        installEngines();
        setDistance();
        this.totalLoadCapacity = totalCargoWeight;
        this.getCargoOnBoard();
    }

    public CargoPlane(int id, int loadCapacity, int quantityOfEngines, int enginesPower, int distance, int quantityOfCargoOnBoard) throws IOException{
        this.setId(id);
        this.totalCargoWeight = loadCapacity;
        this.totalCargoAmount = totalCargoWeight/100;//
        installEngines(quantityOfEngines, enginesPower);
        setDistance(distance);
        this.totalLoadCapacity = totalCargoWeight;
        this.getCargoOnBoard(quantityOfCargoOnBoard);
    }

    private void installEngines(){
        if(totalCargoWeight <= 8000){
            addEngines(2, "Propeller");
        } else if(totalCargoWeight > 8000 && totalCargoWeight <=15000){
            addEngines(4, "Propeller");
        } else if(totalCargoWeight >15000 && totalCargoWeight <=25000){
            addEngines(2, "Reactive");
        } else {
            addEngines(4, "Reactive");
        }
    }

    private void installEngines(int quantity, int power){
        if(power/quantity > 3000){
            addEngines(quantity, "Reactive");
        } else addEngines(quantity, "Propeller");
    }

    private void addEngines(int quantity, String type){
        for(int i  = 0; i < quantity; i++){
            if(type.equals("Reactive"))
                engines.add(new ReactiveEngine());
            else engines.add(new PropellerEngine());
        }
    }


    public List<Cargo> getListOfCargo(){
        return cargoOnBoard;
    }

    private void getCargoOnBoard() throws IOException{
        int quantityOfCargo = new MyRandom().getRandomNumberInRange(totalCargoAmount/3, totalCargoAmount);
        //minimal amount of cargo for flight is total amount/3 + random quantity of cargo in range (66% of total amount)
        int remainFreeWeight = totalCargoWeight;
        int remainFreeAmount = totalCargoAmount;
        for(int i = 0; i < quantityOfCargo; i++){
//            System.out.println("remain free weight: " + remainFreeWeight);
            Cargo temp;
            if(remainFreeWeight > 1 && remainFreeAmount >1) {
                temp = cargoDAO.createCargo(new MyRandom().getRandomNumberInRange(1, remainFreeWeight), new MyRandom().getRandomNumberInRange(1, remainFreeAmount), this.id);
            } else {
                break;
            }
//            if(temp.getWeight() > remainFreeWeight || temp.getAmount() > remainFreeAmount)
//                    break;
//            }
//            System.out.println("Adding cargo on board: weight - " + temp.getWeight() + ", amount, m3 - " + temp.getAmount());
            cargoOnBoard.add(temp);
            remainFreeWeight -= temp.getWeight();
            remainFreeAmount -= temp.getAmount();
        }
//        System.out.println("List of cargo: " + cargoOnBoard);
    }

    private void getCargoOnBoard(int quantityOfCargoOnBoard) throws IOException{
//        int quantityOfCargo = new MyRandom().getRandomNumberInRange(totalCargoAmount/3, totalCargoAmount);
        //minimal amount of cargo for flight is total amount/3 + random quantity of cargo in range (66% of total amount)
        int remainFreeWeight = totalCargoWeight;
        int remainFreeAmount = totalCargoAmount;
        Cargo temp = null;
        for(int i = 0; i < quantityOfCargoOnBoard; i++){
//            System.out.println("remain free weight: " + remainFreeWeight + ", remain free space:" + remainFreeAmount);
//            Cargo temp;
            if(remainFreeWeight > 1 && remainFreeAmount >1) {
                temp = cargoDAO.createCargo(new MyRandom().getRandomNumberInRange(1, remainFreeWeight), new MyRandom().getRandomNumberInRange(1, remainFreeAmount), this.id);
            } else {
                break;
            }
//            if(temp.getWeight() > remainFreeWeight || temp.getAmount() > remainFreeAmount)
//                break;
//            System.out.println("Adding cargo on board: weight - " + temp.getWeight() + ", amount, m3 - " + temp.getAmount());
            cargoOnBoard.add(temp);
            remainFreeWeight -= temp.getWeight();
            remainFreeAmount -= temp.getAmount();
        }
//        System.out.println("List of cargo: " + cargoOnBoard);
    }

    @Override
    public String toString(){
        return "CargoPlane: -id:" + this.getId() + " -quantity of cargo on board:" + this.cargoOnBoard.size() +
                " -distance:" + this.getDistance() + " -load Capacity:" + this.totalLoadCapacity + " -quantity of engines:" + engines.size() +
                " - engines power:" + engines.get(0).getPower() * engines.size() + " -fuel cons.:" +
                engines.get(0).getFuelConsumption()*engines.size();
    }

    @Override
    protected void setEngines(int quantity, int type) {
        for (int i = 0; i < quantity; i++) {
            if (type == 0)
                engines.add(new PropellerEngine());
            else engines.add(new ReactiveEngine());
        }

    }

    @Override
    public int getTotalLoadCapacity() {
        return totalLoadCapacity;//totalQuantityOfPassengers * (82 + 20);
    }
}
