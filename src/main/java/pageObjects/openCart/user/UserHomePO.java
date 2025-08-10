package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }
    public UserLoginPO clickToMyAccount() {
        scrollToElementOnTop(driver, UserHomePageUI.MY_ACCOUNT_LINK_AT_FOOTER);
        waitElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK_AT_FOOTER);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK_AT_FOOTER);
        return PageGenerator.getPage(UserLoginPO.class, driver);
    }
}
