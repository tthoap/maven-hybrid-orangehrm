package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPO extends BasePageFactory {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Define các locator/Element
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameTextbox;

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver, usernameTextbox);
        sendkeyToElement(usernameTextbox, username);
    }

    @FindBy(name = "password")
    private WebElement passwordTextbox;

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, passwordTextbox);
        sendkeyToElement(passwordTextbox, password);
    }

    @FindBy(xpath = "//button[contains(string(),'Login')]")
    private WebElement loginButton;

    public void clickToLoginButton() {
        waitElementClickable(driver,loginButton);
        clickToElement(loginButton);
    }
}
