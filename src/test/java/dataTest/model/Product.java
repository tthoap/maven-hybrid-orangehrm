package dataTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpanda.share.Register;
import core.GlobalConstants;

import java.io.File;

public class Product {
    public static Product getProduct() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "product.json"), Product.class);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @JsonProperty("Login")
    private Login login;

    public static class Login{

        @JsonProperty
        private String username;

        @JsonProperty
        private String password;
    }

    @JsonProperty("firstname")
    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("lastname")
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    @JsonProperty("Register")
    private Register register;

    public static class Register{
        @JsonProperty
        private String fullName;
    }

    public String getFullName() {
        return register.fullName;
    }

    public String getUsername() {
        return login.username;
    }

    public String getPassword() {
        return login.password;
    }
}
