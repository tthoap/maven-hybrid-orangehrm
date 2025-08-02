package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class DependentsPO extends EditNavigatorPO {
    private WebDriver driver;

    public DependentsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }



}
