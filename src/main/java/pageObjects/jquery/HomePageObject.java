package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isSelectedDataDiplayed(String females, String countryName, String males, String total) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_INFO, females, countryName,males, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_PAGE_INFO, females, countryName,males, total);
    }

    public void clickToActionByCountryName(String countryName, String action) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_ACTIONN_BY_COUNTRY_NAME, countryName, action);
        clickToElement(driver, HomePageUI.DYNAMIC_ACTIONN_BY_COUNTRY_NAME, countryName, action);
        sleepInSecond(2);
    }

    public void clickLoadDataButton() {
        waitElementClickable(driver,HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextByColumnNameAndRowIndex(String columnName, String rowIndex, String keyToSend) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, keyToSend, rowIndex, String.valueOf(columnIndex));

    }

    public void selectDropdownByColumnNameAndRowIndex(String columnName, String rowIndex, String optionToSelect) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitElementClickable(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        selectItemInDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, optionToSelect, rowIndex, String.valueOf(columnIndex));
    }

    public void checkToCheckboxByColumnNameAndRowIndex(String columnName, String rowIndex) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        waitElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
        checkToCheckbox(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));

    }

    public void actionToRowByRowIndex(String rowIndex, String actionName) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX, rowIndex,actionName);
        clickToElement(driver, HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX, rowIndex,actionName);
        sleepInSecond(3);
    }

    public List<String> getColumnAllValueByColumnName(String columnName) {
        List<WebElement> allPage = getListElement(driver, HomePageUI.ALL_PAGES);
        List<String> columnAllvalue = new ArrayList<String>();

        waitListElementVisible(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName);
        int columnIndex = getListElementNumber(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) + 1;

        for (WebElement page : allPage){
            page.click();

            List<WebElement> columnAllElement = getListElement(driver, HomePageUI.DYNAMIC_COLUMN_INDEX, String.valueOf(columnIndex));
            for (WebElement value : columnAllElement){
                columnAllvalue.add(value.getText());
            }

        }

        return columnAllvalue;
    }
}
