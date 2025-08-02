package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;

public class Level_06_Page_Manager_I extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = new LoginPO(driver);

        adminUsername = "hoatranauto";
        adminPassword = "%Sue!ImFG@2L!$&SI#";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        loginPage.clickToLoginButton();
        dashboardPage = new DashboardPO(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        dashboardPage.clickToPIMModule();
        employeeListPage = new EmployeeListPO(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToAddEmployeeButton();
        addEmployeePage = new AddEmployeePO(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
        addEmployeePage.enterToLastnameTextbox(employeeLastname);
        employeeID = addEmployeePage.getEmployeeID();

        addEmployeePage.clicktoSaveButton();
        personalDetailPage = new PersonalDetailPO(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(),employeeFirstname);
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(),employeeLastname);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }

    @Test
    public void Login_02_Invalid_Username() {
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname;


}
