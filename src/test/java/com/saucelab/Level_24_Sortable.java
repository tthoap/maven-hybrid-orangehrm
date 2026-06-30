package com.saucelab;

import core.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductPO;

@Slf4j
public class Level_24_Sortable extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);
        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        productPage = loginPage.loginToSauce("standard_user", "secret_sauce");

    }

    @Test
    public void Sort_01_Name() {
        productPage.sortBy("Name (A to Z)");
        verifyEquals(productPage.getSortSelectedItemText(), "Name (A to Z)");
        verifyTrue(productPage.isProductNameSortByAscending());
        productPage.sleepInSecond(2);

        productPage.sortBy("Name (Z to A)");
        verifyEquals(productPage.getSortSelectedItemText(), "Name (Z to A)");
        verifyTrue(productPage.isProductNameSortByDescending());
        productPage.sleepInSecond(2);

        productPage.sortBy("Price (low to high)");
        verifyEquals(productPage.getSortSelectedItemText(), "Price (low to high)");
        verifyTrue(productPage.isProductPriceSortByAscending());
        productPage.sleepInSecond(2);

        productPage.sortBy("Price (high to low)");
        verifyEquals(productPage.getSortSelectedItemText(), "Price (high to low)");
        verifyTrue(productPage.isProductPriceSortByDescending());

    }

    @Test
//    public void Sort_02_Price() {
//        productPage.sortBy("Price (low to high)");
//        verifyTrue(productPage.isProductPriceSortByAscending());
//
//        productPage.sortBy("Price (high to low)");
//        verifyTrue(productPage.isProductPriceSortByDescending());
//
//    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private ProductPO productPage;


}
