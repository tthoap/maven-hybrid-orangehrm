package com.orangehrm;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;
import reportConfig.ChainTestReport;
import reportConfig.ExtentManager;

import java.lang.reflect.Method;

//Add screenshot at every step
public class Level_15_ChainTest extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(browserName, appURL);
        ChainTestListener.log("Run on browser - " + browserName);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee() {
        ChainTestListener.log("Run on browser - " + browserName);
        ChainTestListener.log("AddNewEmployee - Step 01: Enter to Username and Password: " + adminUsername + " | " + adminPassword);

        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        ChainTestListener.log("AddNewEmployee - Step 02: Navigate to Dashboard page");
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        ChainTestListener.log("AddNewEmployee - Step 03: Navigate to Employee list page");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver)); //FAIL

    }

    @Test
    public void Employee_02_ViewNewEmployee() {
        ChainTestListener.log("AddNewEmployee - Step 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("AddNewEmployee - Step 05: Enter to FirstName and LastName" + employeeFirstname + " | " + employeeLastname);
        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
        addEmployeePage.enterToLastnameTextbox(employeeLastname);
        employeeID = addEmployeePage.getEmployeeID();

    }
    @Test
    public void Employee_03_EditNewEmployee() {
        ChainTestListener.log("AddNewEmployee - Step 06: Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clicktoSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
        ChainTestListener.log("AddNewEmployee - Step 07: Verify Firstname is displayed: " + employeeFirstname + " | " + employeeLastname + " | " + employeeID);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(), employeeLastname);//FAIL
    }
    @Test
    public void Employee_04_RemoveNewEmployee() {
        ChainTestListener.log("AddNewEmployee - Step 08: Verify Lastname is displayed " + employeeLastname );
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), employeeFirstname);//FAIL

        ChainTestListener.log("AddNewEmployee - Step 09: Verify Employee ID is displayed: " + employeeID);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeLastname);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname, browserName;


}
