package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_09_Switch_Url_Role extends BaseTest {
    private String userURL, adminURL;

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userURL, String adminURL){
       //Gán dữ liệu cho 2 biến userURL, adminURL ở trên
        this.userURL = userURL;
        this.adminURL = adminURL;
        userFirstname = "Hoa";
        userLastname = "Tran";
        userPassword = "12345678x@X";
        userEmailAddess = "Hoa" + getRandomNumber() + "@gmail.com";
        telephone = "0987456785";
        userAdmin = "hoatran";
        passwordAdmin = "Beocon@123";

        //Mở browser lên sẽ là mở trang user
       driver = getBrowserDriver(browserName, userURL);
       userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
    }


    @Test(enabled = false)
    public void OpenCart_01() {
        /* GIỐNG HÀNH VI CỦA USER */
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterTLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddess);
        userRegisterPage.enterTelephoneNumber(telephone);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.enterToConfirmPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();
        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //User => Admin
        userRegisterPage.openAdminsite(driver, adminURL);
        adminLoginPage =  PageGenerator.getPage(AdminLoginPO.class, driver);
        adminLoginPage.sleepInSecond(2);
        adminLoginPage.enterToAdminUserName(userAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User
        userHomePage = adminLoginPage.openUserSite(driver,userURL);// vì ở đâu cũng mở dc trang user, nên hàm này sẽ define trong basepage.
        userHomePage.sleepInSecond(2);
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);
        userLoginPage.sleepInSecond(2);
        userLoginPage.enterToEmailAddressTextbox(userEmailAddess);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userLoginPage.sleepInSecond(10);

        userMyAccountPage = userLoginPage.clickToLoginButton();
        userMyAccountPage.sleepInSecond(2);

        Assert.assertTrue(userMyAccountPage.isMyAcountPageDisplayed());

        //User => Admin

        userMyAccountPage.openAdminsite(driver, adminURL);
        adminLoginPage =  PageGenerator.getPage(AdminLoginPO.class, driver);
        adminLoginPage.sleepInSecond(4);
        adminLoginPage.enterToAdminUserName(userAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);

        adminLoginPage.sleepInSecond(10);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        //Admin => USer
        userHomePage = adminDashboardPage.openUserSite(driver,userURL);


    }


    @Test(enabled = false)
    public void OpenCart_02_Switching_Without_Logout() {
        /* TIỆN CHO VIỆC DEV/TESTING , NHƯNG KO GIỐNG THỰC TẾ */
        //User vào đăng ký tài khoản rồi mua hàng ..
        //User ko logout
        //Chuyền qua admin page -> login 1 lần
        //Admin vào verify đơn hàng
        //Admin ko logout
        //Chuyển qua trang user
        //Chuyển qua trang admin

        //1 Step 1 User login
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterTLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddess);
        userRegisterPage.enterTelephoneNumber(telephone);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.enterToConfirmPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();
        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //User -> Admin
        userRegisterPage.openAdminsite(driver, adminURL); //vì đứng ở đâu cũng mở ra trang admin, nê sẽ define trong BasePage
        adminLoginPage =  PageGenerator.getPage(AdminLoginPO.class, driver);
        adminLoginPage.enterToAdminUserName(userAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        //Admin ko hề logout, nó vẫn ở trang customer

        //Admin -> User
        userHomePage = adminCustomerPage.openUserSite(driver,userURL);// vì ở đâu cũng mở dc trang user, nên hàm này sẽ define trong basepage.
        userHomePage.sleepInSecond(2);
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        //User -> Admin
        userMyAccountPage.openAdminsite(driver, adminURL);
        adminDashboardPage =  PageGenerator.getPage(AdminDashboardPO.class, driver);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

    }

    @Test
    public void OpenCart_03_Multiple_Tab_Window() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterTLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddess);
        userRegisterPage.enterTelephoneNumber(telephone);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.enterToConfirmPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();
        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getWindowID(driver);

        //User -> Admin by New tab
        userRegisterPage.openUrlByNewTAB(driver, adminURL);

        adminLoginPage =  PageGenerator.getPage(AdminLoginPO.class, driver);
        adminLoginPage.enterToAdminUserName(userAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminWindowID = adminCustomerPage.getWindowID(driver);
        //Admin ko hề logout, nó vẫn ở trang customer

        //Admin -> User
        adminCustomerPage.switchToWindowTabByID(driver, userWindowID);
        adminCustomerPage.sleepInSecond(3);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class, driver);

        userHomePage = userRegisterPage.openUserHomeLogo(driver);
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        //User -> Admin
        userMyAccountPage.switchToWindowTabByID(driver, adminWindowID);
        adminCustomerPage =  PageGenerator.getPage(AdminCustomerPO.class, driver);

        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    private AdminLoginPO adminLoginPage;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private UserLoginPO userLoginPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserMyAccountPO userMyAccountPage;
    private String userWindowID, adminWindowID;
    private String userFirstname, userLastname, userPassword, userEmailAddess, telephone, userAdmin, passwordAdmin;
}
