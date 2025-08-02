package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.ContactDetailPO;
import pageObjects.orangeHRM.editNavigation.DependentsPO;
import pageObjects.orangeHRM.editNavigation.JobPO;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;

public class Level_07_Switch_Page extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGeneratorGeneric.getPage(LoginPO.class, driver);

        adminUsername = "hoatranauto";
        adminPassword = "%Sue!ImFG@2L!$&SI#";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee() {
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
    public void Employee_02_Switch_Page() {
//        //Từ personal  qua Contact
//        contactDetailPage = personalDetailPage.openContactDetailPage(driver);
//
//        //Từ Contact qua Job
//        jobPage = contactDetailPage.openJobPage(driver);
//
//        //Từ Job qua Dependent
//        dependentsPage = jobPage.openDependentsPage(driver);
//
//        //Từ Dependent qua Personal
//        personalDetailPage = dependentsPage.openPersonalPage(driver);
//
//        //Từ Personal qua Job
//        jobPage = personalDetailPage.openJobPage(driver);
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
    private JobPO jobPage;
    private DependentsPO dependentsPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname;


}
