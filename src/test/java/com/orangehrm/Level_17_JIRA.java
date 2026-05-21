package com.orangehrm;

import com.aventstack.chaintest.plugins.ChainTestListener;
import core.BaseTest;
import io.qameta.allure.*;
import jiraConfigs.JiraCreateIssue;
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

@Epic("Orange HRM")
//Add screenshot at every step
public class Level_17_JIRA extends BaseTest {

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


    @Description("Create a new employee")
    @Story("#P123 - Employee - CRUD")
    @Severity(SeverityLevel.BLOCKER)
    @JiraCreateIssue(isCreateIssue =  true)
    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver)); //FAIL

    }

    @Description("View new employee")
    @Story("#P123 - Employee - CRUD")
    @Severity(SeverityLevel.CRITICAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_02_ViewNewEmployee() {
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
        addEmployeePage.enterToLastnameTextbox(employeeLastname);
        employeeID = addEmployeePage.getEmployeeID();

    }

    @Description("Edit employee")
    @Story("#P123 - Employee - CRUD")
    @Severity(SeverityLevel.NORMAL)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_03_EditNewEmployee() {
        personalDetailPage = addEmployeePage.clicktoSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(), employeeLastname);//FAIL
    }

    @Description("Delete a new employee")
    @Story("#P123 - Employee - CRUD")
    @Severity(SeverityLevel.MINOR)
    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void Employee_04_RemoveNewEmployee() {
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), employeeFirstname);//FAIL

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
