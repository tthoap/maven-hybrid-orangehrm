package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public void openPageURL(WebDriver driver, String pageUrl) {
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

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend) {
        getWebElement(driver, locator).sendKeys(keysToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public WebElement getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        clickToElement(driver, parentLocator);
        sleepInSeconds(2);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
        List<WebElement> allItems = getListElement(driver, childItemLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                sleepInSeconds(2);
                break;
            }
        }
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeValue){
       return getWebElement(driver, locator).getDomAttribute(attributeValue);
    }

    public String getElementDOMProperties(WebDriver driver, String locator, String propertyValue){
       return getWebElement(driver, locator).getDomProperty(propertyValue);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver,locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaColor){
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator){
        if(!getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator){
        if(getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void swichToFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyboardtoElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }



    private final int SHORT_TIMEOUT = 10;
    private final int LONG_TIMEOUT = 30;




}
