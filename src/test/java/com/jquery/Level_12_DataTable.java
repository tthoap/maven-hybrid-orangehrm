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

    //@Test
    public void Table_02_Search() {
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isSelectedDataDiplayed("384187", "Afghanistan", "407124", "791312"));
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Females", "12253515");
        Assert.assertTrue(homePage.isSelectedDataDiplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByName("Males", "276472");
        Assert.assertTrue(homePage.isSelectedDataDiplayed("276880", "Angola", "276472", "553353"));
        homePage.sleepInSecond(2);

    }
    //@Test
    public void Table_03_Action() {
        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.refreshPage(driver);
        homePage.clickToActionByCountryName("Afghanistan", "edit");

        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.refreshPage(driver);
        homePage.clickToActionByCountryName("Afghanistan", "remove");

    }
   // @Test
    public void Table_04_Index() {
        homePage.openPageURL(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/" );

        homePage.clickLoadDataButton();

        homePage.sleepInSecond(2);
        homePage.enterToTextByColumnNameAndRowIndex("Company", "1", "AXON");
        homePage.enterToTextByColumnNameAndRowIndex("Contact Person", "1", "Thomas Edison");
        homePage.enterToTextByColumnNameAndRowIndex("Order Placed", "1", "100");
        homePage.selectDropdownByColumnNameAndRowIndex("Country", "1", "Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "1");
        homePage.actionToRowByRowIndex("1","Remove Current Row");
        homePage.sleepInSecond(2);
        homePage.enterToTextByColumnNameAndRowIndex("Company", "3", "Honda");
        homePage.enterToTextByColumnNameAndRowIndex("Contact Person", "3", "Alexander");
        homePage.enterToTextByColumnNameAndRowIndex("Order Placed", "3", "200");
        homePage.selectDropdownByColumnNameAndRowIndex("Country", "3", "Japan");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "3");
        homePage.actionToRowByRowIndex("3","Move Down");


    }
    @Test
    public void Table_05_Get_All_Values() {
        //Lấy trên UI
        List<String> coutryActualValue =  homePage.getColumnAllValueByColumnName("Country");
        System.out.println(coutryActualValue.size());

        List<String> femalesActualValue =  homePage.getColumnAllValueByColumnName("Females");
        System.out.println(femalesActualValue.size());

        //Data or File Data or API, sau đó so sánh value trên UI và API/DB/File là được

    }


   @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;


}
