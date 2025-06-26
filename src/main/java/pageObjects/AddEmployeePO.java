package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFistnameTextbox(String firstname) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstname);
    }

    public void enterToLastnameTextbox(String lastname) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID);
        return getElementDOMProperties(driver, AddEmployeePageUI.EMPLOYEE_ID, "value");
    }

    public void clicktoSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clicktoSaveButton(driver, AddEmployeePageUI.SAVE_BUTTON);
    }
}
