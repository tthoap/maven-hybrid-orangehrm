package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.MyAccountPageUI;

public class UserMyAccountPO extends BasePage {
    WebDriver driver;

    public UserMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMyAcountPageDisplayed() {
        waitElementVisible(driver, MyAccountPageUI.MY_ACCOUNT_BREADCRUM);
        return isElementDisplayed(driver, MyAccountPageUI.MY_ACCOUNT_BREADCRUM);
    }
}
