package com.orangehrm;

import core.BaseTest;
import utilities.DataConfigNet;
import lombok.extern.slf4j.Slf4j;
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
import testdata.orangehrm.EmployeeData;

@Slf4j
public class Level_25_DataTest_III_Static extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);
        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        dataConfig = DataConfigNet.getData();

        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = dataConfig.getUserName();
        employeePassword = "Beocon@123";
        employeeFirstname = dataConfig.getFirstName();
        employeeLastname = dataConfig.getLastName();

        loginPage.enterToTextboxByLabel(driver, "Username", EmployeeData.ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", EmployeeData.ADMIN_PASSWORD);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));

    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        //Add new employee with enabled login Info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", EmployeeData.FIRST_NAME);
        addEmployeePage.enterToTextboxByName(driver, "lastName",  EmployeeData.LAST_NAME);
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username",  EmployeeData.USER_NAME);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", EmployeeData.USER_PASSWORD);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", EmployeeData.USER_PASSWORD);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

    }



    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private DataConfigNet dataConfig;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeUsername, employeePassword, employeeLastname;


}
