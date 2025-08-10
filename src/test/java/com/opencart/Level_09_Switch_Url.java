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

public class Level_09_Switch_Url extends BaseTest {
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


    @Test
    public void OpenCart_01() {
        userLoginPage = userHomePage.clickToMyAccount();

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterTLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddess);
        userRegisterPage.enterTelephoneNumber(telephone);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.enterToConfirmPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //User => Admin
        adminLoginPage = userRegisterPage.openAdminsite(driver, adminURL); //vì đứng ở đâu cũng mở ra trang admin, nê sẽ define trong BasePage

        adminLoginPage.enterToAdminUserName(passwordAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User
        userHomePage = adminLoginPage.openUserSite(driver,userURL);// vì ở đâu cũng mở dc trang user, nên hàm này sẽ define trong basepage.

        userLoginPage = userHomePage.clickToMyAccount();
        userLoginPage.enterToEmailAddressTextbox(userEmailAddess);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAcountPageDisplayed());

        //User => Admin

        adminLoginPage = userMyAccountPage.openAdminsite(driver, adminURL);
        adminLoginPage.enterToAdminUserName(passwordAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        //Admin => USer
        userHomePage = adminDashboardPage.openUserSite(driver,userURL);


    }


    @Test
    public void OpenCart_02() {

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
    private String userFirstname, userLastname, userPassword, userEmailAddess, telephone, userAdmin, passwordAdmin;
}
