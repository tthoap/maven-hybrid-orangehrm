package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.LoginPageUI;

public class LoginPageO extends BasePage {
    private WebDriver driver;

    public LoginPageO(WebDriver driver) {
        this.driver = driver;
    }


    public RegisterPageO clickCreateAnAccoountLink() {
        waitElementVisible(driver, LoginPageUI.CREATE_AN_ACCOUNT_LINK);
        clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_LINK);
        return PageGenerator.getPage(RegisterPageO.class, driver);
    }
}
