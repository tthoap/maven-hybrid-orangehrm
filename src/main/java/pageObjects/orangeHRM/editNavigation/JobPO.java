package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class JobPO extends EditNavigatorPO{
    private WebDriver driver;

    public JobPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
