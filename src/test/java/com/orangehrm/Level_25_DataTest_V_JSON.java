package com.orangehrm;

import core.BaseTest;
import dataTest.model.Employee;
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

@Slf4j
public class Level_25_DataTest_V_JSON extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        employeeData = Employee.getEmployee();

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = employeeData.getEmployeeUsername() + getRandomNumber();

    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        //Add new employee with enabled login Info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeData.getEmployeeFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeData.getEmployeeLastName());
        addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID);

        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeData.getEmployeeUsername());
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeeData.getEmployeePassword());
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeeData.getEmployeePassword());

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeData.getEmployeeFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeData.getEmployeeLastName());
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
    private Employee employeeData;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname,employeeUsername, employeePassword;


}
