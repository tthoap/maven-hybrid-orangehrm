package javaFaker;

import java.util.Calendar;
import java.util.Random;

public class Topic_01_Random {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(9999));
        System.out.println(random.nextBoolean());
        System.out.println(random.nextFloat());
        System.out.println(random.nextLong());
        System.out.println(random.nextDouble());

        System.out.println("JohnSmith" + getRandomNumber(100, 999) + "@gmail.com");
        System.out.println("JohnSmith" + random.nextInt(99999) + "@gmail.com");
        System.out.println("JohnSmith" + random.nextInt(99999) + "@gmail.com");
        System.out.println("JohnSmith" + random.nextInt(99999) + "@gmail.com");
        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());

    }
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static String getRandomEmail() {
        return "JohnSmith" + getRandomNumberByDateTime() + "@gmail.com";
    }

    public static long getRandomNumberByDateTime() {
        return Calendar.getInstance().getTimeInMillis() * 1000;
    }

}
