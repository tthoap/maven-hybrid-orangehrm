package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static BasePage getInstance(){
        return new BasePage();
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

    public String getWindowID(WebDriver driver){
        return driver.getWindowHandle();
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

    public void openUrlByNewTAB(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openUrlByNewWindow(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
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

    public By getByLocator(String locator) {
        if (locator == null || locator.trim().isEmpty()) {
            throw new RuntimeException("Locator cannot be null or empty!");
        }
        String[] locatorArr = locator.split("=", 2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        System.out.println("Locator prefix = " + locatorPrefix);
        System.out.println("Locator value = " + locatorValue);
        switch (locatorArr[0].toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new RuntimeException("Locator is not support!");
        }
    }

    public String castParameters(String locator, String... values){
        return locator = String.format(locator, (Object[]) values);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        waitElementVisible(driver, locator);
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restvalues) {
        waitElementVisible(driver, castParameters(locator, restvalues));
        getWebElement(driver, castParameters(locator, restvalues)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, CharSequence keysToSend) {
        waitElementVisible(driver, locator);
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keysToSend);
    }
    public void sendkeyToElement(WebDriver driver, String locator, CharSequence keysToSend, String... restvalues) {
        waitElementVisible(driver, castParameters(locator, restvalues));
        //getWebElement(driver, castParameters(locator, restvalues)).clear();
        getWebElement(driver, castParameters(locator, restvalues)).sendKeys(keysToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem, String... restvalues) {
        new Select(getWebElement(driver, castParameters(locator, restvalues))).selectByVisibleText(valueItem);
    }

    public WebElement getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption();
    }

    public WebElement getSelectedItemInDropdown(WebDriver driver, String locator, String... restvalues) {
        return new Select(getWebElement(driver, castParameters(locator, restvalues))).getFirstSelectedOption();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator, String... restValues) {
        return new Select(getWebElement(driver, castParameters(locator, restValues))).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        clickToElement(driver, parentLocator);
        sleepInSeconds(2);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
        List<WebElement> allItems = getListElement(driver, childItemLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                sleepInSeconds(2);
                break;
            }
        }
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem, String... restValues) {
        clickToElement(driver, castParameters(parentLocator, restValues));
        sleepInSeconds(2);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
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

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeValue, String... restvalues ){
       return getWebElement(driver, castParameters(locator, restvalues)).getDomAttribute(attributeValue);
    }

    public String getElementDOMProperties(WebDriver driver, String locator, String propertyValue){
       return getWebElement(driver, locator).getDomProperty(propertyValue);
    }

    public String getElementDOMProperties(WebDriver driver, String locator, String propertyValue, String... restvalues){
       return getWebElement(driver, castParameters(locator, restvalues)).getDomProperty(propertyValue);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restvalues ) {
        return getWebElement(driver, castParameters(locator, restvalues)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver,locator).getCssValue(propertyName);
    }
    public String getElementCssValue(WebDriver driver, String locator, String propertyName, String... restvalues){
        return getWebElement(driver,castParameters(locator, restvalues)).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaColor){
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }
    public int getListElementNumber(WebDriver driver, String locator, String... restvalues){
        return getListElement(driver, castParameters(locator, restvalues)).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator){
        if(!getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckbox(WebDriver driver, String locator, String... restvalues){
        if(!getWebElement(driver, castParameters(locator, restvalues)).isSelected()){
            getWebElement(driver, castParameters(locator, restvalues)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator){
        if(getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }
    public void uncheckToCheckbox(WebDriver driver, String locator, String... restvalues){
        if(getWebElement(driver, castParameters(locator, restvalues)).isSelected()){
            getWebElement(driver, castParameters(locator, restvalues)).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restvalues){
        return getWebElement(driver, castParameters(locator, restvalues)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restvalues){
        return getWebElement(driver, castParameters(locator, restvalues)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locator, String... restvalues){
        return getWebElement(driver, castParameters(locator, restvalues)).isEnabled();
    }

    public void swichToFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void swichToFrame(WebDriver driver, String locator, String... restValues){
        driver.switchTo().frame(getWebElement(driver, castParameters(locator, restValues)));
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

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.domain;");
    }

    public String getPageURL(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.URL;");
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDOMAttribute(driver,locator,"style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(5);
    }

    public void clickToElementByJS(WebDriver driver,String locator, String... restvalues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, castParameters(locator, restvalues)));
        sleepInSecond(5);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
        sleepInSecond(1);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator, String... restValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, castParameters(locator, restValues)));
        sleepInSecond(1);
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public void waitElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitElementVisible(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameters(locator, restvalues))));
    }

    public void waitListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitListElementVisible(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameters(locator, restvalues))));
    }

    public void waitElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitElementSelected(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameters(locator, restvalues))));
    }

    public void waitElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitElementClickable(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameters(locator, restvalues))));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator, String... restvalues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameters(locator, restvalues))));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator, String... restvalues){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, castParameters(locator, restvalues))));
    }

    public void waitElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitElementPresence(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameters(locator, restvalues))));
    }

    public void waitListElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }
    public void waitListElementPresence(WebDriver driver, String locator, String... restvalues){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameters(locator,restvalues))));
    }


    //OrangeHRM
    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver, BasePageUI.LOADING_SPINNER);
    }


    //Run for OpenCart app
    public UserHomePO clickToLogoutLinkAtUserSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        waitElementClickable(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }
    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public void openAdminsite(WebDriver driver, String adminURL) {
        openPageURL(driver,adminURL);
    }

    public UserHomePO openUserSite(WebDriver driver, String userUrl) {
        openPageURL(driver,userUrl);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserHomePO openUserHomeLogo(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver, BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }


    private int SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;
    private int LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;
}

