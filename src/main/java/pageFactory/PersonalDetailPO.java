package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.PersonalDetailPageUI;

import java.util.List;

public class PersonalDetailPO extends BasePageFactory {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement firstNameTextbox;
    public String getFirstnameTextboxValue() {
        waitElementVisible(driver, firstNameTextbox);
        return getElementDOMProperties(firstNameTextbox,"value");
    }

    @FindBy(name = "lastName")
    private WebElement lastNameTexbox;
    public String getLastnameTextboxValue() {
        waitElementVisible(driver, lastNameTexbox);
        return getElementDOMProperties(lastNameTexbox,"value");
    }

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeID;
    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, employeeID);
        return getElementDOMProperties(employeeID, "value");
    }

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
    
}
