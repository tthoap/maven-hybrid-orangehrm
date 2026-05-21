package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.HomePageUI;
import pageUIs.techpanda.RegisterPageUI;

public class RegisterPageO extends BasePage {
    private WebDriver driver;

    public RegisterPageO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, RegisterPageUI.FIRST_NAME);
        sendkeyToElement(driver, RegisterPageUI.FIRST_NAME, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, RegisterPageUI.LAST_NAME);
        sendkeyToElement(driver, RegisterPageUI.LAST_NAME, lastName);
    }

    public void enterToEmailTextbox(String email) {
        waitElementVisible(driver, RegisterPageUI.EMAIL_ADDRESS);
        sendkeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS, email);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, RegisterPageUI.PASSWORD);
        sendkeyToElement(driver, RegisterPageUI.PASSWORD, password);
    }

    public void enterToConfirmPassword(String confirmPassword) {
        waitElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD);
        sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD, confirmPassword);
    }

    public MyAccountPageO clickToRegisterButton() {
        waitElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getPage(MyAccountPageO.class, driver);
    }

    public MyAccountPageO acceptContinueAlert() {
        acceptToAlert(driver);
        return PageGenerator.getPage(MyAccountPageO.class, driver);
    }
}
