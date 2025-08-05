package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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
import pageObjects.openCart.user.UserRegisterPO;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.ContactDetailPO;
import pageObjects.orangeHRM.editNavigation.DependentsPO;
import pageObjects.orangeHRM.editNavigation.JobPO;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;

public class Level_09_Switch_Url extends BaseTest {
    private String userURL, adminURL;

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userURL, String adminURL){
       //Gán dữ liệu cho 2 biến userURL, adminURL ở trên
        this.userURL = userURL;
        this.adminURL = adminURL;

        //Mở browser lên sẽ là mở trang user
       driver = getBrowserDriver(browserName, userURL);
       userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
    }


    @Test
    public void OpenCart_01() {
        userLoginPage = userHomePage.clickToMyAccount();

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName("");
        userRegisterPage.enterTLastName("");
        userRegisterPage.enterToEmailAddress("");
        userRegisterPage.enterToPassword("");
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        userRegisterPage.clickToLogoutButton();
        userHomePage = userRegisterPage.clickToContinueButton();

        adminLoginPage = userRegisterPage.openAdminsite(adminURL);

        adminLoginPage.enterToUserName("");
        adminLoginPage.enterToPassword("");
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCutomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLink();

        userHomePage = adminLoginPage.openUserSite(userURL);





        //Từ User page ==> Admin Page
        //Login bên Admin
        //Thao tác bên admin
        //logout khỏi admin

        //Quay lại User
        //Login bên User
        //THao tác bên User

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
}
