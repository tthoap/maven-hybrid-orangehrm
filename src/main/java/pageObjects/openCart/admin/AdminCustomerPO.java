package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.admin.AdminCustomerPageUI;

public class AdminCustomerPO extends BasePage {
    WebDriver driver;

    public AdminCustomerPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCustomerHeaderDisplayed() {
        waitListElementVisible(driver, AdminCustomerPageUI.CUSTOMER_HEDAER_TEXT);
        return isElementDisplayed(driver, AdminCustomerPageUI.CUSTOMER_HEDAER_TEXT);
    }
}

