package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataConfig {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker();

    public static DataConfig getData() {
        return new DataConfig();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getLastName() {
        return faker.name().lastName();
    }

    public String getUsername() {
        return faker.name().username();
    }
}
