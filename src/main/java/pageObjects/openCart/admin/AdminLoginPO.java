package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToAdminUserName(String username) {
        waitElementVisible(driver, AdminLoginPageUI.USERNAME_TEXBOX);
        sendkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXBOX, username);
    }

    public void enterToAdminPassword(String password) {
        waitElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXBOX, password);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPO.class, driver);
    }
}
