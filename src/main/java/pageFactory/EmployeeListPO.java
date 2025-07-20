package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeListPO extends BasePageFactory {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement AddEmployeeButton;

    public void clickToAddEmployeeButton() {
        waitElementClickable(driver, AddEmployeeButton);
        clickToElement(AddEmployeeButton);
    }

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
