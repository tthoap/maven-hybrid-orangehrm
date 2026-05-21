package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.EmployeeListPageUI;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("CLick to Add employee button")
    public AddEmployeePO clickToAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(AddEmployeePO.class, driver);

    }


    public void enterEmployeeById(WebDriver driver, String employeeID) {
        waitElementVisible(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXTBOX);
        sendkeyToElement(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXTBOX, employeeID);
    }

    public boolean isSearchedEployeeDisplayedBycolumeID(WebDriver driver, String columeName, String rowValue) {
        waitElementVisible(driver,EmployeeListPageUI.EMPLOYEE_ROW_BY_COLUME_NAME_AND_ROW_VALUE,columeName,rowValue);
        return isElementDisplayed(driver,EmployeeListPageUI.EMPLOYEE_ROW_BY_COLUME_NAME_AND_ROW_VALUE,columeName,rowValue);
    }


    public void clickToActionIconByEmployeeID(WebDriver driver, String employeeID, String action) {
        waitElementVisible(driver,EmployeeListPageUI.EMPLOYEE_ACTION_BY_ID, employeeID, action);
        clickToElement(driver,EmployeeListPageUI.EMPLOYEE_ACTION_BY_ID, employeeID, action);
    }

    public void clickYesOnPopupToDeleteEmployee(WebDriver driver) {
        waitElementVisible(driver, EmployeeListPageUI.YES_BUTTON_POPUP);
        clickToElement(driver, EmployeeListPageUI.YES_BUTTON_POPUP);
    }
}
