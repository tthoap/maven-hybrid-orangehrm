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

import java.util.List;

public class Level_13_Upload_File extends BaseTest {


    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass( String appURL, String browserName) {

        driver = getBrowserDriver(browserName, appURL);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }


    @Test
    public void Upload_01_Single() {
        homePage.uploadMultipleFiles(driver, hanoi);
        homePage.uploadMultipleFiles(driver, hcm);
        homePage.uploadMultipleFiles(driver, hoian);

        Assert.assertTrue(homePage.isFileLoaded(hanoi));
        Assert.assertTrue(homePage.isFileLoaded(hcm));
        Assert.assertTrue(homePage.isFileLoaded(hoian));

       // homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(hanoi));
        Assert.assertTrue(homePage.isFileUploadedSuccess(hcm));
        Assert.assertTrue(homePage.isFileUploadedSuccess(hoian));

    }



   @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String hanoi = "HaNoi.jpg";
    String hcm = "HCM.jpg";
    String hoian = "HoiAn.jpg";


}
