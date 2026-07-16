package dataTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.GlobalConstants;

import java.io.File;

public class Employee {
    public static Employee employee = new Employee();

    public static Employee getEmployee() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "employee.json"), Employee.class);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @JsonProperty("first_name")
    private String employeeFirstName;

    @JsonProperty("last_name")
    private String employeeLastName;

    @JsonProperty("user_name")
    private String employeeUsername;

    @JsonProperty("password")
    private String employeePassword;

    @JsonProperty("email")
    private String employeeEmail;

    @JsonProperty("dob")
    private String employeeDob;

    @JsonProperty("address")
    private String employeeAddress;


    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeeDob() {
        return employeeDob;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }
}
