package javaFaker;

import net.datafaker.Faker;

public class Topic_02_Data_Faker {
    public static void main(String[] args) {
        Faker faker = new net.datafaker.Faker();
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().lastName());
        System.out.println(faker.name().fullName());
        System.out.println(faker.number().randomDigit());
        System.out.println(faker.number().randomDigits(999));
        System.out.println(faker.number().randomDigits(99999));


        System.out.println(faker.address().country());
        System.out.println(faker.address().fullAddress() + " " + faker.address().city());


    }
}
