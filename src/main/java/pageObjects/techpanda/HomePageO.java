package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPO;
import pageUIs.techpanda.HomePageUI;

public class HomePageO extends BasePage {
    private WebDriver driver;

    public HomePageO(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPageO openLoginPage() {
        waitElementClickable(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGenerator.getPage(LoginPageO.class, driver);
    }
}
