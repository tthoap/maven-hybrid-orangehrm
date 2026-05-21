package com.techpanda.share;

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.techpanda.HomePageO;
import pageObjects.techpanda.LoginPageO;
import pageObjects.techpanda.MyAccountPageO;
import pageObjects.techpanda.RegisterPageO;

import java.util.Set;

public class Register extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeTest
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        homePage = PageGenerator.getPage(HomePageO.class, driver);

        loginPage = homePage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccoountLink();

        registerPage.enterToFirstNameTextbox("Hoa");
        registerPage.enterToLastNameTextbox("Tran");
        registerPage.enterToEmailTextbox("hoa" + getRandomNumber()+ "@gmail.com");
        registerPage.enterToPassword("12345678");
        registerPage.enterToConfirmPassword("12345678");
        myAccountPage =  registerPage.clickToRegisterButton();
//        myAccountPage = registerPage.acceptContinueAlert();
        verifyEquals(myAccountPage.getSuccessMsg(),"Thank you for registering with Main Website Store.");

        cookies = myAccountPage.getPageCookies(driver);
        for (Cookie cookie : cookies) {
            System.out.println("Cookies are :" + cookie);
        }

        closeBrowser();
    }



    private WebDriver driver;
    private HomePageO homePage;
    private LoginPageO loginPage;
    private RegisterPageO registerPage;
    private MyAccountPageO myAccountPage;

    public static Set<Cookie> cookies;

}
