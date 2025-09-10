package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

public class Level_12_DataTable extends BaseTest {


    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass( String appURL, String browserName) {

        driver = getBrowserDriver(browserName, appURL);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }


    //@Test
    public void Table_01_Paging() {
        homePage.openPageByPageNumber("3");
        Assert.assertTrue(homePage.isPageActiveByNumbner("3"));
        homePage.openPageByPageNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumbner("5"));
        homePage.openPageByPageNumber("8");
        Assert.assertTrue(homePage.isPageActiveByNumbner("8"));
    }

    @Test
    public void Table_02_Search() {
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Females", "384187");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Males", "407124");
        homePage.sleepInSecond(2);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;


}
