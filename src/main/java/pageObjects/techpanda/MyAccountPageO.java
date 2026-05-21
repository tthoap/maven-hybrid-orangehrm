package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.techpanda.MyAccountPageUI;

public class MyAccountPageO extends BasePage {
    private WebDriver driver;

    public MyAccountPageO(WebDriver driver) {
        this.driver = driver;
    }


    public String getSuccessMsg() {
        waitElementVisible(driver, MyAccountPageUI.SUCCES_MSG);
        return getElementText(driver, MyAccountPageUI.SUCCES_MSG);
    }

    public String getMyAccountPageTitle() {
        waitElementVisible(driver, MyAccountPageUI.MY_DASHBOARD_TITLE);
        return getElementText(driver, MyAccountPageUI.MY_DASHBOARD_TITLE);
    }
}
