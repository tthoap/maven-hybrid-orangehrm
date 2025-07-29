package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DashboardPageUI;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeeListPO clickToPIMModule() {
        waitElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver, DashboardPageUI.PIM_MODULE);
        return PageGeneratorGeneric.getPage(EmployeeListPO.class, driver);
    }
}
