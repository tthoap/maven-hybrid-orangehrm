package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {

    public static BasePageFactory getInstance(){
        return new BasePageFactory();
    }

    public void openPageURL(WebDriver driver, String pageUrl) {
        System.out.println("Driver in BasePage" + driver.toString());
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void fowardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public void senkeyToAlert(WebDriver driver, String keyToSend) {
        waitForAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void switchToWindowTabByID(WebDriver driver, String expectedID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(expectedID);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            driver.switchTo().window(id);
            sleepInSeconds(2);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String parentWindow) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            if (!id.equals(parentWindow)) {
                driver.switchTo().window(id);
                sleepInSeconds(2);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        driver.getTitle();
    }

    private void sleepInSeconds(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Thao tác với element
    public void clickToElement(WebElement element) {
       element.click();
    }

    public void sendkeyToElement( WebElement element, String keysToSend) {
        element.clear();
        element.sendKeys(keysToSend);
    }


    public String getElementDOMAttribute( WebElement element, String attributeValue){
       return element.getDomAttribute(attributeValue);
    }

    public String getElementDOMProperties(WebElement element, String propertyValue){
        return element.getDomProperty(propertyValue);
    }


    public String getElementText(WebElement element) {
        return element.getText();
    }

    public boolean isElementDisplayed( WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementSelected( WebElement element){
        return element.isSelected();
    }

    public boolean isElementEnabled(WebElement element){
        return element.isEnabled();
    }

    public void doubleClick(WebDriver driver, WebElement element){
        new Actions(driver).doubleClick(element).perform();
    }


    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitElementVisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitListElementVisible(WebDriver driver, List <WebElement> elements){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitElementSelected(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitElementInvisible(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, List <WebElement> elements){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(elements));
    }


    private final int SHORT_TIMEOUT = 10;
    private final int LONG_TIMEOUT = 30;

}
