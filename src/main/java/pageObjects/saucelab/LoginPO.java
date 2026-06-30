package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.saucelab.LoginPageUI;

public class LoginPO  extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPO loginToSauce(String username, String password) {
        waitElementVisible(driver, LoginPageUI.USERNAME);
        sendkeyToElement(driver, LoginPageUI.USERNAME, username);
        sendkeyToElement(driver, LoginPageUI.PASSWORD, password);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(ProductPO.class, driver);
    }
}
