package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class PersonalDetailPO extends EditNavigatorPO {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getFirstnameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMProperties(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, "_value");
    }

    public String getLastnameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementDOMProperties(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, "_value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID);
        return getElementDOMProperties(driver, PersonalDetailPageUI.EMPLOYEE_ID, "_value");
    }
}
