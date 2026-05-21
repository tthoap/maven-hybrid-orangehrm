package pageUIs.orangeHRM;

public class EmployeeListPageUI {
    public static final String ADD_EMPLOYEE_BUTTON = "XPATH=//a[text()='Add Employee']";
    public static final String EMPLOYEE_ID_TEXTBOX = "XPATH=//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String EMPLOYEE_ROW_BY_COLUME_NAME_AND_ROW_VALUE = "XPATH=//div[text()='%s']/parent::div/parent::div/following-sibling::div//div[text()='%s']";
    public static final String EMPLOYEE_ACTION_BY_ID = "XPATH=//div[text()='%s']/parent::div//following-sibling::div//i[contains(@class,'%s')]";
    public static final String YES_BUTTON_POPUP = "XPATH=//button[contains(string(),'Yes, Delete')]";
}
