package model;
import DAO.PassengerDAO;
import controller.MyRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PassengerPlane extends Plane {
        private int totalQuantityOfPassengers;
        private int totalLuggageAmount;
        private PassengerDAO passengerDAO = new PassengerDAO();
        private List<Passenger> passengersOnBoard = new ArrayList<Passenger>();

        public PassengerPlane(int id, int totalQuantityOfPassengers) throws IOException{
                this.setId(id);
                this.totalQuantityOfPassengers = totalQuantityOfPassengers;
                this.totalLuggageAmount = totalQuantityOfPassengers * 20;
                this.installEngines();

                setDistance();
                this.totalLoadCapacity = totalQuantityOfPassengers * (82 + 20);//82 - weight of one passenger, 20 - weight of luggage
                this.getPassengersOnBoard();
        }

        public PassengerPlane(int id, int totalQuantityOfPassengers, int quantityOfPassengersOnBoard, int quantityOfEngines, int enginesPower, int distance) throws IOException{
                this.setId(id);
                this.totalQuantityOfPassengers = totalQuantityOfPassengers;
                this.totalLuggageAmount = totalQuantityOfPassengers * 20;
                this.installEngines(quantityOfEngines, enginesPower);

                setDistance(distance);
                this.totalLoadCapacity = totalQuantityOfPassengers * (82 + 20);//82 - weight of one passenger, 20 - weight of luggage
                this.getPassengersOnBoard(quantityOfPassengersOnBoard);

        }



        public int getTotalQuantityOfPassengers() {
                return totalQuantityOfPassengers;
        }

        public void setTotalQuantityOfPassengers(int totalQuantityOfPassengers) {
                this.totalQuantityOfPassengers = totalQuantityOfPassengers;
        }

        public int getTotalLuggageAmount() {
                return totalLuggageAmount;
        }

        public List<Passenger> getListOfpassengers(){
                return passengersOnBoard;
        }

        private void getPassengersOnBoard() throws IOException{
                int quantityOfPassengers = new MyRandom().getRandomNumberInRange(totalQuantityOfPassengers/3, totalQuantityOfPassengers);
                //minimal quantity of passengers for flight is total quantity/3 + random quantity of passengers in range (66% of total quantity)
                for(int i = 0; i < quantityOfPassengers; i++){
                        passengersOnBoard.add(passengerDAO.createPassenger("" + i, new MyRandom().getRandomNumberInRange(0, 20), this.id));//luggage in range 0 - 20 kg
                }
        }

        private void getPassengersOnBoard(int quantityOfPassengersOnBoard) throws IOException{

                //minimal quantity of passengers for flight is total quantity/3 + random quantity of passengers in range (66% of total quantity)
                for(int i = 0; i <quantityOfPassengersOnBoard; i++){
                        passengersOnBoard.add(passengerDAO.createPassenger("" + i, new MyRandom().getRandomNumberInRange(0, 20), this.id));//luggage in range 0 - 20 kg
                }
        }

        private void installEngines(){
                if(totalQuantityOfPassengers <= 80){
                addEngines(2, "Propeller");
                } else if(totalQuantityOfPassengers >= 81 && totalLuggageAmount <=150){
                addEngines(4, "Propeller");
                } else if(totalQuantityOfPassengers >=151 && totalQuantityOfPassengers <=250){
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

        @Override
        public String toString(){
                return "\r\n" + "PassengerPlane: -id:" + this.getId() + " -quantity of passengers on board:" + this.passengersOnBoard.size() +
                " -distance:" + this.getDistance() + " -load Capacity:" + this.totalLoadCapacity + " -quantity of engines:" + engines.size() +
                " - engines power:" + (engines.get(0).getPower())*engines.size() + " -fuel cons.:" + engines.get(0).getFuelConsumption()*engines.size();
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