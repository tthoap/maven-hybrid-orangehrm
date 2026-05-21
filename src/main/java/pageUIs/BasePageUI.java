package pageUIs;

public class BasePageUI {
    //OrangeHRM
    public static final String LOADING_SPINNER = "css=div.oxd-loading-spinner";
    public static final String TEXBOX_BY_LABEL = "Xpath=//label[text()=\"%s\"]/parent::div/following-sibling::div//input";
    public static final String TEXBOX_BY_NAME = "Xpath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT = "Xpath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE = "Xpath=//h6[text()='%s']/parent::div//button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MAIN_MENU = "Xpath=//span[text()='%s']/parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String PARENT_DROPDOWN_BY_LABEL = "Xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String CHILD_DROPDOWN_BY_LABEL = "Xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";
    public static final String TOAST_MESSAGE_BY_TEXT = "Xpath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String LOGIN_ERROR_MESSAGE_BY_TEXT = "Xpath=//p[text()='%s']";
    public static final String RADIO_BY_LABEL = "Xpath=//label[text()='%s']";
    public static final String CHECKBOX_BY_LABEL = "Xpath=//p[text()='%s']/parent::div/div//span";
    public static final String USER_DROPDOWN = "Css=p.oxd-userdropdown-name";
    public static final String LOGOUT_LINK = "XPath=//a[@class='oxd-userdropdown-link' and text()='Logout']";
    public static final String SEARCH_BUTTON_IN_MENU_BY_TEXT = "XPath=//h5[text()='%s']/parent::div/parent::div/following-sibling::div//button[contains(string(),'Search')]";



    //OpenCart
    public static final String USER_MY_ACCOUNT_HEADER = "Xpath=//nav[@id='top']//span[text()='My Account']";
    public static final String ADMIN_LOGOUT_LINK_ITEM = "Xpath=//i[contains(@class,'fa-sign-out')]//parent::a/span[text()='Logout']";
    public static final String USER_LOGOUT_LINK_ITEM = "Xpath=//ul[contains(@class,'dropdown-menu')]//a[text()='Logout']";
    public static final String USER_HOME_LOGO = "CSS=div#logo>a";

    //jQuery
    public static final String UPLOAD_FILE_TYPE = "Css=input[type=file]";

}
