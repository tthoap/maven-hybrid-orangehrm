package pageUIs;

public class AddEmployeePageUI {
    public static final String USER_NAME_TEXTBOX = "//input[@name='firstName']";
    public static final String PASSWORD_TEXTBOX = "//input[@name='lastName']";
    public static final String EMPLOYEE_ID = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "//button[contains(string(), 'Save')]";
}
