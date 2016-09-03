package controller;


import DAO.AirCompanyDAO;
import DAO.PlaneDAO;
import model.AirCompany;
import model.CargoPlane;
import model.PassengerPlane;
import model.Plane;
import view.menu.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuControllerNew {

    MainMenu mainMenu;
    MenuAddPlane menuAddPlane;
    MenuConfigurePassPlane menuConfigurePassPlane;
    MenuConfigureCargoPlane menuConfigureCargoPlane;
    MenuFindPlane menuFindPlane;
    AirCompanyDAO airCompanyDAO;
    PlaneDAO planeDAO;

    public MenuControllerNew()throws IOException{
        airCompanyDAO = new AirCompanyDAO();
        mainMenu = new MainMenu();
        menuAddPlane = new MenuAddPlane();
        menuConfigurePassPlane = new MenuConfigurePassPlane();
        menuConfigureCargoPlane = new MenuConfigureCargoPlane();
        menuFindPlane = new MenuFindPlane();
        planeDAO = new PlaneDAO();

    }





    public void mainMenuLogic() throws IOException {
        while (true) {
            System.out.println(mainMenu.showMainMenu());
            int mainMenuItem = getChoice(1);
            switch (mainMenuItem) {
                case 1:
                    menuAddPlaneLogic();
                    break;
                case 2:
                    System.out.println("Total cargo capacity:" + airCompanyDAO.getTotalLoadCapacity() +
                            ", total passengers capacity:" + airCompanyDAO.getTotalPassengers());
                    break;
                case 3:
                    menuFindPlaneLogic();
                    break;
                case 4:
                    System.out.println("List of planes sorted by distance:\r\n" );
                    List<Plane> planeList = airCompanyDAO.sortByDistance();
                    for(Plane plane: planeList){
                        System.out.println(plane);
                    }
                    break;
                default:
                    System.out.println("Please choose item from 1 to 4");
                    break;
            }
        }

    }

    public void menuAddPlaneLogic() throws IOException{
        int menuAddPlaneChoice;
        do {
            menuAddPlane.showMenuAddPlane();
            menuAddPlaneChoice = getChoice(1);
            System.out.println("your choice is " + menuAddPlaneChoice);
            switch (menuAddPlaneChoice){
                case 1:
                    menuConfigurePassPlaneLogic();
                    menuAddPlaneChoice = 1;
                    break;
                case 2:
                    menuConfigureCargoPlaneLogic();
                    menuAddPlaneChoice = 2;
                    break;
                default:
                    System.out.println("please get your choice from 1 to 2");
                    break;
            }
        }while(menuAddPlaneChoice != 1 && menuAddPlaneChoice !=2);
    }

    public void menuConfigurePassPlaneLogic() throws IOException {
//---------------------configure quantity of passengers-------------------------------------------
        int menuConfigurePassPlaneItem1Choice;
        do{
            menuConfigurePassPlane.showMenuConfigurePassPlaneItem1();
            menuConfigurePassPlaneItem1Choice = getChoice(1);
            if(menuConfigurePassPlaneItem1Choice > 350 || menuConfigurePassPlaneItem1Choice < 50)
                System.out.println("Please enter quantity in range 50 - 350");
        }while (menuConfigurePassPlaneItem1Choice > 350 || menuConfigurePassPlaneItem1Choice < 50);

//----------------------creating plane and adding it to fleet ------------------------------------
        int id = airCompanyDAO.getTotalQuantityPlanes() + 1;
        airCompanyDAO.setTotalQuantityPlanes(id);

        airCompanyDAO.addPlane(planeDAO.createPassengerPlane(id, menuConfigurePassPlaneItem1Choice));
        System.out.println("Plane is added!\r\n");
    }

    public void menuConfigureCargoPlaneLogic() throws IOException{
        //---------------------configure weight of cargo-------------------------------------------
        int menuConfigureCargoPlaneItem1Choice;
        do{
            menuConfigureCargoPlane.showMenuConfigureCargoPlaneItem1();
            menuConfigureCargoPlaneItem1Choice = getChoice(1);
            if(menuConfigureCargoPlaneItem1Choice > 25000 || menuConfigureCargoPlaneItem1Choice < 8000)
                System.out.println("Please enter weight of cargo in range 8000 - 25000");
        }while (menuConfigureCargoPlaneItem1Choice > 25000 || menuConfigureCargoPlaneItem1Choice < 8000);


        int id = airCompanyDAO.getTotalQuantityPlanes() + 1;
        airCompanyDAO.setTotalQuantityPlanes(id);
        airCompanyDAO.addPlane(planeDAO.createCargoPlane(id, menuConfigureCargoPlaneItem1Choice));
        System.out.println("Plane is added!\r\n");
    }

    public void menuFindPlaneLogic() throws IOException{
        int menuFindPlaneChoiceMin = 0;
        int menuFindplaneChoiceMax = 0;
        do{
            menuFindPlane.showMenuFindPlaneItem1();
            int[] rangeFuelConsumtion = getChoice("range");
            menuFindPlaneChoiceMin = rangeFuelConsumtion[0];
            menuFindplaneChoiceMax = rangeFuelConsumtion[1];
//            System.out.println("menuFindPlaneChoiceMin:" + menuFindPlaneChoiceMin + ",menuFindplaneChoiceMax:" + menuFindplaneChoiceMax);

            if(menuFindPlaneChoiceMin > menuFindplaneChoiceMax)
                System.out.println("Please enter min and max digits accordingly");
        }while(menuFindPlaneChoiceMin > menuFindplaneChoiceMax);
        List<String> foundPlanes = airCompanyDAO.findPlane(menuFindPlaneChoiceMin, menuFindplaneChoiceMax);//airCompany.findPlane(menuFindPlaneChoiceMin, menuFindplaneChoiceMax);
        System.out.println("\r\nSorted list of planes depending on distance:\r\n");
        for(String s: foundPlanes) {
            System.out.println(s);
        }

    }

    public int getChoice(int integer){
        System.out.print("Please enter your choise:");
        Scanner sc=new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public int[] getChoice(String range){
        System.out.print("Please enter your choice:");
        Scanner sc=new Scanner(System.in);
        String choice = sc.nextLine();
//        System.out.println("Choice string:" + choice);
        String[] strArr = choice.split(" ", 2);
//        System.out.println("String array:" + Arrays.toString(strArr));
        int[] intArray = new int[2];

        int i = 0;
        for(String s: strArr) {
            intArray[i] = Integer.valueOf(strArr[i]);
//            System.out.println("Value of Integer.valueOf(strArray[" + i + "]:" + intArray[i]);
            i++;
        }
//        System.out.println("you have entered:" + Arrays.toString(intArray));
        return intArray;
    }

}
