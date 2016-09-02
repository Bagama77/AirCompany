package controller;
import DAO.AirCompanyDAO;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
//        new controller.MenuController().mainMenuLogic();
//        AirCompanyDAO airCompanyDAO = new AirCompanyDAO();
//        airCompanyDAO.getTotalLoadCapacity();
//        airCompanyDAO.getTotalPassengers();
//        System.out.println("Fuel cons 100-700: ");
//        airCompanyDAO.findPlane(100, 700);
//        System.out.println("Fuel cons 1000-1700: ");
//        airCompanyDAO.findPlane(1000, 1700);
//        System.out.println("Fuel cons 1000-5000: ");
//        airCompanyDAO.findPlane(1000, 5000);
////
//
//        airCompanyDAO.sortByDistance();

        new MenuControllerNew().mainMenuLogic();
    }
}
