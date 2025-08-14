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
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_10_Multiple_Driver extends BaseTest {

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
       userDriver = getBrowserDriver(browserName, userURL);
       userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

       adminDriver = getBrowserDriver(browserName, adminURL);
       adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);
    }





    @Test
    public void OpenCart_02_Switching_Without_Logout() {
        /* TIỆN CHO VIỆC DEV/TESTING , NHƯNG KO GIỐNG THỰC TẾ */
        //User vào đăng ký tài khoản rồi mua hàng ..
        //User ko logout
        //Chuyền qua admin page -> login 1 lần
        //Admin vào verify đơn hàng
        //Admin ko logout
        //Chuyển qua trang user
        //Chuyển qua trang admin

        //Brower1: 1 Step 1 User login
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

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

        //Browers 2: User -> Admin
        adminLoginPage.enterToAdminUserName(userAdmin);
        adminLoginPage.enterToAdminPassword(passwordAdmin);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());
        //Admin ko hề logout, nó vẫn ở trang customer

        //Browser 1: FF của User chạy tiếp

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);
        Assert.assertTrue(userMyAccountPage.isMyAcountPageDisplayed());
    }


    @AfterClass
    public void afterClass(){
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    private WebDriver userDriver;
    private WebDriver adminDriver;
    private String userURL, adminURL;
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
