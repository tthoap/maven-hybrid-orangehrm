package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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

import java.lang.reflect.Method;

//Add screenshot at every step
public class Level_14_Extent_report_v2 extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee(Method method) {
//        ExtentManager.startTest(method.getName(),"Employee_01_CreateNewEmployee");
//        ExtentManager.getTest().log(LogStatus.INFO,"Employee 01 Create New Employee");
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 01: Enter to Username and Password: " + adminUsername + " | " + adminPassword);
//        loginPage.enterToUsernameTextbox(adminUsername);
//        loginPage.enterToPasswordTextbox(adminPassword);
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 02: Navigate to Dashboard page");
//        dashboardPage = loginPage.clickToLoginButton();
//        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
//        dashboardPage.sleepInSecond(2);
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 03: Navigate to Employee list page");
//        employeeListPage = dashboardPage.clickToPIMModule();
//        verifyFalse(employeeListPage.isLoadingSpinnerDisappear(driver)); //FAIL
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 04: Navigate to Add Employee page");
//        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
//        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 05: Enter to FirstName and LastName" + employeeFirstname + " | " + employeeLastname);
//        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
//        addEmployeePage.enterToLastnameTextbox(employeeLastname);
//        employeeID = addEmployeePage.getEmployeeID();
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 06: Navigate to Personal Detail page");
//        personalDetailPage = addEmployeePage.clicktoSaveButton();
//        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
//        personalDetailPage.sleepInSecond(2);
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 07: Verify Firstname is displayed: " + employeeFirstname + " | " + employeeLastname + " | " + employeeID);
//        verifyEquals(personalDetailPage.getFirstnameTextboxValue(), employeeLastname);//FAIL
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 08: Verify Lastname is displayed " + employeeLastname );
//        verifyEquals(personalDetailPage.getLastnameTextboxValue(), employeeFirstname);//FAIL
//        takeScreenShot();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"AddNewEmployee - Step 09: Verify Employee ID is displayed: " + employeeID);
//        verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);
//        takeScreenShot();
//
//        ExtentManager.endTest();
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
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname;


}
