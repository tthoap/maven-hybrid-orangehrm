package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.EmployeeListPageUI;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePO clickToAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGeneratorGeneric.getPage(AddEmployeePO.class, driver);

    }
}
