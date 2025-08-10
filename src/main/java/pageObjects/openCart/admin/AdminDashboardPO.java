package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminCustomerPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }
    public AdminCustomerPO openCustomerPage() {
        waitElementClickable(driver, AdminCustomerPageUI.CUSTOMER_MENU);
        clickToElement(driver, AdminCustomerPageUI.CUSTOMER_MENU);
        waitElementClickable(driver, AdminCustomerPageUI.CUSTOMER_LINK);
        clickToElement(driver, AdminCustomerPageUI.CUSTOMER_LINK);
        return PageGenerator.getPage(AdminCustomerPO.class, driver);
    }
}
