package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {

    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstName(String firstname) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstname);
    }
    public void enterTLastName(String lastname) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);
    }
    public void enterToEmailAddress(String email) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.EMAIL_TEXTBOX, email);
    }
    public void enterTelephoneNumber(String telephone) {
        waitElementVisible(driver, UserRegisterPageUI.TELEPHONE_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.TELEPHONE_TEXTBOX, telephone);
    }
    public void enterToPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }
    public void enterToConfirmPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver,UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void acceptPrivacyCheckbox() {
        waitElementVisible(driver, UserRegisterPageUI.PRIVACY_POLICY_CHECKBOX);
        checkToCheckbox(driver,UserRegisterPageUI.PRIVACY_POLICY_CHECKBOX);
    }


    public void clickToContinueButton() {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplayed() {
        waitElementVisible(driver, UserRegisterPageUI.SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserRegisterPageUI.SUCCESS_MESSAGE);
    }



}
