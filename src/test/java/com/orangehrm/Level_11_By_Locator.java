package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pageObjects.orangeHRM.editNavigation.*;

public class Level_11_By_Locator extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(Level_11_By_Locator.class);

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.sleepInSecond(3);
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
        addEmployeePage.enterToLastnameTextbox(employeeLastname);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clicktoSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(),employeeFirstname);
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(),employeeLastname);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }

    @Test
    public void Employee_02_Page_Navigator() {
        //Từ personal  qua Contact
        contactDetailPage = (ContactDetailPO) personalDetailPage.openEditNavigatorByPageName("Contact Details");

        //Từ Contact qua Job
        jobPage = (JobPO) contactDetailPage.openEditNavigatorByPageName("Job");

        //Từ Job qua Dependent
        dependentsPage = (DependentsPO) jobPage.openEditNavigatorByPageName("Dependents");

        //Từ Dependent qua Personal
        personalDetailPage = (PersonalDetailPO) dependentsPage.openEditNavigatorByPageName("Personal Details");

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
    private ContactDetailPO contactDetailPage;
    private EditNavigatorPO editNavigatorPage;
    private JobPO jobPage;
    private DependentsPO dependentsPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname;


}
