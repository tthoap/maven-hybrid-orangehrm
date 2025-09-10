package pageUIs.jquery;

public class HomePageUI {
    public static final String DYNAMIC_PAGE_BY_PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_SELECTED_PAGE_BY_PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s' and contains(@class,'active')]";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME= "xpath=//div[text()='%s']/parent::div/following-sibling::input";
}
