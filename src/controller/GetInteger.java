package controller;

/**
 * Created by Lenovo on 13.09.2016.
 */
public class GetInteger {
    public int getInteger(String string, String nameOfInt){

        String[] array = new String[2];
        array = string.split(nameOfInt);
        array = array[1].split(" ");
        return Integer.parseInt(array[0]);
    }
}
