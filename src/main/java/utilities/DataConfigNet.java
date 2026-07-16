package utilities;

import net.datafaker.Faker;

import java.util.Locale;

public class DataConfigNet {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker();

    public static DataConfigNet getData() {
        return new DataConfigNet();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getLastName() {
        return faker.name().lastName();
    }
    public String getUserName() {
        return faker.credentials().username();
    }

    public String getPassword() {
        return faker.credentials().password(10, 15, true, true, true);
    }
    public String getEmployeeID(){
        return getRandomNumber();
    }
    public String getRandomNumber() {
        return String.valueOf(faker.number().randomDigit());
    }


}
