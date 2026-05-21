package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;
import pageUIs.orangeHRM.AddEmployeePageUI;
import pageUIs.BasePageUI;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

   @Step("Enter to Firstname textbox")
    public void enterToFistnameTextbox(String firstname) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstname);
    }

    @Step("Enter to Lastname textbox")
    public void enterToLastnameTextbox(String lastname) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastname);
    }

    @Step("Add employee ID")
    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID);
        return getElementDOMProperties(driver, AddEmployeePageUI.EMPLOYEE_ID, "value");
    }

    @Step("Click to Save button")
    public PersonalDetailPO clicktoSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, BasePageUI.LOADING_SPINNER);
        return PageGenerator.getPage(PersonalDetailPO.class, driver);
    }
}
