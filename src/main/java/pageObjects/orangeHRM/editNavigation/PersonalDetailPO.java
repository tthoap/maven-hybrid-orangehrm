package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class PersonalDetailPO extends EditNavigatorPO {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Firstname textbox value")
    public String getFirstnameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMProperties(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, "_value");
    }
    @Step("Get Lastname textbox value")
    public String getLastnameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementDOMProperties(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, "_value");
    }
    @Step("Get EmployeeID textbox value")
    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID);
        return getElementDOMProperties(driver, PersonalDetailPageUI.EMPLOYEE_ID, "_value");
    }

    public void clickToProfileImage(WebDriver driver) {
        waitElementClickable(driver, PersonalDetailPageUI.PROFILE_IMAGE);
        clickToElement(driver, PersonalDetailPageUI.PROFILE_IMAGE);
    }

    public String getErrorMessage(WebDriver driver) {
        waitElementVisible(driver, PersonalDetailPageUI.PROFILE_IMAGE_ERROR_MESSAGE);
        return getElementText(driver, PersonalDetailPageUI.PROFILE_IMAGE_ERROR_MESSAGE);
    }

    public Dimension getProfileNatureImageSize() {
        waitElementVisible(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        int x = Integer.parseInt(getElementDOMProperties(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE, "naturalWidth"));
        int y = Integer.parseInt(getElementDOMProperties(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE, "naturalHeight"));
        return new Dimension(x, y);
    }
}
