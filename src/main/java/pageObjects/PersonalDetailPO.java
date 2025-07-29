package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.PersonalDetailPageUI;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
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

    public ContactDetailPO OpenContactDetailPage() {
        //wait
        //click
        return new ContactDetailPO(driver);
    }
}
