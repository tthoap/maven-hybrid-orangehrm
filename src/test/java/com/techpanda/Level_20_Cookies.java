package com.techpanda;

import com.techpanda.share.Register;
import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.techpanda.HomePageO;
import pageObjects.techpanda.LoginPageO;
import pageObjects.techpanda.MyAccountPageO;
import pageObjects.techpanda.RegisterPageO;

import java.util.Set;

public class Level_20_Cookies extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        this.cookies = Register.cookies;

        homePage = PageGenerator.getPage(HomePageO.class, driver);
        loginPage = homePage.openLoginPage();
        loginPage.setPageCookies(driver, this.cookies);

        myAccountPage = PageGenerator.getPage(MyAccountPageO.class, driver);
//        verifyEquals(myAccountPage.getMyAccountPageTitle(),"My Dashboard");
    }

    @Test
    public void Employee_01_CreateNewEmployee() {

    }

    @AfterClass
    public void afterClass(){
//        driver.quit();
    }

    private WebDriver driver;
    private HomePageO homePage;
    private LoginPageO loginPage;
    private RegisterPageO registerPage;
    private MyAccountPageO myAccountPage;

    private Set<Cookie> cookies;
}
