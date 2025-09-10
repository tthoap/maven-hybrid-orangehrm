package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByPageNumber(String pageNumber) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_PAGE_NUMBER,pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_BY_PAGE_NUMBER,pageNumber);
        sleepInSecond(2);
    }

    public boolean isPageActiveByNumbner(String pageNumner) {
        waitElementVisible(driver, HomePageUI.DYNAMIC_SELECTED_PAGE_BY_PAGE_NUMBER, pageNumner);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_SELECTED_PAGE_BY_PAGE_NUMBER, pageNumner);
    }

    public void enterToHeaderTextboxByName(String headerName, String value) {
        waitElementVisible(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, headerName);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, value, headerName);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
    }
}
