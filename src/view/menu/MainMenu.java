package view.menu;

public class MainMenu {
    String mainMenuItem1 = "1. Add Plane - 1;\r\n";
    String mainMenuItem2 = "2. Calculate total cargo and passengers capacity - 2\r\n";
    String mainMenuItem3 = "3. Find plane with defined fuel consumption - 3\r\n";
    String mainMenuItem4 = "4. List of planes sorted by distance - 4\r\n";

    public String showMainMenu(){
        return "\r\n----------------------MAIN MENU-------------------------------\r\n" + mainMenuItem1 + mainMenuItem2 + mainMenuItem3 + mainMenuItem4;
    }
}
