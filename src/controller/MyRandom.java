package controller;

public class MyRandom {
    public int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        int random = (int)(Math.random() * ((max - min) + 1)) + min;
//        System.out.println("Random instance:" + random);
        return random;
    }
}
