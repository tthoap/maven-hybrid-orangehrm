package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployeePO extends BasePageFactory {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement firstNameTextbox;
    public void enterToFistnameTextbox(String firstname) {
        waitElementVisible(driver, firstNameTextbox);
        sendkeyToElement(firstNameTextbox, firstname);
    }

    @FindBy(name = "lastName")
    private WebElement lastNameTexbox;
    public void enterToLastnameTextbox(String lastname) {
        waitElementVisible(driver, lastNameTexbox);
        sendkeyToElement(lastNameTexbox, lastname);
    }

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement EmployeeID;
    public String getEmployeeID() {
        waitElementVisible(driver, EmployeeID);
        return getElementDOMProperties( EmployeeID, "value");
    }
    @FindBy(xpath = "//button[contains(string(), 'Save')]")
    private WebElement saveButton;
    public void clicktoSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(saveButton);
    }

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
