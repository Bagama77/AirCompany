package view.menu;

public class MenuAddPlane {

    public String menuAddPlaneItem1 = "1. Add passenger plane - 1;\r\n";
    public String menuAddPlaneItem2  = "2. Add cargo plane - 2\r\n";
    public String titleMenuAddPlane  = "\r\n-------------MENU ADD PLANE--------------\r\n";

    public void showMenuAddPlane(){
        System.out.println(titleMenuAddPlane + menuAddPlaneItem1  + menuAddPlaneItem2);
    }
}

