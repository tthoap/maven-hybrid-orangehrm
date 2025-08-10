package pageUIs.openCart.user;

public class UserRegisterPageUI {
    public static final String FIRST_NAME_TEXTBOX = "//input[@name='firstname']";
    public static final String LAST_NAME_TEXTBOX = "//input[@name='lastname']";
    public static final String EMAIL_TEXTBOX = "//input[@name='email']";
    public static final String TELEPHONE_TEXTBOX = "//input[@name='telephone']";
    public static final String PASSWORD_TEXTBOX = "//input[@id='input-password']";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='input-confirm']";
    public static final String PRIVACY_POLICY_CHECKBOX = "//input[@name='agree']";
    public static final String CONTINUE_BUTTON = "//input[@value='Continue' and @type='submit']";

    public static final String SUCCESS_MESSAGE = "//div[@id='common-success']//h1[text()='Your Account Has Been Created!']";
    public static final String LOGOUT_BUTTON = "//div[@class='list-group']//a[text()='Logout']";
}
